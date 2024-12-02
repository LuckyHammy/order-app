package com.abhishekchoksi.orderfoodv1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderItem> orderList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onInfoClick(OrderItem orderItem);
    }

    public OrderAdapter(List<OrderItem> orderList, OnItemClickListener onItemClickListener) {
        this.orderList = orderList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem orderItem = orderList.get(position);
        holder.orderId.setText(orderItem.getOrderId());
        holder.deliveryType.setText(orderItem.getDeliveryType());
        holder.paymentType.setText(orderItem.getPaymentType());
        holder.customerName.setText(orderItem.getCustomerName());
        holder.contactNumber.setText(orderItem.getContactNumber());

        holder.infoButton.setOnClickListener(v -> onItemClickListener.onInfoClick(orderItem));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, deliveryType, paymentType, customerName, contactNumber;
        ImageButton infoButton;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_id);
            deliveryType = itemView.findViewById(R.id.delivery_type);
            paymentType = itemView.findViewById(R.id.payment_type);
            customerName = itemView.findViewById(R.id.customer_name);
            contactNumber = itemView.findViewById(R.id.contact_number);
            infoButton = itemView.findViewById(R.id.info_button);
        }
    }
}
