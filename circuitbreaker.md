# Circuit Breaker(断路器)

## 为什么要用断路器？

  断路器可以在分布式系统中，有效预防级联错误，提供稳定性保护。断路器可以有效防止远程接口调用过程中，因为一个组件超时而导致整个系统不可用的现象。

  举个例子，有一个Web应用，它同时会与另一个第三方Web服务交互。这个时候，如果它的某个服务依赖的第三方Web服务过载，并且导致数据库处于超高负载的状态。这种情况下，数据库需要很长时间才能给这个第三方Web服务一个回复。这就会导致调用超时而失败。再回到我们的Web应用，用户会发现他们提交的表单长时间得不到响应。坐在电脑前的他们，除了不断点击刷新按钮，什么都不能干。这又无意给服务器增加了更多同样的请求。最终导致了Web应用因为资源耗尽而使得所有服务不可用。请注意，这会影响到所有的用户，其中包括那些没用用到依赖于那个第三方服务的用户。

在Web服务调用中引入断路器，这会使得请求可以变得快速得到失败响应，让用户知道后台出问题了，刷新可解决不了问题。同时，引入断路器还可以让失败行为只限制住那些依赖于那个第三方请求的用户，而其他的用户不受影响。断路器还能允许精明的开发人员标记住那些不可用的站点，或者当断路器断开的时候，适当返回一些缓存的内容。


Akka库提供了一种断路器的实现，**akka.pattern.CircuitBreaker**。下面来详细看一下怎么使用它。

## 断路器怎么做到的

- 断路器在正常情况下，处于Closed（闭合）状态：
 - 出现异常或者调用超时（超过配置的**callTimeout**参数）会触发失败failure counter加1
 - 成功恢复后会重置failure counter为0
 - 当failure counter的值达到**maxFailures**以后，断路器转入Open（断开）状态
- 处于Open(断开)状态：
 - 所有的调用会失败，并返回**CircuitBreakerOpenException**
 - 当时间到达配置的**resetTimeout**以后，断路器进入Half-Open（半断开）状态
- 处于Half-Open(半断开)状态：
 - 允许第一次尝试服务调用，这次不会快速失败
 - 其他调用都会失败，和Open状态一样
 - 如果第一次调用成功，断路器会重置成Closed状态，resetTimeout也会重置为初始化配置的值。
 - 如果第一次调用失败，断路器会回到Open状态。而resetTimeout的值会变成resetTimeout的值乘以指数回退因子（**exponential backoff factor**）。
 - 状态转换监听器（**State transition listeners**）
  - **onOpen, onClose, onHalfOpen** 为每种状态提供了回调
  - 这些回调由已知的**ExecutionContext**来执行
 - 调用结果监听器（**Calls result listeners**）
  - 提供回调可以用来收集一些统计信息，比如调用成功，失败，超时等。
  - 支持的回调有：**onCallSuccess, onCallFailure, onCallTimeout, onCallBreakerOpen.**
  - 这些回调由已知的**ExecutionContext**来执行

![MacDown logo](http://doc.akka.io/docs/akka/current/images/circuit-breaker-states.png)

## 示例：

### 初始化
配置 CircuitBreaker：
- maxFailures设置为5
- callTimeout设置为10s
- resetTimeout设置为1min

  ```
import scala.concurrent.duration._
import akka.pattern.CircuitBreaker
import akka.pattern.pipe
import akka.actor.{ Actor, ActorLogging, ActorRef }

import scala.concurrent.Future
import scala.util.{ Failure, Success, Try }

class DangerousActor extends Actor with ActorLogging {
  import context.dispatcher

  val breaker =
    new CircuitBreaker(
      context.system.scheduler,
      maxFailures = 5,
      callTimeout = 10.seconds,
      resetTimeout = 1.minute).onOpen(notifyMeOnOpen())

  def notifyMeOnOpen(): Unit =
    log.warning("My CircuitBreaker is now open, and will not close for one minute")
  ```






### 基于API的Future & Synchronous

一旦配置了断路器的actor初始化以后，就可以通过基于API的Future机制或者基于API的synchronous同步机制来实现与该actor的交互。这两种API都可以被看作是调用保护（Call Protection），因为不管是同步还是异步，断路器的目的都是为了保护你的系统不陷入一个调用失败而级联到其他服务的失败。**withCircuitBreaker**是基于Future的API，需要给它传入一个异步方法（可以用Future来包装）。例如，一个调用要从数据库中获取数据，然后把数据返回给sender。如果因为某些原因，数据库没有响应，或者中间哪里又出了什么其他问题。断路器就会转入Open（断开）状态，而不会一遍又一遍去尝试。

基于Synchronous的同步API是**withSyncCircuitBreaker**，用它包住你的调用即可。传入的参数就不需要用Future包住。

 ```
def dangerousCall: String = "This really isn't that dangerous of a call after all"

def receive = {
  case "is my middle name" =>
    breaker.withCircuitBreaker(Future(dangerousCall)) pipeTo sender()
  case "block for me" =>
    sender() ! breaker.withSyncCircuitBreaker(dangerousCall)
}
 ```
#### 注意
使用CircuitBreaker伴生对象的apply或create方法将返回在调用者的线程中执行回调的CircuitBreaker。如果异步Future不必要的时候，这可以是很有用的，例如仅调用同步的API。


package akka.learn3

import java.util.Optional

import akka.actor.Actor.Receive
import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorLogging, ActorSystem, OneForOneStrategy, PoisonPill, Props}

/**
  * Created by chandlerzhao on 2017/8/28.
  */
class BrokenPlateException(info: String) extends RuntimeException
class DrunkenFoolException(info: String) extends RuntimeException
class RestaurantFireException(info: String) extends RuntimeException
class TiredChefException(info: String) extends RuntimeException

class Manager extends Actor with ActorLogging {

  val chef = context.actorOf(Props[Chef], "Chef")

  override val supervisorStrategy = {
    OneForOneStrategy() {
      case x: BrokenPlateException => Resume
      case d: DrunkenFoolException => Restart
      case r: RestaurantFireException => Escalate
      case t: TiredChefException => Stop
      case _ => Escalate
    }
  }

  override def preStart = {
    println("Manager preStart")
  }

  override def postStop = {
    println("Manager postStop")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Manager preRestart")
  }

  override def postRestart(reason: Throwable): Unit = {
    println("Manager postRestart")
  }

  override def receive: Receive = {
    case "shut down" => {
      context.stop(chef)
    }
    case msg:String => {
      chef ! msg
    }
  }
}

class Chef extends Actor with ActorLogging {

  override def preStart = {
    println("Chef preStart")
  }

  override def postStop = {
    println("Chef postStop")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Chef preRestart")
  }

  override def postRestart(reason: Throwable): Unit = {
    println("Chef postRestart")
  }

  override def receive: Receive = {
    case "1" => {
      throw new BrokenPlateException("不小心打破了盘子")
    }
    case "2" => {
      throw new DrunkenFoolException("喝酒喝喝傻了，遭到了客户投诉")
    }
    case "3" => {
      throw new RestaurantFireException("不小心点火烧了店")
    }
    case "4" => {
      throw new TiredChefException("干活干多了，想休息一下")
    }
  }
}

object Main {
  def main(args: Array[String]) {
    val sys = ActorSystem("system")
    val manager = sys.actorOf(Props[Manager],"Manager")
    manager ! "1"
//    println("*" * 20)
//    manager ! "2"
//    println("*" * 20)
//    manager ! "3"
//    println("*" * 20)
//    manager ! "4"
//    println("*" * 20)
    Thread.sleep(2000)
//    manager ! "shut down"
    manager ! PoisonPill
  }
}


