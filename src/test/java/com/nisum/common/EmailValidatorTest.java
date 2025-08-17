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
public class EmailValidatorTest {

    @Mock
    private EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        ReflectionTestUtils.setField(emailValidator, "emailRegex", regex);
    }

    @Test
    void testValidEmail() {
        assertTrue(emailValidator.isValidEmail("test@example.com"));
        assertTrue(emailValidator.isValidEmail("user.name+tag@domain.co"));
    }

    @Test
    void testInvalidEmail_NoAtSymbol() {
        assertFalse(emailValidator.isValidEmail("testexample.com"));
    }

    @Test
    void testInvalidEmail_EmptyString() {
        assertFalse(emailValidator.isValidEmail(""));
    }

    @Test
    void testInvalidEmail_Null() {
        assertFalse(emailValidator.isValidEmail(null));
    }

    @Test
    void testInvalidEmail_BadDomain() {
        assertFalse(emailValidator.isValidEmail("user@.com"));
    }
}
