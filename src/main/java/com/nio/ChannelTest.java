package com.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * Created by i325622 on 6/5/17.
 */
public class ChannelTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile afile = new RandomAccessFile("./src/main/resources/file.txt", "rw");
        FileChannel channel = afile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        int byteRead = channel.read(byteBuffer);
        while(byteRead != -1) {
            System.out.println("Read: " + byteRead);
            byteBuffer.flip();

            while(byteBuffer.hasRemaining()) {
                System.out.println((char)byteBuffer.get());
            }

            byteBuffer.clear();
            byteRead = channel.read(byteBuffer);
        }

        afile.close();


        SocketChannel socketChannel = SocketChannel.open();

        Selector selector = Selector.open();

        socketChannel.configureBlocking(false);
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

    }
}
