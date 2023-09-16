package com.shinhan.changyo.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final ValueOperations<String, String> valueOperations;

    public RedisRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    public Boolean save(String key, String value, int timeout) {
        valueOperations.set(key, value);
        return redisTemplate.expire(key, timeout, TimeUnit.MINUTES);
    }

    public Boolean expire(String key) {
        return redisTemplate.expire(key, 0, TimeUnit.SECONDS);
    }

    public String findByKey(String key) {
        return valueOperations.get(key);
    }
}
