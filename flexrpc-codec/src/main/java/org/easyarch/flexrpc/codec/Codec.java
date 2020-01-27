package org.easyarch.flexrpc.codec;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-23
 * 编解码器，提供多种实现
 */
public interface Codec {

    <T> byte[] encode(T data);

    <T> T decode(byte[] data, Class<T> clazz);

}
