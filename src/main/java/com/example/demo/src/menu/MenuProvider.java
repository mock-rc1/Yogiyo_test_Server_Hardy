package com.example.demo.src.menu;


import com.example.demo.config.BaseException;
import com.example.demo.src.menu.model.*;
import com.example.demo.src.store.StoreDao;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class MenuProvider {

    private final MenuDao menuDao;
    private final StoreDao storeDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MenuProvider(MenuDao menuDao, StoreDao storeDao, JwtService jwtService) {
        this.menuDao = menuDao;
        this.storeDao = storeDao;
        this.jwtService = jwtService;
    }

    public List<GetMenuRes> getMenus(int storeIdx) throws BaseException {
        if (storeDao.checkStoreIdx(storeIdx) == 0) {
            throw new BaseException(EMPTY_STORE_ID);
        }
        try {
            List<GetMenuRes> getMenuRes = menuDao.getMenus(storeIdx);
            return getMenuRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSingleMenuRes> getMenu(int storeIdx, int menuIdx) throws BaseException {
        if (storeDao.checkStoreIdx(storeIdx) == 0) {
            throw new BaseException(EMPTY_STORE_ID);
        }
        if (storeDao.checkMenuIdx(menuIdx) == 0) {
            throw new BaseException(EMPTY_MENU_ID);
        }
        try {
            List<GetSingleMenuRes> getSingleMenuRes = menuDao.getMenu(storeIdx, menuIdx);
            return getSingleMenuRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}