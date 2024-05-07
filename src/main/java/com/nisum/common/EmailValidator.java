package com.nisum.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator {

    @Value("${email.regex}")
    private String emailRegex;

    public boolean isValidEmail(String password) {

        Pattern pattern = Pattern.compile(emailRegex);

        if (password == null || password.isEmpty()) {
            return false;
        }

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
