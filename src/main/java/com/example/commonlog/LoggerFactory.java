package com.example.commonlog;

public class LoggerFactory {

    public static Logger getLogger(Class<?> clazz) {
        return new LoggerImpl(clazz);
    }

}
