package org.easyarch.flexrpc.test;

import org.easyarch.flexrpc.core.netty.handler.AbstractHttpHandler;
import org.easyarch.flexrpc.core.netty.handler.FlexRequestDispatcher;
import org.easyarch.flexrpc.core.netty.handler.HttpEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-27
 *
 */
public class TestSpring {

    protected static final Logger logger = LoggerFactory.getLogger(TestSpring.class);

    public static void main(String[] args) {
        FlexRequestDispatcher dispatcher = new FlexRequestDispatcher();
        AbstractHttpHandler handler = dispatcher.findHandler(HttpEndPoint.CALL);
        logger.info("find a handler:{}", handler);
    }
}
