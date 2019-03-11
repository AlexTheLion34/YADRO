package com.service.parser.helpers.enums;

public enum InfoParameters {

    MAC("HWaddr"),
    IPV4("inet"),
    IPV6("inet6"),
    MTU("MTU");

    private String name;

    InfoParameters(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
