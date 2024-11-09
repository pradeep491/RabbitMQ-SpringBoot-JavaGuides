package net.javaguides.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    //Spring Bean for RabbitMQ Queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    //Spring Bean for RabbitMQ Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    //Binding between Queue & Exchange using Routing Key
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey)
                ;
    }

    //Binding between Queue & Exchange using Routing Key
   /* @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }*/

    //SpringBoot will automatically configures the below three for us
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
