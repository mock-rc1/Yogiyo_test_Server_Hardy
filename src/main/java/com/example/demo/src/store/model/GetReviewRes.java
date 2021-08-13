package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewRes {
    private Integer reviewIdx;
    private Integer storeIdx;
    private Integer menuIdx;
    private Integer userIdx;
    private String userName;
    private Integer reviewRating;
    private String reviewContent;
    private String reviewImageUrl;
    private Integer tasteRating;
    private Integer amountRating;
    private Integer deliveryRating;
}
