package net.indigitall.pushnotificationstest.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import net.indigitall.pushnotificationstest.R;
import net.indigitall.pushnotificationstest.login.LoginActivity;

public class PushNotificationsActivity extends AppCompatActivity {

    private static final String TAG = PushNotificationsActivity.class.getSimpleName();

    private PushNotificationsFragment mNotificationsFragment;
    private PushNotificationsPresenter mNotificationsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.title_activity_notifications));

        // ¿Existe un usuario logueado?
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        mNotificationsFragment =
                (PushNotificationsFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.notifications_container);
        if (mNotificationsFragment == null) {
            mNotificationsFragment = PushNotificationsFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.notifications_container, mNotificationsFragment)
                    .commit();
        }

        mNotificationsPresenter = new PushNotificationsPresenter(
                mNotificationsFragment, FirebaseMessaging.getInstance());
    }
}
