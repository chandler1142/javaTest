package designpattern.java8.strategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by chandlerzhao on 2017/7/26.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Compressor gzipCompressor = new Compressor(new ZipCompressionStrategy());
        gzipCompressor.compress(Paths.get("src/file.txt"), new File("src/file1.zip"));

    }
}
