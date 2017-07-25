package com.concurrent;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by i325622 on 3/20/17.
 */
public class ClientTest {
    Socket socket;

    public static void main(String[] args) {
        ClientTest test = new ClientTest();
//        test.connectSendReceive(12313132);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.connectSendReceive(1231231);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.connectSendReceive(345345);
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.connectSendReceive(456456);
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.connectSendReceive(789798);
            }
        });

        thread1.run();
        thread2.run();
        thread3.run();
        thread4.run();
    }

    private void connectSendReceive(long i) {
        try {
            Socket socket = new Socket("localhost", 8009);
            MessageUtils.sendMessage(socket, Long.toString(i));
            String message = MessageUtils.getMessage(socket);
            System.out.println("accept from Server: " + message);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
