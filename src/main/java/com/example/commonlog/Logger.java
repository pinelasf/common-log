package com.example.commonlog;

public interface Logger {

    void trace(String message, Object... params);

    void trace(IdType idType, Object id, String message, Object... params);

    void debug(String message, Object... params);

    void debug(IdType idType, Object id, String message, Object... params);

    void info(String message, Object... params);

    void info(IdType idType, Object id, String message, Object... params);

    void warn(String message, Object... params);

    void warn(IdType idType, Object id, String message, Object... params);

    void error(String message, Object... params);

    void error(IdType idType, Object id, String message, Object... params);

}
