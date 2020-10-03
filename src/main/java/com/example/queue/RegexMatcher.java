package com.example.queue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {
    public static void main(String[] args) {
        System.out.println(match("phone", "phonepe"));
    }

    static boolean match(String regexStr, String str) {
        Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();

        // return true; // returning for now ,  will fix this method later
    }

    static boolean matchDuplicate(String regexStr, String str) {
        Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.lookingAt();
    }


}
