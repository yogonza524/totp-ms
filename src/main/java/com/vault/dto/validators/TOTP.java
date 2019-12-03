package com.vault.dto.validators;

import com.vault.dto.TOTPRequestPayload;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TOTPPayloadValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TOTP {
    String message() default "Invalid TOTP Payload";
    Class<?>[] groups() default {};

    Class<? extends TOTPRequestPayload>[] payload() default {};
}
