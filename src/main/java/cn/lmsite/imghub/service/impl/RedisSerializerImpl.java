package cn.lmsite.imghub.service.impl;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisSerializerImpl implements RedisSerializer {
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return null;
    }

    @Override
    public Class<?> getTargetType() {
        return null;
    }

    @Override
    public boolean canSerialize(Class type) {
        return false;
    }
}
