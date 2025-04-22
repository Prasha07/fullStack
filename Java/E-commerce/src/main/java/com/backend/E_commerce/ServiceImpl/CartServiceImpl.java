package com.backend.E_commerce.ServiceImpl;

import com.backend.E_commerce.Entity.Cart;
import com.backend.E_commerce.Entity.CartItem;
import com.backend.E_commerce.Entity.Product;
import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.Repository.CartRepository;
import com.backend.E_commerce.Service.CartItemService;
import com.backend.E_commerce.Service.CartService;
import com.backend.E_commerce.Service.ProductService;
import com.backend.E_commerce.exception.CartItemException;
import com.backend.E_commerce.exception.ProductException;
import com.backend.E_commerce.exception.UserException;
import com.backend.E_commerce.request.AddItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }



    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException, CartItemException, UserException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(request.getProductId());
        CartItem cartItemIsPresent =  cartItemService.isCartItemExist(cart,product,request.getSize(),userId);
        if(cartItemIsPresent == null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setCart(cart);
            cartItem.setUserId(userId);
            int price = product.getPrice()  * request.getQuantity();
            cartItem.setPrice(price);
            int discpuntedPrice = product.getDiscountedPrice()* request.getQuantity();
            cartItem.setDiscountedPrice(discpuntedPrice);
            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        else {
            CartItem cartItem = cartItemService.updateCartItem(userId, cartItemIsPresent.getId(), cartItemIsPresent);
        }

       return "Cart item is added/modified successfully.";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice = 0 ;
        int totalDiscountedPrice = 0 ;
        int totalItems = 0 ;
        for(CartItem cartItem : cart.getCartItems()){
            totalPrice=totalPrice + cartItem.getPrice();
            totalDiscountedPrice = totalDiscountedPrice + cartItem.getDiscountedPrice();
            totalItems = totalItems + cartItem.getQuantity();
        }
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItems);
        cart.setDiscount(totalPrice - totalDiscountedPrice);
        return cartRepository.save(cart);
    }
}
