package org.easyarch.flexrpc.test;

import org.easyarch.flexrpc.codec.ProtoCodec;
import org.easyarch.flexrpc.spring.config.FlexRpcConfiguration;
import org.easyarch.flexrpc.test.bean.MyBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 认真负责地对待每行代码
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-01-26
 *
 */
public class TestMain {

    protected static final Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(FlexRpcConfiguration.class);
        ProtoCodec codec = (ProtoCodec) context.getBean("protoCodec");
        MyBean bean = new MyBean();
        bean.setId(10000L);
        bean.setName("xingtianyu");
        byte[] data = codec.encode(bean);
        logger.info("byte size:{}", data.length);
        MyBean newBean = (MyBean) codec.decode(data, MyBean.class);
        logger.info("bean info:{}", newBean);
    }
}
