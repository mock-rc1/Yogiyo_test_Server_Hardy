package com.example.demo.src.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;
import static com.example.demo.utils.ValidationRegex.isRegexPassword;

@RestController
@RequestMapping("/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;




    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService){
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * 1. 회원가입 API
     * [POST] /users
     * @return BaseResponse<PostUserRes>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        if(postUserReq.getUserEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        } else if(!isRegexEmail(postUserReq.getUserEmail())){
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        } else if(postUserReq.getUserEmail().length() > 45){
            return new BaseResponse<>(POST_USERS_LENGTH_EMAIL);
        } else if(postUserReq.getUserPassword() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
        } else if(!isRegexPassword(postUserReq.getUserPassword())){
            return new BaseResponse<>(POST_USERS_INVALID_PASSWORD);
        } else if(postUserReq.getUserNickname().length() > 45){
            return new BaseResponse<>(POST_USERS_LENGTH_USERNAME);
        }
        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     * 2. 이메일 로그인 API
     * [POST] /users/email-logIn
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("/email-login")
    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq){
        try{
            if(postLoginReq.getUserEmail() == null){
                return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
            } else if(!isRegexEmail(postLoginReq.getUserEmail())){
                return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
            } else if(postLoginReq.getUserEmail().length() > 45){
                return new BaseResponse<>(POST_USERS_LENGTH_EMAIL);
            } else if(postLoginReq.getUserPassword() == null){
                return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
            } else if(!isRegexPassword(postLoginReq.getUserPassword())) {
                return new BaseResponse<>(POST_USERS_INVALID_PASSWORD);
            }
            PostLoginRes postLoginRes = userProvider.logIn(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 3. 자동로그인 API
     * [GET] /users/:userIdx/auto-login
     * @return BaseResponse<CheckAutoLoginRes>
     */
    @ResponseBody
    @GetMapping("/{userIdx}/auto-login")
    public BaseResponse<AutoLoginRes> AutoLogin(@PathVariable int userIdx) {
        try {
            int userIdxByJwt = jwtService.getUserIdx();
            if (userIdx != userIdxByJwt) {
                return new BaseResponse(FAIL_LOGIN);
            }
            return new BaseResponse<>(new AutoLoginRes(1));
        } catch (BaseException exception) {
            return new BaseResponse<>(FAIL_LOGIN);
        }
    }
}
