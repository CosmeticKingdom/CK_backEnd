package com.ck.backend.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Rating {
    ZERO(0.0),
    HALF(0.5),
    ONE(1.0),
    ONE_AND_HALF(1.5),
    TWO(2.0),
    TWO_AND_HALF(2.5),
    THREE(3.0),
    THREE_AND_HALF(3.5),
    FOUR(4.0),
    FOUR_AND_HALF(4.5),
    FIVE(5.0);

    @JsonValue
    private final double value;

    Rating(double value) {
        this.value = value;
    }

    @JsonCreator
    public static Rating fromString(String symbol) {
        return Stream.of(values())
                .filter(r -> r.name().equalsIgnoreCase(symbol))
                .findFirst()
                .orElse(null);
    }
}
