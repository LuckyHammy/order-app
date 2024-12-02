package com.abhishekchoksi.orderfoodv1;

public class OrderItem {
    private String orderId;
    private String deliveryType;
    private String paymentType;
    private String customerName;
    private String contactNumber;

    public OrderItem(String orderId, String deliveryType, String paymentType, String customerName, String contactNumber) {
        this.orderId = orderId;
        this.deliveryType = deliveryType;
        this.paymentType = paymentType;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
