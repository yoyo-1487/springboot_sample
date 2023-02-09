package com.example.menu.aggregate;

import com.example.menu.dao.OrderDao;
import com.example.menu.entity.OrderEntity;
import com.example.menu.entity.SetItemAmountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class OrderAggregate {
    private int cuisine_num=0;//炒時蔬
    private int eggswithtomatoes_num=0;//番茄炒蛋
    private int Pork_bean_curds_num=0;//豆干肉絲

    @Autowired
    OrderDao orderDao;

    //------------撈出訂單所有資料------------
    public List getOrder_all() {
        return orderDao.findAll();
    }
    //------------找出訂單有哪些種類------------並累加數量------------
    public Set count_object_and_amount(List<OrderEntity> OrderList_All) {
        Set objects = new HashSet<>();
        cuisine_num=0;//炒時蔬
        eggswithtomatoes_num=0;//番茄炒蛋
        Pork_bean_curds_num=0;//豆干肉絲
        for (OrderEntity s: OrderList_All){
            //用來存item、amount
            if("炒時蔬".equals(s.getOrder_item())){
                cuisine_num++;
                objects.add("炒時蔬");
            }
            else if ("番茄炒蛋".equals(s.getOrder_item())){
                eggswithtomatoes_num++;
                objects.add("番茄炒蛋");
            }
            else if ("豆干肉絲".equals(s.getOrder_item())) {
                Pork_bean_curds_num++;
                objects.add("豆干肉絲");
            }
        }
        return objects;
    }
    //------------將品項及數量塞進List------------
    public List set_item_amount(Set objects) {
        List<SetItemAmountEntity> Order_item_amount = new ArrayList<>();
        if(objects.contains("炒時蔬")){
            SetItemAmountEntity order_num =new SetItemAmountEntity();//把項目跟數目包起來
            order_num.setItemamount_item("炒時蔬");
            order_num.setItemamount_amount(cuisine_num);
            Order_item_amount.add(order_num);
        }
        if(objects.contains("番茄炒蛋")){
            SetItemAmountEntity order_num =new SetItemAmountEntity();//把項目跟數目包起來
            order_num.setItemamount_item("番茄炒蛋");
            order_num.setItemamount_amount(eggswithtomatoes_num);
            Order_item_amount.add(order_num);
        }
        if(objects.contains("豆干肉絲")){
            SetItemAmountEntity order_num =new SetItemAmountEntity();//把項目跟數目包起來
            order_num.setItemamount_item("豆干肉絲");
            order_num.setItemamount_amount(Pork_bean_curds_num);
            Order_item_amount.add(order_num);
        }
        return Order_item_amount;
    }
    //--------------------------------------------------------

}
