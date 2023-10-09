package com.rabbitMQTest.RabitMQTest.controller;


import com.rabbitMQTest.RabitMQTest.config.MessageConfig;
import com.rabbitMQTest.RabitMQTest.dto.Order;
import com.rabbitMQTest.RabitMQTest.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        //restaurantservice
        order.setOrderId(UUID.randomUUID().toString());
        //payment service
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed succesfully in " + restaurantName);
        template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, orderStatus);
        return "Success !!";
    }
}