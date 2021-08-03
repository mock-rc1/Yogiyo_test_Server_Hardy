package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationRegex {
    public static boolean isRegexEmail(String target) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    public static boolean isRegexPassword(String target) {
        // 영문/숫자/특수문자 2가지 이상 조합
        // 8-20자 비밀번호
        String regexEnglishAndNum = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$";
        String regexEnglishAndSpecial = "^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,20}$";
        String regexSpecialAndNum = "^(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,20}$";

        Pattern pattern = Pattern.compile(regexEnglishAndNum, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        boolean resultEnglishAndNum = matcher.find();

        pattern = Pattern.compile(regexEnglishAndSpecial, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(target);
        boolean resultEnglishAndSpecial = matcher.find();

        pattern = Pattern.compile(regexSpecialAndNum, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(target);
        boolean resultSpecialAndNum = matcher.find();

        if(!(resultEnglishAndNum || resultEnglishAndSpecial || resultSpecialAndNum)){
            return false;
        }
        return true;
    }

//    public static boolean isRegexUserName(String target) {
//        //한글 이름, 2-20자 이
//        String regex = "^[가-힣]{2,20}$";
//        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(target);
//        return matcher.find();
//    }
}

