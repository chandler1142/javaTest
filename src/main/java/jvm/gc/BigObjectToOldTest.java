package jvm.gc;

/**
 * Created by chandlerzhao on 2017/7/29.
 */
public class BigObjectToOldTest {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM Options: -Xms20M -Xmx20M -Xmn10m -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=1024 -XX:+PrintGCDetails -XX:+UseSerialGC
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] localtion;
        localtion = new byte[4 * _1MB];
    }

}
