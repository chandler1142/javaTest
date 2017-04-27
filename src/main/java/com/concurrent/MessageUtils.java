package com.concurrent;

import java.io.*;
import java.net.Socket;

/**
 * Created by i325622 on 3/20/17.
 */
public class MessageUtils {
    public static void sendMessage(Socket socket, String message) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeUTF(message);
        objectOutputStream.flush();
    }

    public static String getMessage(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectOutputStream = new ObjectInputStream(inputStream);
        return objectOutputStream.readUTF();
    }

}
