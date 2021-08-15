package com.example.demo.src.event;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.event.model.GetEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final EventProvider eventProvider;

    public EventController(EventProvider eventProvider) {
        this.eventProvider = eventProvider;
    }

    /**
     * 45. 이벤트 전체 조회 API
     * [GET] /events
     *
     * @return BaseResponse<List < GetEvents>>
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetEvents>> getEvents() {
        try {
            List<GetEvents> result = eventProvider.getEvents();
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            logger.warn("#45. " + exception.getStatus().getMessage());
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
