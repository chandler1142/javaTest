package com.redis.chapter1;

import redis.clients.jedis.Jedis;

/**
 * Created by i325622 on 6/19/17.
 */
public class HelloRedis {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        
        jedis.set("hello", "world");
        String r = jedis.get("hello");
        System.out.println(r);
    }

}
