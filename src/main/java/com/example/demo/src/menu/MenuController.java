package com.example.demo.src.menu;

import com.example.demo.src.menu.model.GetMenuRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.menu.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/menus")
public class MenuController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final MenuProvider menuProvider;
    @Autowired
    private final MenuService menuService;
    @Autowired
    private final JwtService jwtService;


    public MenuController(MenuProvider menuProvider, MenuService menuService, JwtService jwtService) {
        this.menuProvider = menuProvider;
        this.menuService = menuService;
        this.jwtService = jwtService;
    }