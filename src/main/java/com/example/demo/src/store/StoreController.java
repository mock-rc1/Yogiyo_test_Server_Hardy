package com.example.demo.src.store;

import com.example.demo.src.order.model.Order;
import com.example.demo.src.order.model.PatchOrderReq;
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
     * @return BaseResponse<GetCategoryRes>
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

    /**
     * 10. 가게 별 정보 조회 API
     * [GET] /stores/:storeIdx
     * @return BaseResponse<GetStoreRes>
     */
    @ResponseBody
    @GetMapping("/{storeIdx}")
    public BaseResponse<GetStoreRes> getStore(@PathVariable("storeIdx") int storeIdx) {
        try {
            GetStoreRes getStoreRes = storeProvider.getStore(storeIdx);
            return new BaseResponse<>(getStoreRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 16. 리뷰 조회 API
     * [GET] /stores/:storeIdx/reviews
     * @return BaseResponse<List<GetReviewRes>>
     */
    @ResponseBody
    @GetMapping("/{storeIdx}/reviews")
    public BaseResponse<List<GetReviewRes>> getReview(@PathVariable("storeIdx") int storeIdx) {
        try {
            List<GetReviewRes> getReviewRes = storeProvider.getReview(storeIdx);
            return new BaseResponse<>(getReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 17. 리뷰 삭제 API
     * [PATCH] /stores/:storeIdx/reviews/:reviewIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{storeIdx}/reviews/{reviewIdx}")
    public BaseResponse<String> deleteReview(@PathVariable("storeIdx") int storeIdx, @PathVariable("reviewIdx") int reviewIdx, @RequestBody Review review){
        try {
            PatchReviewReq patchReviewReq = new PatchReviewReq(storeIdx, reviewIdx, review.getIsDeleted());
            storeService.deleteReview(patchReviewReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}