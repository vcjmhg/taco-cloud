package com.vcjmhg.tacocloud.data;

import com.vcjmhg.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;


public interface TacoRepository 
         extends CrudRepository<Taco, Long> {

}
