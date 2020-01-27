package org.easyarch.flexrpc.spring;

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.beans.factory.BeanFactoryUtils.beansOfTypeIncludingAncestors;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;

import org.easyarch.flexrpc.spring.config.FlexRpcConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-27
 * 封装spring bean相关的功能
 */
public class FlexBeanFactory {

    protected static final Logger logger = LoggerFactory.getLogger(FlexBeanFactory.class);

    private static volatile GenericApplicationContext applicationContext;

    private static volatile boolean initByBeanFactory = false;

    public static void init() {
        if (applicationContext == null) {
            synchronized (FlexBeanFactory.class) {
                if (applicationContext == null) {
                    if (initByBeanFactory) {
                        // 为了检测出bean里field有用getBean()声明的成员变量
                        // 但是并不能100%检测出，比如如果有一个static的变量，在spring扫到它之前就被引用并触发了getBean就没戏了
                        // 但是能检测出一点儿也比什么都检测不出来好。
                        throw new RuntimeException(
                                "found invalid spring declare. call beanfactory in bean field? first init stack:");
                    } else {
                        initByBeanFactory = true;
                    }
                    Charset defaultCharset = defaultCharset();
                    logger.info("init bean factory self. default charset:{}", defaultCharset);
                    if (!Objects.equals(defaultCharset, UTF_8)) {
                        logger.warn("FOUND INVALID DEFAULT CHARSET:{}", defaultCharset);
                    }
                    try {
                        applicationContext = new GenericApplicationContext(new AnnotationConfigApplicationContext(FlexRpcConfiguration.class));
                        applicationContext.refresh();
                    } catch (RuntimeException e) {
                        throw e;
                    }
                }
            }
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        init();
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String beanName, Class<T> tClass) {
        init();
        return applicationContext.getBean(beanName, tClass);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        init();
        logger.info("application context:{}", applicationContext);
        return beansOfTypeIncludingAncestors(applicationContext, type);
    }


}
