package org.easyarch.flexrpc.core.netty.handler;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-27
 * http请求endpoint定义
 */
public interface HttpEndPoint {

    String CALL = "/rest/call";

    String PING = "/rest/ping";

    String STAT = "/rest/stat";

}
