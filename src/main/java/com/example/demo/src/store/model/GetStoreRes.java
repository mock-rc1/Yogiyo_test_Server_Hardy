package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreRes {
    public Integer storeIdx;
    public Integer categoryIdx;
    public String storeName;
    public String storeRating;
    public String deliveryTime;
    public String minOrderPrice;
    public String deliveryTip;
    public String storeOpenTime;
    public String storePhoneNum;
    public String storeAddress;
    public String businessName;
    public String businessLicenseNum;
    public String storeFoodInfo;
    public String isDeleted;
    public Integer status;
    public String storeImageUrl;
}
