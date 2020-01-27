package org.easyarch.flexrpc.core.netty;

import org.easyarch.flexrpc.core.netty.http.NettyHttpSimpleHandler;
import org.easyarch.flexrpc.core.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-27
 * netty channel initializer
 */
@Lazy
@Service
public class BaseChildHandler extends ChannelInitializer<SocketChannel> {

    private RpcContext context;

    @Autowired
    private NettyHttpSimpleHandler dispatcherHandler;

    public BaseChildHandler(RpcContext ctx) {
        this.context = ctx;
    }

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpContentCompressor(9));
        pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        pipeline.addLast(new HttpContentDecompressor());
    }
}
