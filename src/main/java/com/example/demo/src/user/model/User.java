package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int userIdx;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userNickname;
    private String userPhoneNum;
    private String userAddress;
    private Integer storeBoss;
    private Integer status;
}
