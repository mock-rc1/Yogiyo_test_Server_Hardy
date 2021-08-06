package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreCategoryRes {
    public Integer storeIdx;
    public Integer categoryIdx;
    public String storeName;
    public String storeRating;
    public String deliveryTime;
    public String deliveryTip;
    public String storeAddress;
    public String storeImageUrl;
    public String storeLogoUrl;
}
