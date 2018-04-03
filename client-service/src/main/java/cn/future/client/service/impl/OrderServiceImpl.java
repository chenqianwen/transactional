package cn.future.client.service.impl;

import cn.future.client.entity.Order;
import cn.future.client.mapper.OrderMapper;
import cn.future.client.service.IOrderService;
import cn.future.client.service.ITransService;
import cn.future.client.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


/**
 * @author： ygl
 * @date： 2018/3/30-16:40
 * @Description：
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ITransService transService;

    private final String url ="http://localhost:9080/trans/sendOrder?orderId=";

    @Transactional
    @Override
    public String updateModel(Order model){
        String flag = "-1";
        String orderId = model.getId();
        flag = transService.trans(url + orderId);
        model = new Order();
        model.setId(orderId);
        if("0".equals(flag)){
            model.setOrderStatus(0);
        } else {
            model.setOrderStatus(1);
        }
        update(model);
        return flag;
    }
}
