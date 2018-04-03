package cn.future.client.service.impl;

import cn.future.client.entity.Order;
import cn.future.client.mapper.OrderMapper;
import cn.future.client.service.IOrderService;
import cn.future.client.service.ITransService;
import cn.future.client.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
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

    @Autowired
    private TransactionTemplate transactionTemplate;

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

    @Transactional(propagation = Propagation.NEVER)
    @Override
    public String updateModelByTemplate(Order model){
        String orderId = model.getId();
        transactionTemplate.execute(new TransactionCallback<Object>(){
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Order model = new Order();
                model.setId(orderId);
                model.setOrderStatus(4);//订单处理
                update(model);
                return null;
            }
        });

        String flag = transService.trans(url + orderId);
        transactionTemplate.execute(new TransactionCallback<Object>(){
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Order model = new Order();
                model.setId(orderId);
                model.setOrderStatus(Integer.parseInt(flag));
                update(model);
                return null;
            }
        });
        return flag;
    }
}
