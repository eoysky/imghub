package cn.lmsite.imghub.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

@Service
public class RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value, Long expiration) {
        RedisSerializer redisSerializer =new StringRedisSerializer();
        //更改在redis里面查看key编码问题
        redisTemplate.setKeySerializer(redisSerializer);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, Duration.ofHours(expiration));
    }

    public Object get(String key) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }
}
