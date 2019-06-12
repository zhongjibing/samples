package com.icezhg.samples.test;

import java.util.Arrays;
import java.util.Collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhongjibing on 2019/06/12.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void testSet() {
        redisTemplate.opsForValue().set("test", 1000);
    }

    @Test
    public void testGet() {
        Object value = redisTemplate.opsForValue().get("test");
        log.info("redis.get.{}={}", "test", value);
    }

    @Test
    public void testIncr() {
        Long value = redisTemplate.opsForValue().increment("test");
        log.info("redis.incr.{}={}", "test", value);
    }

    @Test
    public void testDecrby() {
        Long value = redisTemplate.opsForValue().decrement("test", 99);
        log.info("redis.decrby.{}={}", "test", value);
    }

    @Test
    public void testLuaDecrby() {
        String script = "if ((tonumber(redis.call('get', KEYS[1])) or 0) >= tonumber(ARGV[1])) then redis.call('decrby', KEYS[1], ARGV[1]); return 1; end; return 0; ";
        Boolean result = redisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Collections.singletonList("test"), 100);
        log.info("redis.luaDecrby.{}={}", "test", result);
    }

    @Test
    public void testHset() {
        redisTemplate.opsForHash().put("test_hash", "stock", 1000);
    }

    @Test
    public void testHget() {
        Object value = redisTemplate.opsForHash().get("test_hash", "stock");
        log.info("redis.hget.resultType:{}", value != null ? value.getClass().getSimpleName() : null);
        log.info("redis.hget.{}.{}={}", "test_hash", "stock", value);
    }

    @Test
    public void testHincrby() {
        Long value = redisTemplate.opsForHash().increment("test_hash", "stock", 100);
        log.info("redis.hincrby.{}.{}={}", "test_hash", "stock", value);
    }

    @Test
    public void testHdecrby() {
        Long value = redisTemplate.opsForHash().increment("test_hash", "stock", -200);
        log.info("redis.hdecrby.{}.{}={}", "test_hash", "stock", value);
    }

    @Test
    public void testLuaHincrby() {
        String script = "if ((tonumber(redis.call('hget', KEYS[1], KEYS[2])) or 0) >= -tonumber(ARGV[1])) then redis.call('hincrby', KEYS[1], KEYS[2], ARGV[1]); return 1; end; return 0;";
        Boolean result = redisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Arrays.asList("test_hash", "stock"), -600);
        log.info("redis.luaHincrby.{}.{}={}", "test_hash", "stock", result);
    }
}
