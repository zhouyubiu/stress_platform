package com.pressure.test.login.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @NAME: RedisPool
 * @USER: zhouyu
 * @DATE: 2020/12/6
 */
public class RedisPool {
    private static JedisPool jedisPool;
    private static Integer maxTotal=20;
    private static Integer maxIdle=10;
    private static Integer minIde=2;
    private static Boolean testOnBorro=true;
    private static Boolean testonRetuen=false;

    private static String redisIp="127.0.0.1";
    private static Integer redisport=6379;


    public static void initPool(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIde);
        config.setTestOnBorrow(testOnBorro);
        config.setTestOnReturn(testonRetuen);

        config.setBlockWhenExhausted(true);
        jedisPool=new JedisPool(config,redisIp,redisport,1000*2);
    }

    static{
        initPool();
    }


    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void returnResource(Jedis jedis){
        jedisPool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis){
        jedisPool.returnBrokenResource(jedis);
    }

//    public static void main(String[] args) {
//        Jedis jedis=jedisPool.getResource();
//        jedis.set("1","2");
//        jedisPool.destroy();
//    }


}
