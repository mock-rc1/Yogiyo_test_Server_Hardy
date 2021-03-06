package com.example.demo.src.store;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.store.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class StoreProvider {

    private final StoreDao storeDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StoreProvider(StoreDao storeDao, JwtService jwtService) {
        this.storeDao = storeDao;
        this.jwtService = jwtService;
    }

    public List<GetCategoryRes> getCategories() throws BaseException {
        try {
            List<GetCategoryRes> getCategoriesRes = storeDao.getCategories();
            return getCategoriesRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetStoreCategoryRes> getStoreCategories() throws BaseException{
        try{
            List<GetStoreCategoryRes> getStoreCategoriesRes = storeDao.getStoreCategories();
            return getStoreCategoriesRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetStoreCategoryRes> getStoreCategoriesById(Integer categoryIdx) throws BaseException{
        if (storeDao.checkCategoryIdx(categoryIdx) == 0) {
            throw new BaseException(EMPTY_CATEGORY_ID);
        }
        try{
            List<GetStoreCategoryRes> GetStoreCategoriesRes = storeDao.getStoreCategoriesById(categoryIdx);
            return GetStoreCategoriesRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetStoreRes getStore(int storeIdx) throws BaseException {
        if (storeDao.checkStoreIdx(storeIdx) == 0) {
            throw new BaseException(EMPTY_STORE_ID);
        }
        try {
            GetStoreRes getStoreRes = storeDao.getStore(storeIdx);
            return getStoreRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetReviewRes> getReview(int storeIdx) throws BaseException{
        if (storeDao.checkStoreIdx(storeIdx) == 0) {
            throw new BaseException(EMPTY_STORE_ID);
        }
        try{
            List<GetReviewRes> GetReviewsRes = storeDao.getReview(storeIdx);
            return GetReviewsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}