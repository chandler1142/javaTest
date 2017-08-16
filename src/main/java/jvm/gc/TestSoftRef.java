package jvm.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * Created by chandlerzhao on 2017/7/29.
 */
public class TestSoftRef {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
        SoftReference<Object> softRef = new SoftReference<Object>(obj, refQueue);
        System.out.println(softRef.get()); // java.lang.Object@f9f9d8
        System.out.println(refQueue.poll());// null

        // 清除强引用,触发GC
        obj = null;
        System.gc();

        System.out.println(softRef.get());

        Thread.sleep(200);
        System.out.println(refQueue.poll());
    }
}
