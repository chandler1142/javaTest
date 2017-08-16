package jvm.gc;

/**
 * Created by chandlerzhao on 2017/7/29.
 */
public class EdenTest {

    private static final int _1MB = 1024*1024;

    /**
     *  VM Options: -Xms20M -Xmx20M -Xmn10m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     */
    public static void main(String[] args) {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2*_1MB];
        a2 = new byte[2*_1MB];
        a3 = new byte[2*_1MB];
        a4 = new byte[4*_1MB]; //Monitor GC
    }
}
