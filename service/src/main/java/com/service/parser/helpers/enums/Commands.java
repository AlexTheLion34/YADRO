package com.service.parser.helpers.enums;

public enum Commands {

    RETURN_NAMES("ifconfig -s"),
    RETURN_INFO("ifconfig ");

    private String name;

    Commands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
