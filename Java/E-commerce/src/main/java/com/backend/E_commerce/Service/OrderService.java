package com.backend.E_commerce.Service;

import com.backend.E_commerce.Entity.Address;
import com.backend.E_commerce.Entity.Order;
import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.exception.OrderException;

import java.util.List;

public interface OrderService {

    public Order createOrder(User user, Address shippingAddress);
    public Order findOrderById(Long id) throws OrderException;
    public List<Order> usersOrderHistory(Long userId);
    public Order placedOrder(Long orderId) throws OrderException;
    public Order confirmedOrder(Long orderId) throws OrderException;
    public Order shippedOrder(Long orderId) throws OrderException;
    public Order deliveredOrder (Long orderId) throws  OrderException;
    public Order canceledOrder (Long orderId) throws OrderException;
    public List<Order> getAllOrders();
    public void deleteOrder(Long orderId) throws OrderException;
}
