package com.turkcell.notification_service.messaging.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderCreatedNotification {
    @Bean
    public Consumer<OrderCreatedEvent> orderCreated() {
        return event -> {
            System.out.println("[Notification] maili gönderildi.");
            System.out.println(event.productId());
        };
    }

    record OrderCreatedEvent(String productId) {
    }
}
