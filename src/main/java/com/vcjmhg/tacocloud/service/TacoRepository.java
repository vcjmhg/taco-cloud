package com.vcjmhg.tacocloud.service;

import com.vcjmhg.tacocloud.pojo.Taco;

/**
 * @Description 封装Taco与数据库之间的操作
 * @Author vcjmhg
 * @Date 2020/6/24 21:33
 * @Version ch3
 **/
public interface TacoRepository {
    Taco save(Taco taco);
}
