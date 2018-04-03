package cn.future.client.entity;

import cn.future.client.support.BaseEntity;
import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author： ygl
 * @date： 2018/4/3
 * @Description：
 */
@Data
@Table(name = "orders")
public class Order extends BaseEntity {
    /**
     * 订单状态
     */
    private int orderStatus;

    /**
     * 订单总价
     */
    private BigDecimal totalPrice;
}