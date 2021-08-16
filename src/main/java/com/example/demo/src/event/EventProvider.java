package com.example.demo.src.event;

import com.example.demo.config.BaseException;
import com.example.demo.src.event.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;


@Service
public class EventProvider {
    private final EventDao eventDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public EventProvider(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<GetEvents> getEvents() throws BaseException {
        try {
            List<GetEvents> result = eventDao.getEvents();
            return result;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetEventInfo getEventInfo(Integer eventIdx) throws BaseException{
        try{
            GetEventInfo getEventsInfo = eventDao.getEventInfo(eventIdx);
            return getEventsInfo;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}