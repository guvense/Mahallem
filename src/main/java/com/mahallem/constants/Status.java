package com.mahallem.constants;

public enum Status {
    DELETED(-1),
    PASSIVE(0),
    ACTIVE(1);

    private final int id;
    Status(int id) { this.id = id; }
    public int getValue() { return id; }
}
