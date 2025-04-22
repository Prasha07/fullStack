package com.backend.E_commerce.Service;

import com.backend.E_commerce.Entity.Review;
import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.exception.ProductException;
import com.backend.E_commerce.request.ReviewRequest;

import java.util.List;
import java.util.ListIterator;

public interface ReviewService {
    public Review createReview(ReviewRequest reviewRequest, User user) throws ProductException;
    public List<Review> getAllReview(Long productId);
}
