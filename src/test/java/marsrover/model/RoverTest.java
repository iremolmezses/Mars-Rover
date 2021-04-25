package marsrover.model;

import com.equalexperts.marsrover.model.Command;
import com.equalexperts.marsrover.model.Direction;
import com.equalexperts.marsrover.model.Position;
import com.equalexperts.marsrover.model.Rover;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(JUnitParamsRunner.class)
public class RoverTest {
    private Rover rover;

    @Before
    public void setUp(){
        rover = new Rover(Position.of(4, 2, Direction.EAST));
    }

    @Parameters
    public static List<Object[]> commandExecution() {
        return Arrays.asList(new Object[][] {
                // counter moves, no change
                {List.of(Command.F, Command.B), Position.of(4, 2, Direction.EAST)},
                {List.of(Command.B, Command.F), Position.of(4, 2, Direction.EAST)},
                {List.of(Command.R, Command.L), Position.of(4, 2, Direction.EAST)},
                {List.of(Command.L, Command.R), Position.of(4, 2, Direction.EAST)},
                // direction change
                {List.of(Command.R), Position.of(4, 2, Direction.SOUTH)},
                {List.of(Command.R, Command.R), Position.of(4, 2, Direction.WEST)},
                {List.of(Command.R, Command.R, Command.R), Position.of(4, 2, Direction.NORTH)},
                {List.of(Command.R, Command.R, Command.R, Command.R), Position.of(4, 2, Direction.EAST)},
                {List.of(Command.L), Position.of(4, 2, Direction.NORTH)},
                {List.of(Command.L, Command.L), Position.of(4, 2, Direction.WEST)},
                {List.of(Command.L, Command.L, Command.L), Position.of(4, 2, Direction.SOUTH)},
                {List.of(Command.L, Command.L, Command.L, Command.L), Position.of(4, 2, Direction.EAST)},
                // mixed
                {List.of(Command.F, Command.F, Command.F, Command.F, Command.L, Command.L), Position.of(8, 2, Direction.WEST)},
                {List.of(Command.B, Command.B, Command.B, Command.B, Command.R, Command.R), Position.of(0, 2, Direction.WEST)},
                {List.of(Command.F, Command.F, Command.R, Command.F, Command.F, Command.F, Command.F), Position.of(6, -2, Direction.SOUTH)},
                {List.of(Command.B, Command.B, Command.B, Command.B, Command.B, Command.B, Command.L), Position.of(-2, 2, Direction.NORTH)},
                // the case from the assignment description
                {List.of(Command.F, Command.L, Command.F, Command.F, Command.F, Command.R, Command.F, Command.L, Command.B), Position.of(6, 4, Direction.NORTH) }
        });
    }

    @Parameters
    public static List<Object[]> directionChange() {
        return Arrays.asList(new Object[][] {
                { Direction.EAST, 1, Direction.SOUTH },
                { Direction.EAST, -1, Direction.NORTH },
                { Direction.WEST, 1, Direction.NORTH },
                { Direction.WEST, -1, Direction.SOUTH },
                { Direction.SOUTH, 1, Direction.WEST },
                { Direction.SOUTH, -1, Direction.EAST },
                { Direction.NORTH, 1, Direction.EAST },
                { Direction.NORTH, -1, Direction.WEST },
        });
    }

    @Test
    @Parameters(method = "directionChange")
    public void changeDirection(Direction initialDirection, int sign, Direction expectedDirection){
        Direction result = rover.rotate(initialDirection, sign);
        assertEquals(expectedDirection, result);
    }

    @Test
    @Parameters(method = "commandExecution")
    public void executeCommand(List<Command> commands, Position expectedPosition){
        Position result = rover.executeCommand(commands);
        assertEquals(expectedPosition, result);
    }
}
