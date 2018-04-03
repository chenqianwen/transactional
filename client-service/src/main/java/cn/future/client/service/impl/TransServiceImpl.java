package cn.future.client.service.impl;

import cn.future.client.entity.Order;
import cn.future.client.mapper.OrderMapper;
import cn.future.client.service.IOrderService;
import cn.future.client.service.ITransService;
import cn.future.client.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author： ygl
 * @date： 2018/3/30-16:40
 * @Description：
 */
@Service
public class TransServiceImpl implements ITransService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String trans(String url) {
        return restTemplate.getForEntity(url,String.class).getBody();
    }
}
