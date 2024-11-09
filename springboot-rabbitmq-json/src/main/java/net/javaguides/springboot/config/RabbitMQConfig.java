package net.javaguides.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.jsonqueue.name}")
    private String jsonQueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;

    //Spring Bean for RabbitMQ JSON Queue
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    //Spring Bean for RabbitMQ Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }


    //Binding between Queue & Exchange using JSON Routing Key
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    //SpringBoot will automatically configures the below three for us
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
