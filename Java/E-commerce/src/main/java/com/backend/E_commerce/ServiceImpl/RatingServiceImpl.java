package com.backend.E_commerce.ServiceImpl;

import com.backend.E_commerce.Entity.Product;
import com.backend.E_commerce.Entity.Rating;
import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.Repository.RatingRepository;
import com.backend.E_commerce.Service.ProductService;
import com.backend.E_commerce.Service.RatingService;
import com.backend.E_commerce.exception.ProductException;
import com.backend.E_commerce.request.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private ProductService productService;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest ratingRequest, User user) throws ProductException {
        Product product = productService.findProductById(ratingRequest.getProductId());
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setProduct(product);
        rating.setRating(ratingRequest.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
