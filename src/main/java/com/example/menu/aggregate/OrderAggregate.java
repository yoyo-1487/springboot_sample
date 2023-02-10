package com.example.menu.aggregate;

import com.example.menu.model.dao.OrderDao;
import com.example.menu.model.entity.OrderEntity;
import com.example.menu.object.SetItemAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class OrderAggregate {
    private int cuisineNum=0;//炒時蔬
    private int eggswithtomatoesNum=0;//番茄炒蛋
    private int porkbeancurdsNum=0;//豆干肉絲

    @Autowired
    OrderDao orderDao;

    //------------撈出訂單所有資料------------
    public List<OrderEntity> getOrderAll() {
        return orderDao.findAll();
    }
    //------------找出訂單有哪些種類------------並累加數量------------
    public Set<String> findObjectAndCountAmount(List<OrderEntity> OrderListAll) {
        Set<String> items = new HashSet<>();
        cuisineNum=0;//炒時蔬
        eggswithtomatoesNum=0;//番茄炒蛋
        porkbeancurdsNum=0;//豆干肉絲
        for (OrderEntity s: OrderListAll){
            //用來存item、amount
            if("炒時蔬".equals(s.getOrderItem())){
                cuisineNum++;
                items.add("炒時蔬");
            }
            else if ("番茄炒蛋".equals(s.getOrderItem())){
                eggswithtomatoesNum++;
                items.add("番茄炒蛋");
            }
            else if ("豆干肉絲".equals(s.getOrderItem())) {
                porkbeancurdsNum++;
                items.add("豆干肉絲");
            }
        }
        return items;
    }
    //------------將品項及數量塞進List------------
    public List<SetItemAmount> setItemAndAmount(Set<String> items) {
        List<SetItemAmount> setItemAndAmount = new ArrayList<>();
        if(items.contains("炒時蔬")){
            SetItemAmount cuisineItemAmount =new SetItemAmount();//把項目跟數目包起來
            cuisineItemAmount.setItem("炒時蔬");
            cuisineItemAmount.setAmount(cuisineNum);
            setItemAndAmount.add(cuisineItemAmount);
        }
        if(items.contains("番茄炒蛋")){
            SetItemAmount eggswithtomatoesItemAmount =new SetItemAmount();//把項目跟數目包起來
            eggswithtomatoesItemAmount.setItem("番茄炒蛋");
            eggswithtomatoesItemAmount.setAmount(eggswithtomatoesNum);
            setItemAndAmount.add(eggswithtomatoesItemAmount);
        }
       if(items.contains("豆干肉絲")){
            SetItemAmount porkbeancurdsItemAmount =new SetItemAmount();//把項目跟數目包起來
            porkbeancurdsItemAmount.setItem("豆干肉絲");
            porkbeancurdsItemAmount.setAmount(porkbeancurdsNum);
            setItemAndAmount.add(porkbeancurdsItemAmount);
        }
        return setItemAndAmount;
    }
    //--------------------------------------------------------

}
