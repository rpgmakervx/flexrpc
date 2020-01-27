package org.easyarch.flexrpc.core.netty.handler;

import java.util.Map;
import java.util.stream.Collectors;

import org.easyarch.flexrpc.spring.FlexBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-27
 *
 */
@Lazy
@Service
public class FlexRequestDispatcher {

    protected static final Logger logger = LoggerFactory.getLogger(FlexRequestDispatcher.class);

    private static final Map<String, AbstractHttpHandler> HANDLER_MAP = FlexBeanFactory
            .getBeansOfType(AbstractHttpHandler.class)
            .values()
            .stream()
            .collect(Collectors.toMap(handler -> handler.endpoint(), handler -> handler));

    public AbstractHttpHandler findHandler(String endpoint) {
        AbstractHttpHandler handler = HANDLER_MAP.get(endpoint);
        logger.info("handler map:{}", HANDLER_MAP);
        if (handler != null) {
            return handler;
        }
        throw new IllegalArgumentException("endpoint not found, endpoint:" + endpoint);
    }

}
