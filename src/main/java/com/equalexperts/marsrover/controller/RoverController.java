package com.equalexperts.marsrover.controller;

import com.equalexperts.marsrover.service.RoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoverController {
    private final RoverService service;

    @PostMapping(value = "/command", consumes = "text/plain", produces = "text/plain")
    public ResponseEntity<String> postCommand(@Valid @RequestBody String command){
        return ResponseEntity.ok(service.processCommand(command).toString());
    }
}
