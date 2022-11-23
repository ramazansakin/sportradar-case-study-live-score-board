package com.rsakin.sportradar.casestudy;

public enum CommandType {
    START("start"),
    UPDATE("update"),
    FINISH("finish"),
    SUMMARY("summary"),
    EXIT("exit");

    private String type;

    CommandType(String type) {
        this.type = type;
    }
}
