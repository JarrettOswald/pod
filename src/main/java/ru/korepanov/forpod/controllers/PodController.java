package ru.korepanov.forpod.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PodController {

    private final PodControllerConfig config;

    public PodController(PodControllerConfig config) {
        this.config = config;
    }

    @GetMapping(value = "/pod")
    public String getPerson() {
        return String.format("Hello from %s.%s", config.getName(), config.getVersion());
    }

    @GetMapping(value = "/version")
    public String getVersion() {
        return String.format("Hello from %s.v2\n", config.getName());
    }

}
