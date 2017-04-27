package com.concurrent;

import java.io.IOException;

/**
 * Created by i325622 on 3/20/17.
 */
public class TestThread {

    public static void main(String[] args) throws IOException {
        Server server = new Server(8009, 3000);
        server.run();
    }
}
