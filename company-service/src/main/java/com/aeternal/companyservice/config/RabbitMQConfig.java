package com.aeternal.companyservice.config;

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

    /* Queue configuration for LOG communication */

    @Value("${rabbit.log-queue.name}")
    private String logQueueName;

    @Value("${rabbit.log-exchange.name}")
    private String logQueueExchange;

    @Value("${rabbit.log-routing.key}")
    private String logQueueRoutingKey;

    /* Queue configuration for observer design pattern */

    @Value("${rabbit.subject-queue.name}")
    private String subjectQueueName;

    @Value("${rabbit.subject-exchange.name}")
    private String subjectQueueExchange;

    @Value("${rabbit.subject-routing.key}")
    private String subjectQueueRoutingKey;

    @Bean("LOG-QUEUE")
    public Queue logQueue() {
        return new Queue(logQueueName);
    }

    @Bean("LOG-EXCHANGE")
    public TopicExchange logQueueExchange() {
        return new TopicExchange(logQueueExchange);
    }

    @Bean("LOG-BINDING")
    public Binding logQueueBinding() {
        return BindingBuilder
                .bind(logQueue())
                .to(logQueueExchange())
                .with(logQueueRoutingKey);
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

    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        rabbitTemplate.setReplyAddress(null);
        return rabbitTemplate;
    }

}
