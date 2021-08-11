package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostOrderMenus {
    private Integer menuIdx;
    private String menuName;
    private String menuDetail;
    private Integer menuQuantity;
    private Integer totalPrice;
}