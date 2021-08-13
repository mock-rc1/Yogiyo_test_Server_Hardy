package com.example.demo.src.store;


import com.example.demo.src.order.model.PatchOrderReq;
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
        String getStoreCategoriesQuery = "SELECT storeIdx, categoryIdx, storeName, storeRating, deliveryTime, deliveryTip, storeAddress, storeImageUrl, storeLogoUrl\n" +
                "FROM Store;";
        return this.jdbcTemplate.query(getStoreCategoriesQuery,
                (rs, rowNum) -> new GetStoreCategoryRes(
                        rs.getInt("storeIdx"),
                        rs.getInt("categoryIdx"),
                        rs.getString("storeName"),
                        rs.getString("storeRating"),
                        rs.getString("deliveryTime"),
                        rs.getString("deliveryTip"),
                        rs.getString("storeAddress"),
                        rs.getString("storeImageUrl"),
                        rs.getString("storeLogoUrl"))
                );
    }

    public List<GetStoreCategoryRes> getStoreCategoriesById(Integer categoryIdx){
        String getStoreCategoriesByIdQuery = "SELECT storeIdx, categoryIdx, storeName, storeRating, deliveryTime, deliveryTip, storeAddress, storeImageUrl, storeLogoUrl\n" +
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
                        rs.getString("storeAddress"),
                        rs.getString("storeImageUrl"),
                        rs.getString("storeLogoUrl")),
                getStoreCategoriesByIdParams);
    }

    public GetStoreRes getStore(int storeIdx){
        String getStoreQuery = "select storeIdx, categoryIdx, storeName, storeRating, deliveryTime, minOrderPrice, deliveryTip, storeOpenTime, storePhoneNum, storeAddress, businessName, businessLicenseNum, storeFoodInfo, isDeleted, status, storeImageUrl from Store where storeIdx = ?";
        int getStoreParams = storeIdx;
        return this.jdbcTemplate.queryForObject(getStoreQuery,
                (rs, rowNum) -> new GetStoreRes(
                        rs.getInt("storeIdx"),
                        rs.getInt("categoryIdx"),
                        rs.getString("storeName"),
                        rs.getString("storeRating"),
                        rs.getString("deliveryTime"),
                        rs.getString("minOrderPrice"),
                        rs.getString("deliveryTip"),
                        rs.getString("storeOpenTime"),
                        rs.getString("storePhoneNum"),
                        rs.getString("storeAddress"),
                        rs.getString("businessName"),
                        rs.getString("businessLicenseNum"),
                        rs.getString("storeFoodInfo"),
                        rs.getString("isDeleted"),
                        rs.getInt("status"),
                        rs.getString("storeImageUrl")),
                getStoreParams);
    }

    public List<GetReviewRes> getReview(int storeIdx){
        String getReviewQuery = "SELECT reviewIdx, storeIdx, menuIdx, R.userIdx, userName, reviewRating, reviewContent, reviewImageUrl, tasteRating, amountRating, deliveryRating,\n" +
                "CASE\n" +
                "when timestampdiff(hour, createdAt, current_timestamp()) < 24\n" +
                "then date_format(createdAt, '%H시간 전')\n" +
                "when timestampdiff(day, createdAt, CURRENT_TIMESTAMP()) < 1\n" +
                "then '오늘'\n" +
                "when timestampdiff(day, createdAt, CURRENT_TIMESTAMP()) < 2\n" +
                "then '어제'\n" +
                "when timestampdiff(day, createdAt, CURRENT_TIMESTAMP()) < 14\n" +
                "then '1주 전'\n" +
                "when timestampdiff(day, createdAt, CURRENT_TIMESTAMP()) < 21\n" +
                "then '2주 전'\n" +
                "when timestampdiff(day, createdAt, CURRENT_TIMESTAMP()) < 28\n" +
                "then '3주 전'\n" +
                "else\n" +
                "date_format(createdAt, '%Y년-%m월-%d일')\n" +
                "end\n" +
                "as createdAt\n" +
                "FROM Review R\n" +
                "JOIN (SELECT userIdx, userName from User) as U\n" +
                "WHERE U.userIdx = R.userIdx\n" +
                "    AND storeIdx = ?";
        Integer getReviewParams = storeIdx;
        return this.jdbcTemplate.query(getReviewQuery,
                (rs, rowNum) -> new GetReviewRes(
                        rs.getInt("reviewIdx"),
                        rs.getInt("storeIdx"),
                        rs.getInt("menuIdx"),
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getInt("reviewRating"),
                        rs.getString("reviewContent"),
                        rs.getString("reviewImageUrl"),
                        rs.getInt("tasteRating"),
                        rs.getInt("amountRating"),
                        rs.getInt("deliveryRating")),
                getReviewParams);
    }

    public int deleteReview(PatchReviewReq patchReviewReq){
        String modifyReviewQuery = "update Review set isDeleted = ? where storeIdx = ? and reviewIdx = ?";
        Object[] modifyReviewParams = new Object[]{patchReviewReq.getIsDeleted(), patchReviewReq.getStoreIdx(), patchReviewReq.getReviewIdx()};

        return this.jdbcTemplate.update(modifyReviewQuery,modifyReviewParams);
    }
}