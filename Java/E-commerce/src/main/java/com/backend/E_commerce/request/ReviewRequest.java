package com.backend.E_commerce.request;

public class ReviewRequest {
    Long productId;
    String Review;


    public ReviewRequest() {
    }

    public ReviewRequest(Long productId, String review) {
        this.productId = productId;
        Review = review;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }
}
