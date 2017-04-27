package com.io;

import org.springframework.core.io.ResourceLoader;

import java.io.*;

/**
 * Created by i325622 on 4/6/17.
 */
public class IOTest {

    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = new File("/Users/i325622/log.txt");
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buf = new byte[1024];
            fileInputStream.read(buf);
            System.out.println(new String(buf));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
