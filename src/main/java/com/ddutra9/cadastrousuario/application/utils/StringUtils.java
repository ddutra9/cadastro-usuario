package com.ddutra9.cadastrousuario.application.utils;

public class StringUtils {

    public static boolean containsUpperCaseLetter(String s){
        if(s == null || s.isBlank()) {
            return false;
        }

        for (char c : s.toCharArray()) {
            if(Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsNumber(String s){
        if(s == null || s.isBlank()) {
            return false;
        }

        for (char c : s.toCharArray()) {
            if(Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

}
