package com.equalexperts.marsrover.model;

import java.util.List;
import java.util.Stack;

public class Rover {
    private final Stack<Position> positionHistory;

    public Rover(Position initialPosition){
        this.positionHistory = new Stack<>();
        this.positionHistory.push(initialPosition);
    }

    public Position executeCommand(final List<Command> commands){
        commands.forEach(command -> {
            final Position current = this.positionHistory.peek();
            final Direction direction = current.getDirection();
            final int sign = command.getSign(); // Forward, Clockwise: +1 & Backward, Counter-Clockwise: -1

            Position newPos = command.isRotation()? Position.of(current.getX(), current.getY(), rotate(direction, sign)) :
                                                    Position.of(current.getX() + (sign * direction.getX()),
                                                            current.getY() + (sign * direction.getY()),
                                                            direction);
            this.positionHistory.push(newPos);
        });

        return this.positionHistory.peek();
    }

    public Direction rotate(Direction direction, int sign) {
        return Direction.values()[Math.floorMod(direction.ordinal() + sign, Direction.values().length)];
    }
}

