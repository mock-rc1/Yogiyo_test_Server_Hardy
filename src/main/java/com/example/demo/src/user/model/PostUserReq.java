package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private Integer termsAndConditions;
    private Integer personalInfo;
    private Integer financialTrans;
    private Integer aboveFourteen;
    private Integer benefitAlarm;
}
