package com.example.menu.service;

import com.example.menu.aggregate.OrderAggregate;
import com.example.menu.dao.OrderDao;
import com.example.menu.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    OrderAggregate  orderAggregate;
    //------------查詢餐點------------
    public List getOrder_all() {
        List<OrderEntity> OrderList_All = orderAggregate.getOrder_all();//1.撈出訂單所有資料
        Set<OrderEntity> find_item = orderAggregate.count_object_and_amount(OrderList_All);//2.找出訂單有哪些種類
        return orderAggregate.set_item_amount(find_item);//將品項及數量塞進List
    }

}
