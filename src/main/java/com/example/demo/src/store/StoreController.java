package com.example.demo.src.store;

import com.example.demo.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.store.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/stores")
public class StoreController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final StoreProvider storeProvider;
    @Autowired
    private final StoreService storeService;
    @Autowired
    private final JwtService jwtService;


    public StoreController(StoreProvider storeProvider, StoreService storeService, JwtService jwtService) {
        this.storeProvider = storeProvider;
        this.storeService = storeService;
        this.jwtService = jwtService;
    }

    /**
     * 4. 메인화면 API
     * [GET] /stores
     * @return BaseResponse<GetStoreRes>
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetCategoryRes>> getCategories() {
        try {
            List<GetCategoryRes> getCategoriesRes = storeProvider.getCategories();
            return new BaseResponse<>(getCategoriesRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 5. 전체 카테고리 API
     * [GET] /stores/categories
     * 6. 카테고리 별 식당 조회 API
     * [GET] /stores/categories?categoryIdx=
     * @return BaseResponse<List<GetStoreCategoryRes>>
     */

    @ResponseBody
    @GetMapping("/categories")
    public BaseResponse<List<GetStoreCategoryRes>> getStoreCategories(@RequestParam(required = false) Integer categoryIdx) {
        try {
            if (categoryIdx == null) {
                List<GetStoreCategoryRes> getStoreCategoriesRes = storeProvider.getStoreCategories();
                return new BaseResponse<>(getStoreCategoriesRes);
            }
            List<GetStoreCategoryRes> getStoreCategories = storeProvider.getStoreCategoriesById(categoryIdx);
            return new BaseResponse<>(getStoreCategories);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}