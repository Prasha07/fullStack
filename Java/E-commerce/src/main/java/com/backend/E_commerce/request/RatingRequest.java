package com.backend.E_commerce.request;

public class RatingRequest {

    private Long productId;
    private  Double rating;

    public RatingRequest() {

    }

    public RatingRequest(Long productId, Double rating) {
        this.productId = productId;
        this.rating = rating;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
