package ru.korepanov.forpod.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("pod")
public class PodControllerConfig {
    private String message;
    private String version;
    private String name;
}


