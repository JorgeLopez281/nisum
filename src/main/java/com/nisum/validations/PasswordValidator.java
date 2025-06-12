package com.nisum.validations;

import com.nisum.dto.request.CreateUserRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidationsAnnotation, CreateUserRequestDTO> {

    @Override
    public boolean isValid(CreateUserRequestDTO userRequestDTO, ConstraintValidatorContext context) {
        if (userRequestDTO.getPassword() == null || userRequestDTO.getName() == null) return true;

        String numberOnPassword = userRequestDTO.getPassword().replaceAll("\\D", "");
        for (int i = 0; i <= numberOnPassword.length() - 4; i++) {
            boolean isAscending = true;
            boolean isDescending = true;

            for (int j = 0; j < 3; j++) {
                int current = Character.getNumericValue(numberOnPassword.charAt(i + j));
                int next = Character.getNumericValue(numberOnPassword.charAt(i + j + 1));

                if (current + 1 != next) isAscending = false;
                if (current - 1 != next) isDescending = false;
            }

            if (isAscending || isDescending) {
                return false;
            }
        }

        String[] nameSplit = userRequestDTO.getName().split(" ");
        for (String name : nameSplit) {
            if (userRequestDTO.getPassword().toLowerCase().contains(name.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
