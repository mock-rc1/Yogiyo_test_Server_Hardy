package com.example.demo.src.user;

import com.example.demo.utils.KakaoApiService;
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
     * [POST] /users/email-login
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

    /**
     * 7. 유저 정보 조회 API
     * [POST] /users/email-login/:userIdx
     * @return BaseResponse<GetUserRes>
     */

    @ResponseBody
    @PostMapping("/email-login/{userIdx}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<PostPatchRes> getUser(@PathVariable("userIdx") int userIdx,
                                            @RequestBody PostLoginReq postLoginReq) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if (userIdx != userIdxByJwt) {
                return new BaseResponse(FAIL_LOGIN);
            }
            PostPatchRes postPatchRes = userProvider.logInPatch(postLoginReq);
            return new BaseResponse<>(postPatchRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }

    /**
     * 8. 유저 닉네임 변경 API
     * [PATCH] /users/email-login/:userIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/email-login/{userIdx}")
    public BaseResponse<String> modifyUserName(@PathVariable("userIdx") int userIdx, @RequestBody User user){
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            if (userIdx != userIdxByJwt) {
                return new BaseResponse(FAIL_LOGIN);
            }
            PatchUserReq patchUserReq = new PatchUserReq(userIdx, user.getUserNickname());
            userService.modifyUserName(patchUserReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 9. 카카오 로그인 API
     * [POST] /users/kakao-login
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("/users/kakao-login")
    public BaseResponse<PostLoginRes> kakaoLogin(@RequestBody PostKaKaoLoginReq postKakaoLogin) {
        if (postKakaoLogin.getAccessToken() == null || postKakaoLogin.getAccessToken().isEmpty()) {
            return new BaseResponse<>(AUTH_KAKAO_EMPTY_TOKEN);
        }
        try {
            // 액세스 토큰으로 사용자 정보 받아온다.
            KaKaoUserInfo kaKaoUserInfo = KakaoApiService.getKakaoUserInfo(postKakaoLogin.getAccessToken());

            // 로그인 처리 or 회원가입 진행 후 jwt, userIdx 반환
            PostLoginRes postLoginRes = userProvider.kakaoLogin(kaKaoUserInfo);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception) {
            logger.warn("#3. " + exception.getStatus().getMessage());
            logger.warn(postKakaoLogin.toString());
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
