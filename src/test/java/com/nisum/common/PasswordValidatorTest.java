package com.nisum.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PasswordValidatorTest {

    @Mock
    private PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
        // Ejemplo de regex: mínimo 8 caracteres, al menos 1 mayúscula, 1 minúscula, 1 dígito y 1 carácter especial
        String regex = "^[A-Za-z0-9_!@#$%.^&*()\\-+=]{8,}$";
        ReflectionTestUtils.setField(passwordValidator, "passwordRegex", regex);
    }

    @Test
    void testValidPassword_lettersAndNumbers() {
        assertTrue(passwordValidator.isValidPassword("Password1"));
    }

    @Test
    void testValidPassword_withSymbols() {
        assertTrue(passwordValidator.isValidPassword("Pass@123"));
        assertTrue(passwordValidator.isValidPassword("Hello!@#"));
    }

    @Test
    void testInvalidPassword_tooShort() {
        assertFalse(passwordValidator.isValidPassword("Abc1!"));
    }

    @Test
    void testInvalidPassword_withSpace() {
        assertFalse(passwordValidator.isValidPassword("Password 1"));
    }

    @Test
    void testInvalidPassword_withInvalidSymbol() {
        assertFalse(passwordValidator.isValidPassword("Passwørd123"));
    }

    @Test
    void testInvalidPassword_null() {
        assertFalse(passwordValidator.isValidPassword(null));
    }

    @Test
    void testInvalidPassword_emptyString() {
        assertFalse(passwordValidator.isValidPassword(""));
    }
}
