package com.vault.dto.validators;

import com.vault.dto.TOTPRequestPayload;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class TOTPPayloadValidator implements ConstraintValidator<TOTP, TOTPRequestPayload> {

    @Override
    public boolean isValid(TOTPRequestPayload value, ConstraintValidatorContext context) {
        System.out.println(value);
        if (value ==  null) {
            context.buildConstraintViolationWithTemplate("TOTP can't be null")
            .addConstraintViolation();
            return false;
        }
        if (value.getQrSize() != null && value.getQrSize() > 0 && (!value.isGenerate() || !value.isExported())) {
            context
                    .buildConstraintViolationWithTemplate("QR Size is not allowed")
                    .addConstraintViolation()
            ;
            return false;
        }
        if (value.getSkew() != null && (value.getSkew() < 0 || value.getSkew() > 1)) {
            context
                    .buildConstraintViolationWithTemplate("Skew value is invalid")
                    .addConstraintViolation();
            return false;
        }
        if (value.getDigits() != null && value.getDigits() != 6 && value.getDigits() != 8) {
            context.buildConstraintViolationWithTemplate("Digits value is not allowed")
                    .addConstraintViolation();
            return false;
        }
        if (StringUtils.isEmpty(value.getAlgorithm()))  {
            context.buildConstraintViolationWithTemplate("Algorithm can't be empty")
                    .addConstraintViolation();
            return false;
        }
        if (!Arrays.asList("SHA1", "SHA256", "SHA512").contains(value.getAlgorithm())) {
            context.buildConstraintViolationWithTemplate("Algorithm is invalid")
                    .addConstraintViolation();
            return false;
        }
        if (value.getPeriod() != null && value.getPeriod() < 0) {
            context.buildConstraintViolationWithTemplate("Period can't be negative")
                    .addConstraintViolation();
            return false;
        }
        if (value.isGenerate() && StringUtils.isEmpty(value.getAccountName())) {
            context.buildConstraintViolationWithTemplate("Account name can't be empty")
                    .addConstraintViolation();
            return false;
        }
        if (value.isGenerate() && StringUtils.isEmpty(value.getIssuer())) {
            context.buildConstraintViolationWithTemplate("Issuer can't be empty")
                    .addConstraintViolation();
            return false;
        }
        if (StringUtils.isEmpty(value.getKey()) && !value.isGenerate() && StringUtils.isEmpty(value.getUrl())) {
            context.buildConstraintViolationWithTemplate("Key is required")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
