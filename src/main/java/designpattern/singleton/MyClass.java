package designpattern.singleton;

/**
 * Created by chandlerzhao on 2017/7/20.
 */
public class MyClass {

    public volatile static MyClass singleInstance = null;

    private MyClass() {
        System.out.println("init My Class");
    }

    public static MyClass getInstance() {
        if (singleInstance == null) {
            synchronized (MyClass.class) {
                if (singleInstance == null) {
                    singleInstance = new MyClass();
                    return singleInstance;
                }
            }
        }
        return singleInstance;
    }

}
