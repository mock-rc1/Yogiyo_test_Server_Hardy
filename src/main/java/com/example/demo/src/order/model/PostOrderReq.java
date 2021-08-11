package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostOrderReq {
    private Integer menuIdx;
    private String menuName;
    private String menuDetail;
    private Integer menuQuantity;
    private Integer totalPrice;
    private String userAddress;
    private Integer storeIdx;
    private Integer orderPrice;
    private Integer deliveryTip;
    private String safeDelivery;
    private String tableware;
    private String payType;
    private Integer userIdx;
}
