package com.backend.E_commerce.ServiceImpl;

import com.backend.E_commerce.Entity.Address;
import com.backend.E_commerce.Entity.Order;
import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.Repository.CartRepository;
import com.backend.E_commerce.Service.CartService;
import com.backend.E_commerce.Service.OrderService;
import com.backend.E_commerce.Service.ProductService;
import com.backend.E_commerce.exception.OrderException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private CartRepository cartRepository;
    private CartService cartItemService;
    private ProductService productService;

    public OrderServiceImpl(CartRepository cartRepository, CartService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long id) throws OrderException {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
