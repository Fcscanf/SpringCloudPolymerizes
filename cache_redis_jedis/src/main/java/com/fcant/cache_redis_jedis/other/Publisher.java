package com.fcant.cache_redis_jedis.other;

import redis.clients.jedis.Jedis;

/**
 * Publisher
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 22:09 2020/8/27/0027
 */
public class Publisher {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println("我是发布者....");
        jedis.publish("channel2","hello,girls.....");
        System.out.println("消息发布完毕....");
    }

}
