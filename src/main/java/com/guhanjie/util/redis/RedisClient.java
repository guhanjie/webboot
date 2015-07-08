package com.guhanjie.util.redis;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisClient {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
	@Autowired
    private JedisPool pool;
 
    public String put(String key, String value) {
        return put(key, value, -1);
    }
 
    /**
     * 
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public String put(String key, String value, int expireSeconds) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        String oldValue = null;
        final Jedis jedis = pool.getResource();
        try {
            if (jedis.exists(key)) {
                oldValue = jedis.get(key);
            }
            if (expireSeconds > -1) {
                jedis.setex(key, expireSeconds, value);
            } else {
                jedis.set(key, value);
            }
        } finally {
        	jedis.close();
        }
        return oldValue;
    }
 
    public String get(String key) {
        String value = null;
        final Jedis jedis = pool.getResource();
        try {
            if (jedis.exists(key)) {
                value = jedis.get(key);
            }
        } finally {
        	jedis.close();
        }
        return value;
    }
 
    public void remove(String... keys) {
        final Jedis jedis = pool.getResource();
        try {
            jedis.del(keys);
        } finally {
        	jedis.close();
        }
    }
 
    public Boolean containsKey(String key) {
        Boolean b = null;
        final Jedis jedis = pool.getResource();
        try {
            b = jedis.exists(key);
        } finally {
        	jedis.close();
        }
        return b;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }
   
    public KeyValueEntity saveKeyValueEntity(KeyValueEntity entity){
    	saveKeyValueEntity(entity, -1);
		return entity;
    }

    public KeyValueEntity saveKeyValueEntity(KeyValueEntity entity,int expireSeconds){
		String value = JSON.toJSONString(entity);
		this.put(entity.getKey(), value, expireSeconds);
		return entity;
    }
    
    public KeyValueEntity getKeyValueEntity(String key){
    	String json = this.get(key);
		if(StringUtils.isNotBlank(json)){
			try {
				KeyValueEntity dto = JSON.parseObject(json, KeyValueEntity.class);
				return dto;
			} catch (Exception e) {
				LOGGER.error("to json error:" + json);
			}
			return null;
		}
		return null;
    }
}
