package com.xu.nohotel.service;

import com.xu.nohotel.domain.Order;

import java.util.List;

public interface OrderService {
    public boolean insert(Order order);
    public Order findOrderByTitle(String title);
    public List<Order> findAllOrderByUserId(Integer userId);
}
