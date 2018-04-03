package cn.future.client.controller;

import cn.future.client.entity.Order;
import cn.future.client.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： ygl
 * @date： 2018/4/3
 * @Description：
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/findById/{id}")
    public Order findById(@PathVariable String id){
        return orderService.findById(id);
    }

    @RequestMapping("/updateModel/{id}")
    public String updateModel(@PathVariable String id){
        Order order = orderService.findById(id);
        return orderService.updateModel(order);
    }
}
