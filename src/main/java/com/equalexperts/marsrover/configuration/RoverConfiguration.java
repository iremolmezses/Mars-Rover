package com.equalexperts.marsrover.configuration;

import com.equalexperts.marsrover.model.Direction;
import com.equalexperts.marsrover.model.Position;
import com.equalexperts.marsrover.model.Rover;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RoverConfiguration {
    private final RoverConfigurationProperties roverConfigurationProperties;

    @Bean
    public Rover landRover(){
        return new Rover(Position.of(roverConfigurationProperties.getXPosition(),
                                     roverConfigurationProperties.getYPosition(),
                                     Direction.valueOf(roverConfigurationProperties.getDirection().toUpperCase())));
    }
}
