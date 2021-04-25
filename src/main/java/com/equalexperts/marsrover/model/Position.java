package com.equalexperts.marsrover.model;

import lombok.Value;

import java.text.MessageFormat;

@Value(staticConstructor = "of")
public class Position {
    int x;
    int y;
    Direction direction;

    @Override
    public String toString() {
        return MessageFormat.format("({0}, {1}) {2}", String.valueOf(this.x), String.valueOf(this.y), this.direction);
    }
}
