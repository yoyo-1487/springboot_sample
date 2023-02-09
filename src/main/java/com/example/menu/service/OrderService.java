package com.example.menu.service;

import com.example.menu.dao.OrderDao;
import com.example.menu.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.util.TypeUtils.type;


@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;
    public List getOrder() {//查詢餐點
        return orderDao.findAll();
    }
    public void additem(OrderEntity add_order) {//新增
        orderDao.save(add_order);
    }
}
