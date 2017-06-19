package net.indigitall.pushnotificationstest.data;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;

/**
 * Repositorio de push notifications
 */
public final class PushNotificationsRepository {

    private static ArrayMap<String, PushNotification> LOCAL_PUSH_NOTIFICATIONS = new ArrayMap<>();
    private static PushNotificationsRepository INSTANCE;

    private PushNotificationsRepository() {
    }

    public static PushNotificationsRepository getInstance() {
        if (INSTANCE == null) {
            return new PushNotificationsRepository();
        } else {
            return INSTANCE;
        }
    }

    public void getPushNotifications(LoadCallback callback) {
        callback.onLoaded(new ArrayList<>(LOCAL_PUSH_NOTIFICATIONS.values()));
    }

    public void savePushNotification(PushNotification notification) {
        LOCAL_PUSH_NOTIFICATIONS.put(notification.getId(), notification);
    }

    public interface LoadCallback {
        void onLoaded(ArrayList<PushNotification> notifications);
    }

}
