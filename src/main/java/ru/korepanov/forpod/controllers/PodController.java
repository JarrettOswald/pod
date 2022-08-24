package ru.korepanov.forpod.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.korepanov.forpod.dto.Person;

import java.io.File;
import java.io.IOException;

@RestController
public class PodController {

    static final Logger LOGGER = LogManager.getLogger();

    private final PodControllerConfig config;
    private int count = 0;

    public PodController(PodControllerConfig config) {
        this.config = config;
    }

    @GetMapping(value = "/pod")
    public String getPerson() {
        return String.format("Hello from %s.%s", config.getName(), config.getVersion());
    }

    @GetMapping(value = "/bug")
    public ResponseEntity<String> getVersion() {
        count++;
        if (count >= 5) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            String responce = String.format("Hello from %s.v3\n", config.getName());
            return new ResponseEntity<>(responce, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/person")
    public ResponseEntity<String> saveFile(@RequestBody Person person) {
        LOGGER.info(person);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("/var/data", "person.json"), person);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(value = "/person")
    public ResponseEntity<Person> getFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person;
        try {
            person = objectMapper.readValue("/var/data/person.json", Person.class);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(new Person(null,null,"Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(person);
    }

}
