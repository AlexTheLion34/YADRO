package com.client.commands.enums;

public enum Commands {

    HELP("help"),
    LIST("list"),
    SHOW("show");

    private final String name;

    Commands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
