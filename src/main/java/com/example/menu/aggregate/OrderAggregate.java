package com.example.menu.aggregate;

import com.example.menu.model.dao.OrderDao;
import com.example.menu.model.entity.OrderEntity;
import com.example.menu.object.SetItemAmount;
import com.example.menu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class OrderAggregate {
//    private int cuisineNum = 0;//炒時蔬
//    private int eggswithtomatoesNum = 0;//番茄炒蛋
//    private int porkbeancurdsNum = 0;//豆干肉絲

    @Autowired
    OrderDao orderDao;

    /**
     * 撈出訂單所有資料
     * @return 菜單
     */
    public List<OrderEntity> getOrderAll() {
        return orderDao.findAll();
    }

    /**
     * 回傳訂單的細節
     *
     * @param orderListAll 訂單Table的資料
     * @return 訂單計算結果
     */
    public HashMap<String, Integer> findObjectAndCountAmount(List<OrderEntity> orderListAll) {
//        Set<String> items = new HashSet<>();
//        cuisineNum = 0;//炒時蔬
//        eggswithtomatoesNum = 0;//番茄炒蛋
//        porkbeancurdsNum = 0;//豆干肉絲

        HashMap<String, Integer> itemsMap = new HashMap<>();
        for (OrderEntity orderEntity : orderListAll) { //---撈出訂單table的菜單
            String orderItem = orderEntity.getOrderItem(); //---取出菜單的名稱
            if (itemsMap.containsKey(orderItem)) { //---如果菜單已經存在我的集合，那就把它原本的數量做+1
                itemsMap.put(orderItem, itemsMap.get(orderItem) + 1);
            } else { //---第一次找到，就做初始化的動作
                itemsMap.put(orderItem, 1);
            }
        }


//        itemsMap.forEach((key,value) ->{
//            System.out.println("key = " + key);
//            System.out.println("value = " + value);
//        });


//        for (OrderEntity orderDetails : orderListAll) {
//            //用來存item、amount
//            if ("炒時蔬".equals(orderDetails.getOrderItem())) {
//                cuisineNum++;
//                items.add("炒時蔬");
//            } else if ("番茄炒蛋".equals(orderDetails.getOrderItem())) {
//                eggswithtomatoesNum++;
//                items.add("番茄炒蛋");
//            } else if ("豆干肉絲".equals(orderDetails.getOrderItem())) {
//                porkbeancurdsNum++;
//                items.add("豆干肉絲");
//            }
//        }
        return itemsMap;
    }

    //------------將品項及數量塞進List------------
//    public List<SetItemAmount> setItemAndAmount(HashMap<String, Integer> items) {
//        List<SetItemAmount> setItemAndAmount = new ArrayList<>();
//        if (items.contains("炒時蔬")) {
//            SetItemAmount cuisineItemAmount = new SetItemAmount();//把項目跟數目包起來
//            cuisineItemAmount.setItem("炒時蔬");
//            cuisineItemAmount.setAmount(cuisineNum);
//            setItemAndAmount.add(cuisineItemAmount);
//        }
//        if (items.contains("番茄炒蛋")) {
//            SetItemAmount eggswithtomatoesItemAmount = new SetItemAmount();//把項目跟數目包起來
//            eggswithtomatoesItemAmount.setItem("番茄炒蛋");
//            eggswithtomatoesItemAmount.setAmount(eggswithtomatoesNum);
//            setItemAndAmount.add(eggswithtomatoesItemAmount);
//        }
//        if (items.contains("豆干肉絲")) {
//            SetItemAmount porkbeancurdsItemAmount = new SetItemAmount();//把項目跟數目包起來
//            porkbeancurdsItemAmount.setItem("豆干肉絲");
//            porkbeancurdsItemAmount.setAmount(porkbeancurdsNum);
//            setItemAndAmount.add(porkbeancurdsItemAmount);
//        }
//        return setItemAndAmount;
//    }
    //--------------------------------------------------------
public Integer countPrice(List<OrderEntity> orderListAll){
        int totalprice=0;
        for(OrderEntity orderentity:orderListAll){
            totalprice += orderentity.getOrderPrice();
        }
        return totalprice;
}

}
