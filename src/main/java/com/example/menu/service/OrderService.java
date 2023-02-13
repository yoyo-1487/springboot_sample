package com.example.menu.service;

import com.example.menu.aggregate.OrderAggregate;
import com.example.menu.model.entity.OrderEntity;
import com.example.menu.object.SetItemAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    OrderAggregate  orderAggregate;

    //------------得到訂單資料(項目,數量)------------
    public HashMap<String, Integer> getOrderItemAndNumber() {
        List<OrderEntity> orderListAll = orderAggregate.getOrderAll();//1.撈出訂單所有資料
        return orderAggregate.findObjectAndCountAmount(orderListAll);//2.找出訂單有哪些種類,並累加數量
    }

    //
    public Integer countPrice(){
        return orderAggregate.countPrice();
    }

}
