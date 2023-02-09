package com.example.menu.controller;

import com.example.menu.controller.request.AddInformationToMenuCommand;
import com.example.menu.entity.MenuEntity;
import com.example.menu.entity.OrderEntity;
import com.example.menu.entity.SetItemAmountEntity;
import com.example.menu.service.MenuService;
import com.example.menu.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MenuController {
    @Autowired
    MenuService menuService;
    @Autowired
    OrderService orderService;

    public int totalprice=0;

    @GetMapping("/getmenu")
    public Iterable<MenuEntity> getMenuList () {
        Iterable<MenuEntity> menuList = menuService.getMenu();
        return menuList;
    }
    @GetMapping("/getorder")
    public List<SetItemAmountEntity> getOrderList () {
        //取點過餐店的id,item,price
        List<OrderEntity> OrderList_All = orderService.getOrder();

        int cuisine_num=0;//炒時蔬
        int eggswithtomatoes_num=0;//番茄炒蛋
        int Pork_bean_curds_num=0;//豆干肉絲

        //將OrderListAll的餐點取出，並算出數量
        Set objects = new HashSet<>();
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
        };
        //將餐點和數量包成List
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
            Order_item_amount.add(order_num);
        }
        return Order_item_amount;
    }
    @PostMapping("/order")
    public String order(@RequestBody AddInformationToMenuCommand command){

        String items = command.getItems();

        if(!menuService.findMenu(items).isPresent()){//無此料理
            return "無此料理";
        }

        else{//有此料理
            OrderEntity order = new OrderEntity();//點餐的資料
            order.setOrder_item(menuService.findMenu(items).get().getItems());
            order.setOrder_price(menuService.findMenu(items).get().getPrice());
            order.setOrder_id(UUID.randomUUID().toString());
            orderService.additem(order);

            totalprice = totalprice + menuService.findMenu(items).get().getPrice();//價格總和

            return "成功點餐,餐點為:" + menuService.findMenu(items).get().getItems()
                    + ",價格:" + menuService.findMenu(items).get().getPrice();
        }
    }
    @GetMapping("/totalprice")
    public Integer totalprice(){
        return totalprice;
    }
}
