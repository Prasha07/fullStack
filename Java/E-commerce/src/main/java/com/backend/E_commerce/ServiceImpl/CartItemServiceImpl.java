package com.backend.E_commerce.ServiceImpl;

import com.backend.E_commerce.Entity.*;
import com.backend.E_commerce.Repository.CartItemRepository;
import com.backend.E_commerce.Repository.CartRepository;
import com.backend.E_commerce.Service.CartItemService;
import com.backend.E_commerce.Service.UserService;
import com.backend.E_commerce.exception.CartItemException;
import com.backend.E_commerce.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(cartItem.getQuantity());
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        CartItem createdCartItem =cartItemRepository.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) throws CartItemException, UserException {
       CartItem item = findCartItemById(cartItemId);
       User user = userService.findUserById(item.getUserId());
       if(user.getId().equals(userId)){
           item.setQuantity(cartItem.getQuantity());
           item.setPrice(item.getProduct().getPrice()*item.getQuantity());
           item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
       }
       return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem = cartItemRepository.isCartItemExist(cart,product,size,userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user =userService.findUserById(cartItem.getUserId());
        User reqUser = userService.findUserById(userId);
        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }
         else
             throw new UserException("A user can delete it's own cartItemRepository only......");
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isPresent()){
            return cartItem.get();
        }
        throw new CartItemException("Cart item does not exist");
    }
}
