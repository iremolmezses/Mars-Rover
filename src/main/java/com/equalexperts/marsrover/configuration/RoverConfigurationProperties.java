package com.equalexperts.marsrover.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties
@ConstructorBinding
@Component
public class RoverConfigurationProperties {
    private int xPosition;
    private int yPosition;
    private String direction;
}
