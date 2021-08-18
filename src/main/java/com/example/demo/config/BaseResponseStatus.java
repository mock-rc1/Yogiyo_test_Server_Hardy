package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    FAIL_LOGIN(false,2004,"로그인 상태가 아닙니다."),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),
    AUTH_KAKAO_EMPTY_TOKEN(false, 2011, "액세스토큰을 입력해주세요."),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),
    POST_USERS_LENGTH_EMAIL(false,2018,"이메일 길이는 45자 이하이어야 합니다."),
    POST_USERS_EMPTY_PASSWORD(false, 2019, "비밀번호를 입력해주세요."),
    POST_USERS_INVALID_PASSWORD(false, 2020, "영문/숫자/특수문자 중 2가지 이상을 조합한 8~20자 비밀번호로 입력해주세요."),
    POST_USERS_LENGTH_USERNAME(false, 2021, "닉네임은 45자 이하이어야 합니다."),
    POST_USERS_MANDATORY_TERMS(false, 2022, "이용약관에 동의하셔야 합니다."),
    POST_USERS_MANDATORY_INFO(false, 2023, "개인정보 수집 및 이용 동의를 하셔야 합니다."),
    POST_USERS_MANDATORY_TRANS(false, 2024, "전자금융거래 이용약관에 동의하셔야 합니다."),
    POST_USERS_MANDATORY_FOURTEEN(false, 2025, "만 14세 이상 이용자만 가입이 가능합니다."),

    // stores
    EMPTY_CATEGORY_ID(false, 2026, "카테고리 인덱스를 입력해주세요."),
    EMPTY_STORE_ID(false, 2027, "가게 인덱스를 입력해주세요."),
    EMPTY_MENU_ID(false, 2028, "메뉴 인덱스를 입력해주세요."),
    EMPTY_REVIEW_ID(false, 2029, "리뷰 인덱스를 입력해주세요."),
    EMPTY_EVENT_ID(false, 2030, "이메일 인덱스를 입력해주세요."),

    // [POST] /orders
    POST_ORDERS_EMPTY_MENU_ID(false, 2031, "주문 메뉴 인덱스를 입력해주세요."),
    POST_ORDERS_EMPTY_MENU(false, 2032, "메뉴 이름을 입력해주세요."),
    POST_ORDERS_LENGTH_MENU(false, 2033, "메뉴 이름는 20자 이하이어야 합니다."),
    POST_ORDERS_EMPTY_MENU_QUANTITY(false, 2034, "메뉴 수량을 입력해주세요."),
    POST_ORDERS_EMPTY_MENU_PRICE(false, 2035, "메뉴 가격을 입력해주세요."),
    POST_ORDERS_EMPTY_USER_ADDRESS(false, 2036, "주소를 입력해주세요."),
    POST_ORDERS_LENGTH_USER_ADDRESS(false, 2037, "주소를 50자 이내로 적어주시길 바랍니다."),
    POST_ORDERS_EMPTY_TOTAL_PRICE(false, 2038, "총 가격을 입력해주세요."),
    POST_ORDERS_EMPTY_DELIVERY_TIP(false, 2039, "배달 요금을 입력해주세요."),
    POST_ORDERS_EMPTY_SAFE_DELIVERY(false, 2040, "안전 배달 여부를 선택해주세요."),
    POST_ORDERS_EMPTY_TABLEWARE(false, 2041, "수저포크 필요 여부를 선택해주세요."),
    POST_ORDERS_EMPTY_PAYTYPE(false, 2042, "결제 방법을 선택해주세요."),

    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),
    QUIT_USER(false,3015,"탈퇴한 유저입니다."),
    FAILED_TO_KAKAO_AUTH(false, 3016, "카카오 유저 정보 조회에 실패하였습니다."),
    FAILED_TO_KAKAO_EMAIL(false, 3017, "카카오 정보에 등록된 이메일이 없습니다. 이메일을 추가 입력해주세요."),




    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");


    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
