package com.backend.E_commerce.Entity;

public class PaymentDetails {

    private String paymentethod;
    private String paymentStatus;
    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferenceId;
    private String razorpayPaymentLinkStatus;
    private String razorpayPaymentId;

    public PaymentDetails() {
    }

    public PaymentDetails(String paymentethod, String paymentStatus, String paymentId, String razorpayPaymentLinkId, String razorpayPaymentLinkReferenceId, String razorpayPaymentLinkStatus, String razorpayPaymentId) {
        this.paymentethod = paymentethod;
        this.paymentStatus = paymentStatus;
        this.paymentId = paymentId;
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
        this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
        this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public String getPaymentethod() {
        return paymentethod;
    }

    public void setPaymentethod(String paymentethod) {
        this.paymentethod = paymentethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRazorpayPaymentLinkId() {
        return razorpayPaymentLinkId;
    }

    public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
    }

    public String getRazorpayPaymentLinkReferenceId() {
        return razorpayPaymentLinkReferenceId;
    }

    public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
        this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
    }

    public String getRazorpayPaymentLinkStatus() {
        return razorpayPaymentLinkStatus;
    }

    public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
        this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }
}
