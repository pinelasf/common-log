package com.example.commonlog;

public class CaseRef implements LogRef {

    private final int value;

    public static CaseRef CASE(int value) {
        return new CaseRef(value);
    }

    public CaseRef(int value) {
        this.value = value;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getMessage() {
        return "Case ID {} | ";
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
