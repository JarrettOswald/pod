package ru.korepanov.forpod.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PodController {

    private static int count = 0;
    private final PodControllerConfig config;

    public PodController(PodControllerConfig config) {
        this.config = config;
    }

    @GetMapping(value = "/pod")
    public String getPerson() {
        return String.format("Hello from %s.%s", config.getName(), config.getVersion());
    }

    @GetMapping(value = "/version")
    public ResponseEntity<String> getVersion() {
        count++;
        if (count >= 5) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            String responce = String.format("Hello from %s.v3\n", config.getName());
            return new ResponseEntity<>(responce, HttpStatus.OK);
        }
    }

}
