package com.chandler;

import com.concurrent.test.BoundedBuffer;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by i325622 on 6/8/17.
 */
public class BoundedBufferTest {

    @Test
    public void testIsEmptyWhenConstructed() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Assert.assertTrue(bb.isEmpty());
        Assert.assertFalse(bb.isFull());
    }

    @Test
    public void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for(int i=0;i<10;i++) {
            bb.put(i);
        }
        Assert.assertTrue(bb.isFull());
        Assert.assertFalse(bb.isEmpty());
    }

    @Test
    public void testInterruptBlockAction() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread(){
            @Override
            public void run() {
                try {
                    int unused = bb.get();
                    System.out.println("Fail");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        taker.start();
        try {
            Thread.sleep(1000);
            taker.interrupt();
            taker.join();
            System.out.println(taker.isAlive());
        } catch (InterruptedException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
        Assert.assertFalse(taker.isAlive());
    }

}
