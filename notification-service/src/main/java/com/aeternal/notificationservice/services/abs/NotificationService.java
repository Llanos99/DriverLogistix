package com.aeternal.notificationservice.services.abs;

import com.google.firebase.messaging.FirebaseMessagingException;

public interface NotificationService {

    void sendNotification(String registrationToken, String message) throws FirebaseMessagingException;

}
