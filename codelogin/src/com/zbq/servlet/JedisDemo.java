package com.zbq.servlet;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {
    @Test
    public void test1(){
        //如果使用本地数据库  可以空参
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("username", "123");
        System.out.println(jedis.get("username"));
        //可以使用setex()方法存储可以指定过期时间的key，value
        jedis.setex("password", 10, "1234");
        jedis.close();
    }

    /**
     * hash数据结构
     */
    @Test
    public void test2(){
        //如果使用本地数据库  可以空参
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.hset("user", "name", "lisi");
        jedis.del("username");
        System.out.println(jedis.hget("user", "name"));

        jedis.close();
    }
    @Test
    public void Test3(){
        //配置连接池信息
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        //创建连接池对象
        JedisPool jedisPool = new JedisPool();
        //获取连接
        Jedis jedis = jedisPool.getResource();

    }
}
