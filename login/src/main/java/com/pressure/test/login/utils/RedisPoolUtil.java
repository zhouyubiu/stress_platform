package com.pressure.test.login.utils;

import com.pressure.test.login.common.RedisPool;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;

/**
 * @NAME: RedisPollUtil
 * @USER: zhouyu
 * @DATE: 2020/12/7
 */

@Log4j2
public class RedisPoolUtil {
    public static String set(String key,String val){
        Jedis jedis=null;
        String res=null;
        try{
            jedis = RedisPool.getJedis();
            res = jedis.set(key,val);
        }catch (Exception e){
            log.error("set key:{} val:{} error{}",key,val,e);
            RedisPool.returnBrokenResource(jedis);
        }
        RedisPool.returnResource(jedis);
        return res;

    }

    public static String setEx(String key,String val,int exTime){
        Jedis jedis=null;
        String res=null;
        try{
            jedis = RedisPool.getJedis();
            res = jedis.setex(key,exTime,val);
        }catch (Exception e){
            log.error("setEx key:{} val:{} exTime{} error{}",key,val,exTime,e);
            RedisPool.returnBrokenResource(jedis);
        }
        RedisPool.returnResource(jedis);
        return res;

    }

    public static Long expire(String key,int exTime){
        Jedis jedis=null;
        Long res=null;
        try{
            jedis = RedisPool.getJedis();
            res = jedis.expire(key,exTime);
        }catch (Exception e){
            log.error("expire key:{}  exTime{} error{}",key, exTime,e);
            RedisPool.returnBrokenResource(jedis);
        }
        RedisPool.returnResource(jedis);
        return res;

    }

    public static String get(String key){
        Jedis jedis=null;
        String res=null;
        try{
            jedis = RedisPool.getJedis();
            res = jedis.get(key);
        }catch (Exception e){
            log.error("get key:{} error{}",key,e);
            RedisPool.returnBrokenResource(jedis);
        }
        RedisPool.returnResource(jedis);
        return res;

    }

    public static Long del(String key){
        Jedis jedis=null;
        Long res=null;
        try{
            jedis = RedisPool.getJedis();
            res = jedis.del(key);
        }catch (Exception e){
            log.error("del key:{} error{}",key,e);
            RedisPool.returnBrokenResource(jedis);
        }
        RedisPool.returnResource(jedis);
        return res;

    }

    public static void main(String[] args) {
        Jedis jedis=RedisPool.getJedis();
        RedisPoolUtil.set("1231231","777777");
    }

}
