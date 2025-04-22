package com.backend.E_commerce.Service;

import com.backend.E_commerce.Entity.Cart;
import com.backend.E_commerce.Entity.CartItem;
import com.backend.E_commerce.Entity.Product;
import com.backend.E_commerce.Entity.Size;
import com.backend.E_commerce.exception.CartItemException;
import com.backend.E_commerce.exception.UserException;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId,Long id , CartItem cartItem)throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product, String size,Long userId);
    public void removeCartItem(Long userId,Long cartItemId) throws CartItemException,UserException;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
