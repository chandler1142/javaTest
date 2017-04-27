package com.thread.gc;

/**
 * Created by i325622 on 3/23/17.
 */
public class FinalizeCase {

    public static void main(String[] args) {
        Block block = new Block();
        block = null;
        System.gc();

    }

    static class Block {
        byte[] _200M = new byte[1024*1024*200];
    }
}
