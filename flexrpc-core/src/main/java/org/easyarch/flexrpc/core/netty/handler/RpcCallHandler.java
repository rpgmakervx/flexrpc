package org.easyarch.flexrpc.core.netty.handler;

import org.easyarch.flexrpc.codec.ProtoCodec;
import org.easyarch.flexrpc.core.rpc.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-27
 *
 */
@Lazy
@Service
public class RpcCallHandler implements AbstractHttpHandler {

    protected static final Logger logger = LoggerFactory.getLogger(RpcCallHandler.class);

    @Autowired
    private ProtoCodec codec;

    public String endpoint() {
        return HttpEndPoint.CALL;
    }

    public FullHttpResponse handle(FullHttpRequest request) {
        String uri = request.uri();
        HttpMethod method = request.method();
        logger.info("request uri:{}, method:{}, contentLength:{}", uri, method.name(),
                request.content().readableBytes());
        ByteBuf buffer = request.content();
        byte[] content = buffer.array();
        RpcRequest rpcRequest = codec.decode(content, RpcRequest.class);

        return null;
    }
}
