package com.equalexperts.marsrover.model;

import lombok.Getter;

import java.util.EnumSet;

@Getter
public enum Command {
    F(1),
    B(-1),
    R(1),
    L(-1);

    // The sign (negative/positive) of the movement must be clear enough for forward & backward moves
    // For rotation case: clockwise rotation is considered positive, counter-clockwise considered negative
    private final int sign;

    Command(int sign){
        this.sign = sign;
    }

    public boolean isRotation(){
        return EnumSet.of(L, R).contains(this);
    }
}
