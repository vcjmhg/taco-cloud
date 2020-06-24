package com.vcjmhg.tacocloud.service;

import com.vcjmhg.tacocloud.pojo.Order;

/**
 * @Description
 * 对Order订单数据进行操作的接口
 * @Author Administrator
 * @Date 2020/6/2421:14
 * @Version ch3
 **/
public interface OrderRepository {
    /**
     * 将order保存到数据库中
     * @param order
     * @return
     */
    Order save(Order order);
}
