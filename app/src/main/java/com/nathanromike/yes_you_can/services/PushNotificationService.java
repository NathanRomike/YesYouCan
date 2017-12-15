package com.nathanromike.yes_you_can.services;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            Log.e("PushService", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
        throw new UnsupportedOperationException("This is a testing exception");
    }
}
