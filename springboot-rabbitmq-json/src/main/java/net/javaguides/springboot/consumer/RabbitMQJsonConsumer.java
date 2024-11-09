package net.javaguides.springboot.consumer;

import lombok.extern.log4j.Log4j2;
import net.javaguides.springboot.dto.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = {"${rabbitmq.jsonqueue.name}"})
    public void consumeJsonMessage(User user) {
        // Handle the received message here
        log.info(String.format("Received Json message -> %s", user.toString()));
    }
}
