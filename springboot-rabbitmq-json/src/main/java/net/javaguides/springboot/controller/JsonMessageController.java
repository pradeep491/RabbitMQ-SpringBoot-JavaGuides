package net.javaguides.springboot.controller;

import net.javaguides.springboot.dto.User;
import net.javaguides.springboot.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {
    private final RabbitMQJsonProducer producer;

    public JsonMessageController(RabbitMQJsonProducer producer) {
        this.producer = producer;
    }

    //http://localhost:8081/api/v1/publish
    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        producer.sendJsonMessage(user);
        return ResponseEntity.ok("JSON Message Sent to RabbitMQ...!");
    }
}
