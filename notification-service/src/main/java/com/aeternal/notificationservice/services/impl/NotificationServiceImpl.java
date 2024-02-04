package com.aeternal.notificationservice.services.impl;

import com.aeternal.notificationservice.services.abs.NotificationService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(String registrationToken, String messageText) throws FirebaseMessagingException {
        Message message = Message.builder()
                .putData("key", "value")
                .setToken(registrationToken)
                .build();
        FirebaseMessaging.getInstance().send(message);
    }
}
