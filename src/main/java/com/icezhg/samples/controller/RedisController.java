package com.icezhg.samples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2019/06/12.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test")
    public Object getTest() {
        return redisTemplate.opsForValue().get("test");
    }

    @PostMapping("/test")
    public String setTest() {
        redisTemplate.opsForValue().set("test", "this is a test");
        return "success";
    }
}
