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

//    /**
//     * 회원 조회 API
//     * [GET] /users
//     * 회원 번호 및 이메일 검색 조회 API
//     * [GET] /users? Email=
//     * @return BaseResponse<List<GetUserRes>>
//     */
//    //Query String
//    @ResponseBody
//    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
//    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {
//        try{
//            if(Email == null){
//                List<GetUserRes> getUsersRes = userProvider.getUsers();
//                return new BaseResponse<>(getUsersRes);
//            }
//            // Get Users
//            List<GetUserRes> getUsersRes = userProvider.getUsersByEmail(Email);
//            return new BaseResponse<>(getUsersRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 회원 1명 조회 API
//     * [GET] /users/:userIdx
//     * @return BaseResponse<GetUserRes>
//     */
//    // Path-variable
//    @ResponseBody
//    @GetMapping("/{userIdx}") // (GET) 127.0.0.1:9000/app/users/:userIdx
//    public BaseResponse<GetUserRes> getUser(@PathVariable("userIdx") int userIdx) {
//        // Get Users
//        try{
//            GetUserRes getUserRes = userProvider.getUser(userIdx);
//            return new BaseResponse<>(getUserRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//
//    }

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
     * 이메일 로그인 API
     * [POST] /users/email-logIn
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("/email-logIn")
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
//
//    /**
//     * 유저정보변경 API
//     * [PATCH] /users/:userIdx
//     * @return BaseResponse<String>
//     */
//    @ResponseBody
//    @PatchMapping("/{userIdx}")
//    public BaseResponse<String> modifyUserName(@PathVariable("userIdx") int userIdx, @RequestBody User user){
//        try {
//            //jwt에서 idx 추출.
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //같다면 유저네임 변경
//            PatchUserReq patchUserReq = new PatchUserReq(userIdx,user.getUserName());
//            userService.modifyUserName(patchUserReq);
//
//            String result = "";
//        return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }


}
