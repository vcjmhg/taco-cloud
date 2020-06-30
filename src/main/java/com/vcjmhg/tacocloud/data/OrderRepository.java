package com.vcjmhg.tacocloud.data;

import com.vcjmhg.tacocloud.Order;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository 
         extends CrudRepository<Order, Long> {

}
