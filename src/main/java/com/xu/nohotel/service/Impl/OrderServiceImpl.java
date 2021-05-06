package com.xu.nohotel.service.Impl;

import com.xu.nohotel.dao.OrderDao;
import com.xu.nohotel.domain.Order;
import com.xu.nohotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public boolean insert(Order order) {
        return orderDao.insert(order) > 0;
    }

    @Override
    public Order findOrderByTitle(String title) {
        return orderDao.findOrderByTitle(title);
    }

    @Override
    public List<Order> findAllOrderByUserId(Integer userId) {
        return orderDao.findAllOrderByUserId(userId);
    }
}
