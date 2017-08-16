-XX:+PrintGCTimeStamps:
打印此次垃圾回收距离jvm开始运行的所耗时间
-XX:+PrintGCDeatils
打印垃圾回收的细节信息

-Xloggc:<filename>
将垃圾回收信息输出到指定文件

-XX:+PrintGCDateStamps
需要打印日历形式的时间戳选项

-XX:+PrintGCApplicationStoppedTime
-XX:+PrintGCApplicationConcurrentTime
打印应用程序由于执行VM安全点操作而阻塞的时间以及两个安全点操作之间应用程序的运行时间
-XX:+PrintSafepointStatistics
可以将垃圾回收的安全点与其他的安全点区分开