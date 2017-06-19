package net.indigitall.pushnotificationstest.notifications;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.indigitall.pushnotificationstest.R;
import net.indigitall.pushnotificationstest.data.PushNotification;

import java.util.ArrayList;

/**
 * Adaptador de notificaciones
 */
public class PushNotificationsAdapter
        extends RecyclerView.Adapter<PushNotificationsAdapter.ViewHolder> {

    ArrayList<PushNotification> pushNotifications = new ArrayList<>();

    public PushNotificationsAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_list_notification, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PushNotification newNotification = pushNotifications.get(position);

        holder.title.setText(newNotification.getTitle());
        holder.description.setText(newNotification.getDescription());
        holder.expiryDate.setText(String.format("Válido hasta el %s", newNotification.getExpiryDate()));
        holder.discount.setText(String.format("%d%%", (int) (newNotification.getDiscount() * 100)));
    }

    @Override
    public int getItemCount() {
        return pushNotifications.size();
    }

    public void replaceData(ArrayList<PushNotification> items) {
        setList(items);
        notifyDataSetChanged();
    }

    public void setList(ArrayList<PushNotification> list) {
        this.pushNotifications = list;
    }

    public void addItem(PushNotification pushMessage) {
        pushNotifications.add(0, pushMessage);
        notifyItemInserted(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView expiryDate;
        public TextView discount;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            expiryDate = (TextView) itemView.findViewById(R.id.tv_expiry_date);
            discount = (TextView) itemView.findViewById(R.id.tv_discount);
        }
    }
}
