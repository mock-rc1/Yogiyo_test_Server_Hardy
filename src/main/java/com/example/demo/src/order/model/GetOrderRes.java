package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderRes {
    private Integer orderMenuIdx;
    private String menuName;
    private Integer storeIdx;
    private String storeName;
    private String storeLogoUrl;
    private Integer userIdx;
}
