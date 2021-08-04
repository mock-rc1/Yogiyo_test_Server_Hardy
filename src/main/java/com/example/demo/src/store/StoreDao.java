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

    public List<GetStoreRes> getStoresByUser(String userAddress){
        String getStoresByUserQuery = "SELECT videoIdId, videoTitle, videoLength, videoImageUrl, isDeleted, userIdx, username, channelImageUrl, timeWatched,\n" +
                "       CASE\n" +
                "            when timestampdiff(hour, createdAt, current_timestamp()) < 24\n" +
                "                then date_format(createdAt, '%H시간 전')\n" +
                "            when timestampdiff(day, createdAt, CURRENT_TIMESTAMP()) < 1\n" +
                "                then '오늘'\n" +
                "            when timestampdiff(day, createdAt, CURRENT_TIMESTAMP()) < 2\n" +
                "                then '어제'\n" +
                "            when timestampdiff(day, createdAt, CURRENT_TIMESTAMP()) < 14\n" +
                "                then '1주 전'\n" +
                "            when timestampdiff(day, createdAt, CURRENT_TIMESTAMP()) < 21\n" +
                "                then '2주 전'\n" +
                "            when timestampdiff(day, createdAt, CURRENT_TIMESTAMP()) < 28\n" +
                "                then '3주 전'\n" +
                "            else\n" +
                "                date_format(createdAt, '%Y년-%m월-%d일')\n" +
                "        end\n" +
                "        as createdAt\n" +
                "\n" +
                "FROM Video v\n" +
                "       INNER JOIN (SELECT userIdx, username FROM Account) as CA\n" +
                "       INNER JOIN (SELECT accountId2, channelImageUrl FROM Channel) as AA\n" +
                "       INNER JOIN (SELECT timeWatched, accountId FROM ViewData) as BB\n" +
                "WHERE v.accountId = CA.userIdx = AA.accountId2 = BB.accountId" +
                "       AND videoTitle =?";
        String getStoresByUserParams = videoTitle;
        return this.jdbcTemplate.query(getStoresByUserQuery,
                (rs, rowNum) -> new GetStoreRes(
                        ,
                getStoresByUserParams);
    }
}