package com.restfulapi.restfulapi.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class RedisUtil {
    private final RedisTemplate myRedisTemplate;

    //Data type : String
    public void setString(String key, String value) {
        myRedisTemplate.opsForValue().set(key, value);
    }

    public String getString(String key) {
        return myRedisTemplate.opsForValue().get(key).toString();
    }

    //Timer
    public void setString(String key, String value, long timer, TimeUnit time) {
        myRedisTemplate.opsForValue().set(key, value,timer, time);
    }


    //Data Type : List
    public void setListLeft(String key, String value) {
        myRedisTemplate.opsForList().leftPush(key, value);
    }

    public void setListRight(String key, String value) {
        myRedisTemplate.opsForList().rightPush(key, value);
    }

    public String getListLeftPop(String key) {
        return myRedisTemplate.opsForList().leftPop(key).toString();
    }

    public String getListRightPop(String key) {
        return myRedisTemplate.opsForList().rightPop(key).toString();
    }

    public long getListSize(String key) {
        return myRedisTemplate.opsForList().size(key);
    }

    public List<String> getListStartFromEnd(String key, long start, long end) {
        return myRedisTemplate.opsForList().range(key, start, end);
    }
}
