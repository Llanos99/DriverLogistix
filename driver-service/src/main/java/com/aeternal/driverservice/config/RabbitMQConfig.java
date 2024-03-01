package com.aeternal.driverservice.config;

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

    @Value("${rabbit.queue.name}")
    private String queueName;

    @Value("${rabbit.exchange.name}")
    private String queueExchange;

    @Value("${rabbit.routing.key}")
    private String queueRoutingKey;

    /* Queue configuration for observer design pattern */

    @Value("${rabbit.subject-queue.name}")
    private String subjectQueueName;

    @Value("${rabbit.subject-exchange.name}")
    private String subjectQueueExchange;

    @Value("${rabbit.subject-routing.key}")
    private String subjectQueueRoutingKey;

    @Bean("LOG-QUEUE")
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean("LOG-EXCHANGE")
    public TopicExchange queueExchange() {
        return new TopicExchange(queueExchange);
    }

    @Bean("LOG-BINDING")
    public Binding queueBinding() {
        return BindingBuilder
                .bind(queue())
                .to(queueExchange())
                .with(queueRoutingKey);
    }

    @Bean("SUBJECT-QUEUE")
    public Queue subjectQueue() {
        return new Queue(subjectQueueName);
    }

    @Bean("SUBJECT-EXCHANGE")
    public TopicExchange subjectQueueExchange() {
        return new TopicExchange(subjectQueueExchange);
    }

    @Bean("SUBJECT-BINDING")
    public Binding subjectQueueBinding() {
        return BindingBuilder
                .bind(subjectQueue())
                .to(subjectQueueExchange())
                .with(subjectQueueRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        rabbitTemplate.setReplyAddress(null);
        return rabbitTemplate;
    }

}
