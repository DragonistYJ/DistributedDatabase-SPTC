package org.example.lesson;

import redis.clients.jedis.Jedis;

public class RedisTemplate {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("rowkey-info-name", "Alice");
        jedis.lpush("test", "test");
        jedis.hset("rowkey-info", "name", "Alice");
        jedis.hset("rowkey-info", "sex", "Male");
        jedis.hset("rowkey-info", "age", "10");
    }
}
