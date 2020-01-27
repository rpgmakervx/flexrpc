package org.easyarch.flexrpc.core.rpc;

import java.util.concurrent.Callable;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-27
 *
 */
public interface RpcInvoker extends Callable<FullHttpResponse> {

    Object invoke(RpcRequest request);
}
