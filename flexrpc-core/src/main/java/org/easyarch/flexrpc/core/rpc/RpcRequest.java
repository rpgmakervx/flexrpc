package org.easyarch.flexrpc.core.rpc;

import java.util.List;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-27
 * rpc 请求体
 */
public class RpcRequest {

    /**
     * 请求id，客户端设置，用来做链路追踪
     */
    private String requestId;

    /**
     * 服务名，对应server侧的beanName
     */
    private String service;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数列表
     */
    private List<Object> args;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }
}
