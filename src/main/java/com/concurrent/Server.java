package com.concurrent;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by i325622 on 3/20/17.
 */
public class Server implements Runnable{
    ServerSocket serverSocket;
    volatile boolean keepProcessing = true;

    public Server(int port, int millisecondsTimeout) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(millisecondsTimeout);
    }

    @Override
    public void run() {
        System.out.println("Server Starting\n");

        while(keepProcessing) {
            System.out.println("accepting client");
            try {
                Socket socket = serverSocket.accept();
                System.out.println("got client");
                process(socket);
            } catch (IOException e) {
                handle(e);
            }
        }
    }

    private void handle(Exception e) {
        if(!(e instanceof SocketException)) {
            e.printStackTrace();
        }
    }

    private void stopProcessing() {
        keepProcessing = false;
        closeIgnoringException(serverSocket);
    }

    void process(Socket socket) {
        if(socket == null) {
            return;
        }
        System.out.println("Server: getting message");

        Runnable clientHandler = new Runnable() {
            @Override
            public void run() {
                try {
                    String message = MessageUtils.getMessage(socket);
                    System.out.printf("Server: got message: %s\n", message);
                    Thread.sleep(1000);
                    System.out.printf("Server: sending reply: %s\n", message);
                    MessageUtils.sendMessage(socket, "Processed: " + message);
                    System.out.println("Sent");
                    closeIgnoringException(socket);
                } catch (IOException e) {
                    handle(e);
                } catch (InterruptedException e) {
                    handle(e);
                }
            }
        };

        Thread clientConnection = new Thread(clientHandler);
        clientConnection.start();
    }

    private void closeIgnoringException(Socket socket) {
        if(socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    private void closeIgnoringException(ServerSocket serverSocket) {
        if(serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
