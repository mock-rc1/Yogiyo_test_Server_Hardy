package com.example.demo.src.menu;

import com.example.demo.src.menu.model.GetMenuRes;
import com.example.demo.src.user.model.GetUserRes;
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
@RequestMapping("")
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

    /**
     * 11. 특정 가게 음식 리스트 조회 API
     * [GET] /stores/:storeIdx/menus/:menuIdx
     * @return BaseResponse<GetMenuRes>
     */

    @ResponseBody
    @GetMapping("/stores/{storeIdx}/menus")
    public BaseResponse<List<GetMenuRes>> getMenus(@PathVariable("storeIdx") int storeIdx) {
        try{
            List<GetMenuRes> getMenuRes = menuProvider.getMenus(storeIdx);
            return new BaseResponse<>(getMenuRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 12. 특정 음식 정보 조회 API
     * [GET] /menus/:storeIdx
     * @return BaseResponse<GetMenuRes>
     */

    @ResponseBody
    @GetMapping("/stores/{storeIdx}/menus/{menuIdx}")
    public BaseResponse<List<GetSingleMenuRes>> getMenu(@PathVariable("storeIdx") int storeIdx,
                                                   @PathVariable("menuIdx") int menuIdx) {
        try{
            List<GetSingleMenuRes> getSingleMenuRes = menuProvider.getMenu(storeIdx, menuIdx);
            return new BaseResponse<>(getSingleMenuRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}