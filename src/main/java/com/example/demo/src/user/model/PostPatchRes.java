package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostPatchRes {
    private int userIdx;
    private String userEmail;
    private String userPhoneNum;
    private String userNickname;
    private String passwordStar;
}
