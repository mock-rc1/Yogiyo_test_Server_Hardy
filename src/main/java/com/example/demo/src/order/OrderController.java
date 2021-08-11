package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final OrderProvider orderProvider;
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final JwtService jwtService;


    public OrderController(OrderProvider orderProvider, OrderService orderService, JwtService jwtService) {
        this.orderProvider = orderProvider;
        this.orderService = orderService;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @PostMapping("/orders")
    public BaseResponse<PostOrderRes> postOrder(@RequestBody PostOrderReq postOrderReq) {
    try
    {
        // 유저 JWT 확인
        int userIdxByJwt = jwtService.getUserIdx();
        if (userIdxByJwt != postOrderReq.getUserIdx()) {
            return new BaseResponse<>(INVALID_USER_JWT);
        }
        PostOrderRes postOrderRes = orderService.postOrder(postOrderReq);
        return new BaseResponse<>(postOrderRes);
    } catch(
    BaseException exception)

    {
        logger.warn("#28 " + exception.getStatus().getMessage());
        logger.warn(postOrderReq.toString());
        return new BaseResponse<>(exception.getStatus());
    }
}