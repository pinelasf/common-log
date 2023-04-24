package com.example.commonlog;

import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

class LoggerImpl implements Logger {

    private final org.slf4j.Logger logger;

    private static final String PREFIX_METHOD = "Method {} | ";

    private static final long NR_LAYERS = 4;

    public LoggerImpl(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    /*@Override
    public void trace(String message, Object... params) {

    }

    @Override
    public void trace(IdType idType, Object id, String message, Object... params) {
        oneIdType(LogLevel.TRACE, idType, id, message, params);
    }

    @Override
    public void debug(String message, Object... params) {

    }

    @Override
    public void debug(IdType idType, Object id, String message, Object... params) {
        oneIdType(LogLevel.DEBUG, idType, id, message, params);
    }*/

    @Override
    public void info(String message, Object... params) {

    }

    @Override
    public void info(LogRef logRef, String message, Object... params) {
        oneIdType(LogLevel.INFO, logRef, message, params);
    }

    /*@Override
    public void warn(String message, Object... params) {

    }

    @Override
    public void warn(IdType idType, Object id, String message, Object... params) {
        oneIdType(LogLevel.WARN, idType, id, message, params);
    }

    @Override
    public void error(String message, Object... params) {

    }

    @Override
    public void error(IdType idType, Object id, String message, Object... params) {
        oneIdType(LogLevel.ERROR, idType, id, message, params);
    }*/

    private void oneIdType(LogLevel logLevel, LogRef logRef, String message, Object... params) {
        TreeMap<Integer, LogRef> orderedLogRef = new TreeMap<>();

        orderedLogRef.put(logRef.getPriority(), logRef);

        Map<String, Object[]> logMessageParams = buildLogMessageParams(orderedLogRef, params, message);

        Map.Entry<String, Object[]> entry = logMessageParams.entrySet().iterator().next();

        logMessage(logLevel, entry.getKey(), entry.getValue());
    }

    private Map<String, Object[]> buildLogMessageParams(TreeMap<Integer, LogRef> orderedLogRef, Object[] params, String message) {
        if (orderedLogRef == null) orderedLogRef = new TreeMap<>();

        int baseParametersSize = orderedLogRef.size() + 1;

        Object[] logParams = new Object[params.length + baseParametersSize];
        StringBuilder logString = new StringBuilder();

        int i = 0;
        for (LogRef logRef : orderedLogRef.values()) {
            logParams[i] = logRef.getValue();
            logString.append(logRef.getMessage());
            i++;
        }

        logParams[i] = getCallerMethodName();
        logString.append(PREFIX_METHOD);

        System.arraycopy(params, 0, logParams, baseParametersSize, params.length);
        logString.append(message);

        return new HashMap<>() {{
            put(logString.toString(), logParams);
        }};
    }

    private String getCallerMethodName() {
        StackWalker stackWalker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

        StackWalker.StackFrame frame = stackWalker
                .walk(stackFrameStream -> stackFrameStream.skip(NR_LAYERS).findFirst().orElse(null));

        if (frame != null) return frame.getMethodName();
        return "Not found";
    }

    private void logMessage(LogLevel logLevel, String message, Object[] logParams) {
        switch (logLevel) {
            case TRACE:
                logger.trace(message, logParams);
                break;
            case DEBUG:
                logger.debug(message, logParams);
                break;
            case INFO:
                logger.info(message, logParams);
                break;
            case WARN:
                logger.warn(message, logParams);
                break;
            case ERROR:
                logger.error(message, logParams);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
