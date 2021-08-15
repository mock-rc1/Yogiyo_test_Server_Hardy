package com.example.demo.src.event;

import com.example.demo.src.event.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EventDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetEvents> getEvents() {
        String getEventsQuery = "SELECT eventIdx, bannerUrl FROM Event";

        return this.jdbcTemplate.query(getEventsQuery,
                (rs, rowNum) -> new GetEvents(rs.getInt("eventIdx"),
                        rs.getString("bannerUrl")));
    }

}
