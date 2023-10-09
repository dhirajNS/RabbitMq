package com.rabbitMQTest.RabitMQTest.service;

import com.rabbitMQTest.RabitMQTest.config.MessageConfig;
import com.rabbitMQTest.RabitMQTest.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

        @RabbitListener(queues = MessageConfig.QUEUE)
        public void consumeMessageFromQueue(OrderStatus orderStatus) {
            System.out.println("Message recieved from queue : " + orderStatus);
        }
}
