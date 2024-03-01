package com.aeternal.companyservice.producer;

import com.aeternal.clients.models.utils.CompanySubjectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompanyProducer {

    @Value("${rabbit.log-exchange.name}")
    private String exchange;

    @Value("${rabbit.log-routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CompanyProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String companyId, String companyName) {
        rabbitTemplate.convertAndSend(exchange, routingKey, new CompanySubjectExchange(companyId, companyName));
    }

}
