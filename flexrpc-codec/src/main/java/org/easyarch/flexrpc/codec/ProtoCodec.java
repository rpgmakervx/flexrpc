package org.easyarch.flexrpc.codec;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableSet;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeEnv;
import io.protostuff.runtime.RuntimeSchema;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-23
 * protobuf序列化
 */
@Lazy
@Service
public class ProtoCodec implements Codec {

    protected static final Logger logger = LoggerFactory.getLogger(ProtoCodec.class);

    private static LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap();

    private <T> Schema<T> getSchema(Class<T> cls, Set<String> exclutions) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            if (exclutions == null) {
                exclutions = new HashSet<>();
            }
            schema = RuntimeSchema.createFrom(cls, exclutions, RuntimeEnv.ID_STRATEGY);
            cachedSchema.put(cls, schema);
        }
        return schema;
    }


    @Override
    public <T> byte[] encode(T data) {
        Class<T> clazz = (Class<T>) data.getClass();
        Schema<T> schema = getSchema(clazz, ImmutableSet.of());
        logger.info("encode schema:{}, class:{}", schema, clazz);
        try {
            return ProtostuffIOUtil.toByteArray(data, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public <T> T decode(byte[] data, Class<T> clazz) {
        Schema<T> schema = getSchema(clazz, ImmutableSet.of());
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, obj, schema);
        return obj;
    }
}
