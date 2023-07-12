package com.renewtrak.glossary.message;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MessageUtil implements InitializingBean {

    @Autowired
    private Environment env;

    private static MessageUtil instance;

    public String getMessage(String code) {
        return env.getProperty(code);
    }

    @Override
    public void afterPropertiesSet() {
        instance = this;
    }

    public static MessageUtil get() {
        return instance;
    }
}