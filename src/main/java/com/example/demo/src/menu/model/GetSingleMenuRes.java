package com.example.demo.src.menu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetSingleMenuRes {
    public Integer menuIdx;
    public String menuName;
    public String menuPrice;
    public String menuImageUrl;
    public String menuInfo;
}
