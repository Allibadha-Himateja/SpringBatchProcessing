package com.example.batch_processing.Models;

import java.util.HashMap;
import java.util.Map;

public class RecordModel {

    private final Map<AttributeKey<?>, Object> values = new HashMap<>();

    public <T> void put(AttributeKey<T> key, T value) {
        values.put(key, value);
    }

    public <T> T get(AttributeKey<T> key) {
        return key.type().cast(values.get(key));
    }

    public boolean contains(AttributeKey<?> key) {
        return values.containsKey(key);
    }
}
