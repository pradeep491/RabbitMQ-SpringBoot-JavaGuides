package net.javaguides.springboot.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitMQConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(String message) {
        // Handle the received message here
        log.info(String.format("Received message -> %s", message));
    }
}
