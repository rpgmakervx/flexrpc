package org.easyarch.flexrpc.core.netty.handler;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-27
 *
 */
public interface AbstractHttpHandler {

    String endpoint();

    FullHttpResponse handle(FullHttpRequest request);
}
