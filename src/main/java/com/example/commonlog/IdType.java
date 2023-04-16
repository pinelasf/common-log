package com.example.commonlog;

public enum IdType {
    CASE_ID("Case ID {} | ");

    private String prefix;
    private Object id;

    IdType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }
}
