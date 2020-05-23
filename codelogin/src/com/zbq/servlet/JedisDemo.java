package com.zbq.servlet;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {
    @Test
    public void test1(){
        //���ʹ�ñ������ݿ�  ���Կղ�
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("username", "123");
        System.out.println(jedis.get("username"));
        //����ʹ��setex()�����洢����ָ������ʱ���key��value
        jedis.setex("password", 10, "1234");
        jedis.close();
    }

    /**
     * hash���ݽṹ
     */
    @Test
    public void test2(){
        //���ʹ�ñ������ݿ�  ���Կղ�
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.hset("user", "name", "lisi");
        jedis.del("username");
        System.out.println(jedis.hget("user", "name"));

        jedis.close();
    }
    @Test
    public void Test3(){
        //�������ӳ���Ϣ
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        //�������ӳض���
        JedisPool jedisPool = new JedisPool();
        //��ȡ����
        Jedis jedis = jedisPool.getResource();

    }
}
