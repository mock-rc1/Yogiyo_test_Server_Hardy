package com.example.demo.src.menu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMenuRes {
    public Integer menuIdx;
    public Integer storeIdx;
    public String menuName;
    public String menuCategory;

}
