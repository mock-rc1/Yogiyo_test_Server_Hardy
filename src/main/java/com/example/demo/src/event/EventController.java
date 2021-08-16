package com.example.demo.src.event;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.event.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final EventProvider eventProvider;
    private final EventService eventService;

    public EventController(EventProvider eventProvider, EventService eventService) {
        this.eventProvider = eventProvider;
        this.eventService = eventService;
    }

    /**
     * 18. 이벤트 전체 조회 API
     * [GET] /events
     *
     * @return BaseResponse<List<GetEvents>>
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetEvents>> getEvents() {
        try {
            List<GetEvents> result = eventProvider.getEvents();
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 19. 이벤트 상세 조회 API
     * [GET] /events/:eventIdx
     *
     * @return BaseResponse<List<GetEventsInfo>>
     */
    @ResponseBody
    @GetMapping("/{eventIdx}")
    public BaseResponse<GetEventInfo> getEventInfo(@PathVariable("eventIdx") int eventIdx) {
        try {
            GetEventInfo getEventInfo = eventProvider.getEventInfo(eventIdx);
            return new BaseResponse<>(getEventInfo);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 20. 이벤트 배너 삭제 API
     * [PATCH] /events/:eventIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{eventIdx}")
    public BaseResponse<String> deleteEvent(@PathVariable("eventIdx") int eventIdx, @RequestBody Event event){
        try {
            PatchEventReq patchEventReq = new PatchEventReq(eventIdx, event.getIsDeleted());
            eventService.deleteEvent(patchEventReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
