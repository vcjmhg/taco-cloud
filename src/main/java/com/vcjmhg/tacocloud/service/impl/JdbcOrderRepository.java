package com.vcjmhg.tacocloud.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcjmhg.tacocloud.pojo.Order;
import com.vcjmhg.tacocloud.pojo.Taco;
import com.vcjmhg.tacocloud.service.OrderRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * 实现OrderReposity接口
 * @Author Administrator
 * @Date 2020/6/24 21:18
 * @Version ch3
 **/
@Repository
public class JdbcOrderRepository implements OrderRepository {
    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;
    JdbcOrderRepository(JdbcTemplate jdbc){
        this.orderInserter =new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");
        this.orderTacoInserter=new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order_Tacos");
        this.objectMapper=new ObjectMapper();
    }
    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId=saveOrderDetails(order);
        List<Taco> tacos=order.getTacos();
        for(Taco taco:tacos){
            saveTacoToOrder(taco,orderId);
        }
        return order;
    }
//TODO 有一个疑问还没搞清楚，2020年6月24日23:27:30
    private long saveOrderDetails(Order order) {
        Map<String,Object> values=objectMapper.convertValue(order,Map.class);
        values.put("placedAt",order.getPlacedAt());
        long orderId= (long) orderInserter.executeAndReturnKey(values);
        return orderId;
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values=new HashMap<>();
        values.put("tacoOrder",orderId);
        values.put("taco",taco.getId());
        orderTacoInserter.execute(values);
    }
}
