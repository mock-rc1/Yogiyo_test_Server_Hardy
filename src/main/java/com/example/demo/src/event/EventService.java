package com.example.demo.src.event;



import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.order.model.PatchOrderReq;
import com.example.demo.src.event.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class EventService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EventDao eventDao;
    private final EventProvider eventProvider;
    private final JwtService jwtService;


    @Autowired
    public EventService(EventDao eventDao, EventProvider eventProvider, JwtService jwtService) {
        this.eventDao = eventDao;
        this.eventProvider = eventProvider;
        this.jwtService = jwtService;
    }

    public void deleteEvent(PatchEventReq patchEventReq) throws BaseException {
        try{
            int result = eventDao.deleteEvent(patchEventReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}