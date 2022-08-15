package ru.korepanov.forpod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.korepanov.forpod.controllers.PodControllerConfig;

@SpringBootApplication
@Import(PodControllerConfig.class)
public class ForpodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForpodApplication.class, args);
    }
}
