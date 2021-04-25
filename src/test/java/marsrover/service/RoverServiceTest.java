package marsrover.service;

import com.equalexperts.marsrover.model.Command;
import com.equalexperts.marsrover.model.Direction;
import com.equalexperts.marsrover.model.Position;
import com.equalexperts.marsrover.model.Rover;
import com.equalexperts.marsrover.service.RoverService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoverServiceTest {
    @Mock
    private Rover rover;

    @InjectMocks
    private RoverService service;

    @Test
    public void processCommand_returnsPosition_givenValidInput(){
        when(rover.executeCommand(List.of(Command.F, Command.B, Command.R, Command.L))).thenReturn(Position.of(4, 2, Direction.EAST));

        Position result = service.processCommand("FBRL");

        assertEquals(Position.of(4, 2, Direction.EAST), result);
    }

    @Test
    public void processCommand_returnsPosition_givenValidInputInLowerCase(){
        when(rover.executeCommand(List.of(Command.F, Command.B, Command.R, Command.L))).thenReturn(Position.of(4, 2, Direction.EAST));

        Position result = service.processCommand("fbrl");

        assertEquals(Position.of(4, 2, Direction.EAST), result);
    }

    @Test
    public void processCommand_throwsIllegalArgumentException_givenNullInput(){
        assertThrows(IllegalArgumentException.class, () -> service.processCommand(null));
    }

    @Test
    public void processCommand_throwsIllegalArgumentException_givenEmptyInput(){
        assertThrows(IllegalArgumentException.class, () -> service.processCommand(""));
    }

    @Test
    public void processCommand_throwsIllegalArgumentException_givenUnsupportedCommand(){
        assertThrows(IllegalArgumentException.class, () -> service.processCommand("TEST"));
    }
}
