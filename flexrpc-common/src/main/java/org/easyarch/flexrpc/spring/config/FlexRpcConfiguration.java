package org.easyarch.flexrpc.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-26
 * spring配置类
 */
@Configuration
@ComponentScan(basePackages = "org.easyarch.flexrpc")
public class FlexRpcConfiguration {

    protected static final Logger logger = LoggerFactory.getLogger(FlexRpcConfiguration.class);

    public FlexRpcConfiguration() {
        logger.info("configuration class init");
    }

}
