package com.vcjmhg.tacocloud.service.impl;

import com.vcjmhg.tacocloud.pojo.Ingredient;
import com.vcjmhg.tacocloud.service.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author vcjmhg
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate jdbc;
    @Autowired
    JdbcIngredientRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id,name,type from Ingredient",
                this::mapRowToIngredient);
    }

    //construct Ingredient with the data from ResultSet
    private  Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type")));
    }

    @Override
    public Ingredient findById(String id) {
        return jdbc.queryForObject("select id,name,type from Ingredient where id =?",
                this::mapRowToIngredient,id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("insert into Ingredient(id,name,type) values(?,?,?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }
}
