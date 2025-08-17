package com.nisum.validations;

import com.nisum.dto.request.CreateUserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PasswordValidatorTest {

    @Mock
    private PasswordValidator passwordValidator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    void testValidPassword_ok() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO();
        dto.setName("John Doe");
        dto.setPassword("SafePass123!");

        assertTrue(passwordValidator.isValid(dto, context));
    }

    @Test
    void testNullPassword_returnsTrue() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO();
        dto.setName("John Doe");
        dto.setPassword(null);

        assertTrue(passwordValidator.isValid(dto, context));
    }

    @Test
    void testNullName_returnsTrue() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO();
        dto.setName(null);
        dto.setPassword("SomePassword1!");

        assertTrue(passwordValidator.isValid(dto, context));
    }

    @Test
    void testPasswordContainsAscendingNumbers_invalid() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO();
        dto.setName("Alice");
        dto.setPassword("Test1234!");

        assertFalse(passwordValidator.isValid(dto, context));
    }

    @Test
    void testPasswordContainsDescendingNumbers_invalid() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO();
        dto.setName("Alice");
        dto.setPassword("Pass4321!");

        assertFalse(passwordValidator.isValid(dto, context));
    }

    @Test
    void testPasswordContainsUserName_invalid() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO();
        dto.setName("Carlos Lopez");
        dto.setPassword("Lopez2024!");

        assertFalse(passwordValidator.isValid(dto, context));
    }

    @Test
    void testPasswordContainsUserName_caseInsensitive_invalid() {
        CreateUserRequestDTO dto = new CreateUserRequestDTO();
        dto.setName("Maria");
        dto.setPassword("maria2025!");

        assertFalse(passwordValidator.isValid(dto, context));
    }
}
