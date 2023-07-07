package com.mera.thar.back_app.Entity;

public class SystemUtils {
    public static boolean validatePassword(String password) {
        if (password.length() >= 6) {
            boolean hasUppercase = !password.equals(password.toLowerCase());
            boolean hasNumber = password.matches(".*\\d.*");
            boolean hasLowercase = !password.equals(password.toUpperCase());
            return hasUppercase && hasNumber && hasLowercase;
        } else {
            return false;
        }
    }
}
