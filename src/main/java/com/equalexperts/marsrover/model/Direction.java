package com.equalexperts.marsrover.model;

import lombok.Getter;

@Getter
public enum Direction {
    EAST(1, 0),   //positive side of the x-axis
    SOUTH(0, -1), //negative side of the y-axis
    WEST(-1, 0),  //negative side of the x-axis
    NORTH(0, 1);  //positive side of the y-axis

    private final int x, y;

    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }
}
