package com.vcjmhg.tacocloud.service.impl;

import com.vcjmhg.tacocloud.pojo.Ingredient;
import com.vcjmhg.tacocloud.pojo.Taco;
import com.vcjmhg.tacocloud.service.TacoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author vcjmhg
 * @Date 2020/6/24 21:31
 * @Version ch3
 **/
@Repository
public class JdbcTacoRepository implements TacoRepository {
    private JdbcTemplate jdbc;
    JdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId=saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient:taco.getIngredients()){
            saveIngredientToTaco(ingredient,tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        //Arrays.asList部分，传入的数组实际上就是String类型的数据，后边的new TimesStamp(..)对象会被隐式转换成String类型
        //通过此种复杂的方法主要是为了能够获取到插入表中id（因为id是通过表产生的，因此需要通过这种方法来获取）
        PreparedStatementCreator psc=new PreparedStatementCreatorFactory("" +
                "insert into Taco(name ,createdAt) values(?,?)", Types.VARCHAR,Types.TIMESTAMP)
                .newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                new Timestamp(taco.getCreatedAt().getTime())));
        KeyHolder keyHolder=new GeneratedKeyHolder();
        //使用PreparedStatementCreator发出更新语句以提供SQL和任何必需的参数。 生成的密钥将放入给定的KeyHolder中。
        jdbc.update(psc,keyHolder);
        //返回的就是插入数据库中的id值
        return keyHolder.getKey().longValue();
    }
    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update("insert into Taco_Ingredients(taco,ingredient) values(?,?)",tacoId,ingredient.getId());
    }
}
