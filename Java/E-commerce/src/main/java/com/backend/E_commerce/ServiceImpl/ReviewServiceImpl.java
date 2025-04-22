package com.backend.E_commerce.ServiceImpl;

import com.backend.E_commerce.Entity.Product;
import com.backend.E_commerce.Entity.Review;
import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.Repository.ReviewRepository;
import com.backend.E_commerce.Service.ProductService;
import com.backend.E_commerce.Service.ReviewService;
import com.backend.E_commerce.exception.ProductException;
import com.backend.E_commerce.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ProductService productService;
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ProductService productService, ReviewRepository reviewRepository) {
        this.productService = productService;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(ReviewRequest reviewRequest, User user) throws ProductException {
       Product product = productService.findProductById(reviewRequest.getProductId());
      Review review = new Review();
      review.setUser(user);
      review.setProduct(product);
      review.setReview(review.getReview());
      review.setCreatedAt(LocalDateTime.now());
      return  reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
