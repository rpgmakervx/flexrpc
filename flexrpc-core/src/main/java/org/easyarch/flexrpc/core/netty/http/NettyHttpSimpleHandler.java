package org.easyarch.flexrpc.core.netty.http;

import org.easyarch.flexrpc.core.netty.handler.FlexRequestDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-27
 * 处理请求的入口
 * 请求封装如下：
 * uri: 区分rpc server的功能，例如处理rpc调用，ping，获取rpc服务信息等
 * method: call默认POST, ping默认HEADER, 获取stat默认GET, 修改服务状态默认PUT
 *
 */
@Lazy
@Service
public class NettyHttpSimpleHandler extends ChannelInboundHandlerAdapter {

    protected static final Logger logger = LoggerFactory.getLogger(NettyHttpSimpleHandler.class);

    @Autowired
    private FlexRequestDispatcher dispatcher;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channel active, ctx:{}", ctx.channel().id());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channel inActive, ctx:{}", ctx.channel().id());

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("error happen", cause);
    }
}
