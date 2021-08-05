package com.example.demo.src.store;


import com.example.demo.src.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StoreDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCategoryRes> getCategories(){
        String getCategoriesQuery = "SELECT categoryIdx, categoryName FROM Category";
        return this.jdbcTemplate.query(getCategoriesQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("categoryIdx"),
                        rs.getString("categoryName"))
                );
    }

    public List<GetStoreCategoryRes> getStoreCategories(){
        String getStoreCategoriesQuery = "SELECT storeIdx, categoryIdx, storeName, storeRating, deliveryTime, deliveryTip, storeAddress\n" +
                "FROM Store;";
        return this.jdbcTemplate.query(getStoreCategoriesQuery,
                (rs, rowNum) -> new GetStoreCategoryRes(
                        rs.getInt("storeIdx"),
                        rs.getInt("categoryIdx"),
                        rs.getString("storeName"),
                        rs.getString("storeRating"),
                        rs.getString("deliveryTime"),
                        rs.getString("deliveryTip"),
                        rs.getString("storeAddress"))
                );
    }

    public List<GetStoreCategoryRes> getStoreCategoriesById(Integer categoryIdx){
        String getStoreCategoriesByIdQuery = "SELECT storeIdx, categoryIdx, storeName, storeRating, deliveryTime, deliveryTip, storeAddress\n" +
                "FROM Store WHERE categoryIdx =?";
        Integer getStoreCategoriesByIdParams = categoryIdx;
        return this.jdbcTemplate.query(getStoreCategoriesByIdQuery,
                (rs, rowNum) -> new GetStoreCategoryRes(
                        rs.getInt("storeIdx"),
                        rs.getInt("categoryIdx"),
                        rs.getString("storeName"),
                        rs.getString("storeRating"),
                        rs.getString("deliveryTime"),
                        rs.getString("deliveryTip"),
                        rs.getString("storeAddress")),
                getStoreCategoriesByIdParams);
    }
}