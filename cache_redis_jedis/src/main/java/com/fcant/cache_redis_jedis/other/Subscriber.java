package com.fcant.cache_redis_jedis.other;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Subscriber
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 22:14 2020/8/27/0027
 */
public class Subscriber extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("订阅渠道:["+ channel + "],收到的消息："+ message);
    }
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        Subscriber subscriber = new Subscriber();
        // 从Redis订阅
        System.out.println("我是订阅者.....");
        jedis.subscribe(subscriber, "channel2");
    }
}
