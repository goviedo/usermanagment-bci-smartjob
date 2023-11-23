package cl.goviedo.usermanagment.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

/**
 * Regex para la validación del correo electrónico.
 * Basado en este articulo https://www.baeldung.com/java-email-validation-regex, seguimos RFC 5322
 * Asi podemos experimentar que algunos correos no son validos tambien
 * @implNote Es aconsejable utilizar una librería para estos efectos como Apache Commons Validator for Email
 * por ser mucho más completa y abarcar otros criterios de correo y evitar ataques con caracteres extraños.
 */
@Slf4j
class CustomEmailRegexValidatorImpl implements ConstraintValidator<CustomEmailRegexValidator, String> {

    @Value("${email.regex.validator.string}")
    String regex;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.info("EMAIL REGEX: "+regex);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}