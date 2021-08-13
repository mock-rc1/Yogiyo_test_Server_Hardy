package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostOrderReq {
    private Integer orderMenuIdx;
    private Integer menuIdx;
    private String menuName;
    private Integer menuQuantity;
    private String menuPrice;
    private String userAddress;
    private Integer storeIdx;
    private String totalPrice;
    private String deliveryTip;
    private Integer safeDelivery;
    private Integer tableware;
    private String payType;
    private Integer userIdx;
}
