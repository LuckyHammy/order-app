package com.abhishekchoksi.orderfoodv1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<OrderItem> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the order list with some data
        orderList = new ArrayList<>();
        orderList.add(new OrderItem("832616", "Delivery", "CASH", "sam biswas Mr/Mrs", "0894215730"));
        // Add more sample data as needed

        // Set up the adapter with a click listener for the info button
        orderAdapter = new OrderAdapter(orderList, this::showOrderDetailsDialog);
        recyclerView.setAdapter(orderAdapter);
    }

    // Show order details dialog when info button is clicked
    private void showOrderDetailsDialog(OrderItem orderItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_order_details, null);

        TextView orderId = dialogView.findViewById(R.id.dialog_order_id);
        TextView deliveryType = dialogView.findViewById(R.id.dialog_delivery_type);
        TextView paymentType = dialogView.findViewById(R.id.dialog_payment_type);
        TextView customerName = dialogView.findViewById(R.id.dialog_customer_name);
        TextView contactNumber = dialogView.findViewById(R.id.dialog_contact_number);

        orderId.setText(orderItem.getOrderId());
        deliveryType.setText(orderItem.getDeliveryType());
        paymentType.setText(orderItem.getPaymentType());
        customerName.setText(orderItem.getCustomerName());
        contactNumber.setText(orderItem.getContactNumber());

        builder.setView(dialogView)
                .setTitle("Order Details")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
