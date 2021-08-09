package com.example.demo.src.menu;


import com.example.demo.src.menu.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MenuDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetMenuRes> getMenus(int storeIdx) {
        String getMenusQuery = "SELECT menuIdx,\n" +
                "       menuName,\n" +
                "       menuPrice,\n" +
                "       menuImageUrl,\n" +
                "       menuInfo,\n" +
                "       menuCategory,\n" +
                "       Menu.storeIdx\n" +
                "from Menu\n" +
                "        join (select storeIdx\n" +
                "            from Store\n" +
                "            group by storeIdx)\n" +
                "        as v\n" +
                "            on Menu.storeIdx = v.storeIdx\n" +
                "where v.storeIdx = ?";
        return this.jdbcTemplate.query(getMenusQuery,
                (rs, rowNum) -> new GetMenuRes(
                        rs.getInt("menuIdx"),
                        rs.getString("menuName"),
                        rs.getString("menuPrice"),
                        rs.getString("menuImageUrl"),
                        rs.getString("menuInfo"),
                        rs.getString("menuCategory")),storeIdx);
    }

    public List<GetSingleMenuRes> getMenu(int storeIdx, int menuIdx) {
        String getMenuQuery = "SELECT menuIdx,\n" +
                "       menuName,\n" +
                "       menuPrice,\n" +
                "       menuImageUrl,\n" +
                "       menuInfo,\n" +
                "       Menu.storeIdx\n" +
                "from Menu\n" +
                "        join (select storeIdx\n" +
                "            from Store\n" +
                "            group by storeIdx)\n" +
                "        as v\n" +
                "            on Menu.storeIdx = v.storeIdx\n" +
                "where v.storeIdx = ? and menuIdx = ?";
        return this.jdbcTemplate.query(getMenuQuery,
                (rs, rowNum) -> new GetSingleMenuRes(
                        rs.getInt("menuIdx"),
                        rs.getString("menuName"),
                        rs.getString("menuPrice"),
                        rs.getString("menuImageUrl"),
                        rs.getString("menuInfo")), storeIdx, menuIdx);
    }
}