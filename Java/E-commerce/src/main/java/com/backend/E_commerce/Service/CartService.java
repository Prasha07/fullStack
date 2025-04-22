package com.backend.E_commerce.Service;

import com.backend.E_commerce.Entity.Cart;
import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.exception.CartItemException;
import com.backend.E_commerce.exception.ProductException;
import com.backend.E_commerce.exception.UserException;
import com.backend.E_commerce.request.AddItemRequest;

public interface CartService {

    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException, CartItemException, UserException;
    public Cart findUserCart(Long userId);
}
