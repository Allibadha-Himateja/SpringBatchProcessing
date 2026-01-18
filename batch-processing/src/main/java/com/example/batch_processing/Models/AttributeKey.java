package com.example.batch_processing.Models;

public final class AttributeKey<T> {

    private final String name;
    private final Class<T> type;

    private AttributeKey(String name, Class<T> type) {
        this.name = name;
        this.type = type;
    }

    public static <T> AttributeKey<T> of(String name, Class<T> type) {
        return new AttributeKey<>(name, type);
    }

    public String name() {
        return name;
    }

    public Class<T> type() {
        return type;
    }
}
