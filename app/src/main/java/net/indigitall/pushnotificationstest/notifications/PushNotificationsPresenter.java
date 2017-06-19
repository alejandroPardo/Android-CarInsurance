package net.indigitall.pushnotificationstest.notifications;

import android.text.TextUtils;

import com.google.firebase.messaging.FirebaseMessaging;
import net.indigitall.pushnotificationstest.data.PushNotificationsRepository;
import net.indigitall.pushnotificationstest.data.PushNotification;

import java.util.ArrayList;

/**
 * Presentador de las notificaciones
 */
public class PushNotificationsPresenter implements PushNotificationContract.Presenter {
    private final PushNotificationContract.View mNotificationView;
    private final FirebaseMessaging mFCMInteractor;

    public PushNotificationsPresenter(PushNotificationContract.View notificationView,
                                      FirebaseMessaging FCMInteractor) {
        mNotificationView = notificationView;
        mFCMInteractor = FCMInteractor;

        notificationView.setPresenter(this);
    }

    @Override
    public void start() {
        registerAppClient();
        loadNotifications();
    }

    @Override
    public void registerAppClient() {
        mFCMInteractor.subscribeToTopic("promos");
    }

    @Override
    public void loadNotifications() {
        PushNotificationsRepository.getInstance().getPushNotifications(
                new PushNotificationsRepository.LoadCallback() {
                    @Override
                    public void onLoaded(ArrayList<PushNotification> notifications) {
                        if (notifications.size() > 0) {
                            mNotificationView.showEmptyState(false);
                            mNotificationView.showNotifications(notifications);
                        } else {
                            mNotificationView.showEmptyState(true);
                        }
                    }
                }
        );
    }

    @Override
    public void savePushMessage(String title, String description,
                                String expiryDate, String discount) {
        PushNotification pushMessage = new PushNotification();
        pushMessage.setTitle(title);
        pushMessage.setDescription(description);
        pushMessage.setExpiryDate(expiryDate);
        pushMessage.setDiscount(TextUtils.isEmpty(discount) ? 0 : Float.parseFloat(discount));

        PushNotificationsRepository.getInstance().savePushNotification(pushMessage);

        mNotificationView.showEmptyState(false);
        mNotificationView.popPushNotification(pushMessage);
    }


}
