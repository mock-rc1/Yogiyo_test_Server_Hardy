package com.example.demo.src.event;

import com.example.demo.src.event.model.*;
import com.example.demo.src.store.model.GetStoreCategoryRes;
import com.example.demo.src.store.model.PatchReviewReq;
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

    public GetEventInfo getEventInfo(Integer eventIdx){
        String getEventInfoQuery = "select eventIdx, eventInfoUrl FROM Event where eventIdx =?";
        Integer getEventInfoParams = eventIdx;
        return this.jdbcTemplate.queryForObject(getEventInfoQuery,
                (rs, rowNum) -> new GetEventInfo(
                        rs.getInt("eventIdx"),
                        rs.getString("eventInfoUrl")),
                getEventInfoParams);
    }

    public int deleteEvent(PatchEventReq patchEventReq){
        String modifyEventQuery = "update Event set isDeleted = ? where eventIdx = ?";
        Object[] modifyEventParams = new Object[]{patchEventReq.getIsDeleted(), patchEventReq.getEventIdx()};

        return this.jdbcTemplate.update(modifyEventQuery,modifyEventParams);
    }

    public int checkEventIdx(int eventIdx) {
        String checkUserIdxQuery = "select exists(select eventIdx from Event where eventIdx = ? AND Event.status != 'N')";
        int checkUserIdxParams = eventIdx;

        return this.jdbcTemplate.queryForObject(checkUserIdxQuery, int.class, checkUserIdxParams);
    }
}
