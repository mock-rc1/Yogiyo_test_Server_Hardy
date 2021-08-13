package com.example.demo.src.order;

import com.example.demo.src.order.model.*;
import com.example.demo.src.user.model.PatchUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createOrder(PostOrderReq postOrderReq) {
        String createOrderQuery = "INSERT INTO OrderMenu (orderMenuIdx, menuIdx, menuName, menuQuantity, menuPrice, userAddress, storeIdx, totalPrice, deliveryTip, safeDelivery, tableware, payType, userIdx) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] createOrderParams = new Object[]{postOrderReq.getOrderMenuIdx(), postOrderReq.getMenuIdx(), postOrderReq.getMenuName(), postOrderReq.getMenuQuantity(), postOrderReq.getMenuPrice(), postOrderReq.getUserAddress(), postOrderReq.getStoreIdx(), postOrderReq.getTotalPrice(), postOrderReq.getDeliveryTip(), postOrderReq.getSafeDelivery(), postOrderReq.getTableware(), postOrderReq.getPayType(), postOrderReq.getUserIdx()};
        this.jdbcTemplate.update(createOrderQuery, createOrderParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public List<GetOrderRes> getOrder(int userIdx){
        String getOrderQuery = "SELECT orderMenuIdx, menuName, O.storeIdx, ST.storeName, ST.storeLogoUrl, userIdx\n" +
                "FROM OrderMenu O\n" +
                "    JOIN (SELECT storeIdx, storeName, storeLogoUrl FROM Store) as ST\n" +
                "WHERE O.storeIdx = ST.storeIdx\n" +
                "    AND userIdx =?";
        Integer getOrderParams = userIdx;
        return this.jdbcTemplate.query(getOrderQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getInt("orderMenuIdx"),
                        rs.getString("menuName"),
                        rs.getInt("storeIdx"),
                        rs.getString("storeName"),
                        rs.getString("storeLogoUrl"),
                        rs.getInt("userIdx")),
                getOrderParams);
    }

    public int deleteOrder(PatchOrderReq patchOrderReq){
        String modifyUserNameQuery = "update OrderMenu set isDeleted = ? where orderMenuIdx = ? and userIdx = ?";
        Object[] modifyUserNameParams = new Object[]{patchOrderReq.getIsDeleted(), patchOrderReq.getOrderMenuIdx(), patchOrderReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }
}