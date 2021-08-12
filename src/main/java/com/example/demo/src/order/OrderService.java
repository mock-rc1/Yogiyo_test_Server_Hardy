//package com.example.demo.src.order;
//
//import com.example.demo.config.BaseException;
//import com.example.demo.src.order.model.PostOrderMenus;
//import com.example.demo.src.order.model.PostOrderReq;
//import com.example.demo.src.order.model.PostOrderRes;
//import com.example.demo.utils.JwtService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import static com.example.demo.config.BaseResponseStatus.*;
//
//@Service
//public class OrderService {
//    final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private final OrderDao orderDao;
//    private final OrderProvider orderProvider;
//    private final JwtService jwtService;
//
//
//    @Autowired
//    public OrderService(OrderDao orderDao, OrderProvider orderProvider, JwtService jwtService) {
//        this.orderDao = orderDao;
//        this.orderProvider = orderProvider;
//        this.jwtService = jwtService;
//    }
//
//    public PostOrderRes postOrder(PostOrderReq postOrderReq) throws BaseException {
//            try {
//                int createdOrderIdx = orderDao.insertOrder(postOrderReq);
//
//                // insert orderMenus
//                for (PostOrderMenus orderMenus : postOrderReq.getOrderMenus()) {
//                    orderDao.insertOrderMenu(orderMenus, createdOrderIdx);
//                }
//                PostOrderRes postOrderRes = new PostOrderRes(createdOrderIdx);
//                return postOrderRes;
//            } catch (Exception exception) {
//                throw new BaseException(DATABASE_ERROR);
//            }
//        }
//    }
//}