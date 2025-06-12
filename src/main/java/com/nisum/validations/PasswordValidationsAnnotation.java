package com.nisum.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidationsAnnotation {
    String message() default "La contraseña no puede contener secuencias numericas de mas de 4 digitos o contener el nombre u apellido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
