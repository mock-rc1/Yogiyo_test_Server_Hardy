package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.*;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.User;
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

    /**
     * 13. 주문하기 API
     * [POST] /orders
     * @return BaseResponse<PostOrderRes>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostOrderRes> createOrder(@RequestBody PostOrderReq postOrderReq) {
        try {
            // 유저 JWT 확인
            int userIdxByJwt = jwtService.getUserIdx();
            if (userIdxByJwt != postOrderReq.getUserIdx()) {
                return new BaseResponse<>(INVALID_USER_JWT);
            } else if (postOrderReq.getOrderMenuIdx() == null || postOrderReq.getOrderMenuIdx() == 0) {
                return new BaseResponse<>(POST_ORDERS_EMPTY_MENU_ID);
            } else if (postOrderReq.getMenuIdx() == null || postOrderReq.getMenuIdx() == 0) {
                return new BaseResponse<>(EMPTY_MENU_ID);
            } else if (postOrderReq.getMenuName() == null) {
                return new BaseResponse<>(POST_ORDERS_EMPTY_MENU);
            } else if (postOrderReq.getMenuName().length() > 20) {
                return new BaseResponse<>(POST_ORDERS_LENGTH_MENU);
            } else if (postOrderReq.getMenuQuantity() == null) {
                return new BaseResponse<>(POST_ORDERS_EMPTY_MENU_QUANTITY);
            } else if (postOrderReq.getMenuPrice() == null) {
                return new BaseResponse<>(POST_ORDERS_EMPTY_MENU_PRICE);
            } else if (postOrderReq.getUserAddress() == null) {
                return new BaseResponse<>(POST_ORDERS_EMPTY_USER_ADDRESS);
            } else if (postOrderReq.getUserAddress().length() > 50) {
                return new BaseResponse<>(POST_ORDERS_LENGTH_USER_ADDRESS);
            } else if (postOrderReq.getStoreIdx() == null || postOrderReq.getStoreIdx() == 0) {
                return new BaseResponse<>(EMPTY_STORE_ID);
            } else if (postOrderReq.getTotalPrice() == null) {
                return new BaseResponse<>(POST_ORDERS_EMPTY_TOTAL_PRICE);
            } else if (postOrderReq.getDeliveryTip() == null) {
                return new BaseResponse<>(POST_ORDERS_EMPTY_DELIVERY_TIP);
            } else if (postOrderReq.getSafeDelivery() == null) {
                return new BaseResponse<>(POST_ORDERS_EMPTY_SAFE_DELIVERY);
            } else if (postOrderReq.getTableware() == null) {
                return new BaseResponse<>(POST_ORDERS_EMPTY_TABLEWARE);
            } else if (postOrderReq.getPayType() == null) {
                return new BaseResponse<>(POST_ORDERS_EMPTY_PAYTYPE);
            }
            PostOrderRes postOrderRes = orderService.createOrder(postOrderReq);
            return new BaseResponse<>(postOrderRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 14. 주문내역 조회 API
     * [GET] /orders/:userIdx
     * @return BaseResponse<List<GetUserRes>>
     */
    @ResponseBody
    @GetMapping("/{userIdx}")
    public BaseResponse<List<GetOrderRes>> getOrder(@PathVariable("userIdx") int userIdx) {
        // Get Users
        try {
            int userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<GetOrderRes> getOrderRes = orderProvider.getOrder(userIdx);
            return new BaseResponse<>(getOrderRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 15. 주문내역 삭제 API
     * [PATCH] /orders/:userIdx/:orderMenuIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{userIdx}/{orderMenuIdx}")
    public BaseResponse<String> deleteOrder(@PathVariable("userIdx") int userIdx, @PathVariable("orderMenuIdx") int orderMenuIdx, @RequestBody Order order){
        try {
            int userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PatchOrderReq patchOrderReq = new PatchOrderReq(orderMenuIdx, userIdx, order.getIsDeleted());
            orderService.deleteOrder(patchOrderReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}