package com.xu.nohotel.dao;

import com.xu.nohotel.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    public int insert(Order order);
    public Order findOrderByTitle(String title);
    public List<Order> findAllOrderByUserId(Integer userId);
}
