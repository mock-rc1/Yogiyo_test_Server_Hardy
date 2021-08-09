package com.example.demo.src.menu;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.menu.model.*;
import com.example.demo.src.store.model.GetStoreRes;
import com.example.demo.utils.AES128;
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
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MenuProvider(MenuDao menuDao, JwtService jwtService) {
        this.menuDao = menuDao;
        this.jwtService = jwtService;
    }

    public List<GetMenuRes> getMenus(int storeIdx) throws BaseException {
        try {
            List<GetMenuRes> getMenuRes = menuDao.getMenus(storeIdx);
            return getMenuRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSingleMenuRes> getMenu(int storeIdx, int menuIdx) throws BaseException {
        try {
            List<GetSingleMenuRes> getSingleMenuRes = menuDao.getMenu(storeIdx, menuIdx);
            return getSingleMenuRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}