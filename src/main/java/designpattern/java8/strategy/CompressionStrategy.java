package designpattern.java8.strategy;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by chandlerzhao on 2017/7/26.
 */
public interface CompressionStrategy {

    OutputStream compress(OutputStream data) throws IOException;
    
}
