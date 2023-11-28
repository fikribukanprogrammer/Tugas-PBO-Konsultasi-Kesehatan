package com.example.tugasprojek.validation;

public class RegexEmailValidation {
    public static  boolean regexEmailValidationPattern(String email){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if(email.matches(regex)){
            return true;
        }
        return false;
    }
}
