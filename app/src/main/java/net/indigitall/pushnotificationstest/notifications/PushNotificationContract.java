package net.indigitall.pushnotificationstest.notifications;

import net.indigitall.pushnotificationstest.BasePresenter;
import net.indigitall.pushnotificationstest.BaseView;
import net.indigitall.pushnotificationstest.data.PushNotification;

import java.util.ArrayList;

/**
 * Interacción MVP en Notificaciones
 */
public interface PushNotificationContract {

    interface View extends BaseView<Presenter>{

        void showNotifications(ArrayList<PushNotification> notifications);

        void showEmptyState(boolean empty);

        void popPushNotification(PushNotification pushMessage);
    }

    interface Presenter extends BasePresenter{

        void registerAppClient();

        void loadNotifications();

        void savePushMessage(String title, String description,
                             String expiryDate, String discount);
    }
}
