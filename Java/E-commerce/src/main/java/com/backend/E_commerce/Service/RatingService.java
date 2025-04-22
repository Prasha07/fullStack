package com.backend.E_commerce.Service;

import com.backend.E_commerce.Entity.Rating;
import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.exception.ProductException;
import com.backend.E_commerce.request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest ratingRequest, User user) throws ProductException;
    public List<Rating> getProductsRating(Long productId);
}
