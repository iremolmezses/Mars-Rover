package com.equalexperts.marsrover.service;

import com.equalexperts.marsrover.model.Command;
import com.equalexperts.marsrover.model.Position;
import com.equalexperts.marsrover.model.Rover;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoverService {
    private final Rover rover;

    public Position processCommand(final String command) {
        if(command == null || command.isBlank()){
            throw new IllegalArgumentException("Command can not be null or empty");
        }
        List<Command> commandList = command.toUpperCase().chars()
                                                         .mapToObj(c -> Command.valueOf(String.valueOf((char) c)))
                                                         .collect(Collectors.toList());
        return rover.executeCommand(commandList);
    }
}
