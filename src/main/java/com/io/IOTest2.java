package com.io;

import java.io.*;

/**
 * Created by i325622 on 4/6/17.
 */
public class IOTest2 {

    public static void main(String[] args) throws IOException {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        InputStream inputStream = new FileInputStream("/Users/i325622/log.txt");
        objectInputStream = new ObjectInputStream(inputStream);
        System.out.println(objectInputStream.readUTF());
    }
}
