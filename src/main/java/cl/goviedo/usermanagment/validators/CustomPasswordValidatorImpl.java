package cl.goviedo.usermanagment.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementador del validador de la clave, por defecto en application.properties:
 * 
 * ^(?=.*[A-Z])(?=.*[$%&])[A-Za-z\d$%&]{8,}$
 * 
 * Explicación:
 * 
 * ^: Comienzo String.
 * (?=.*[A-Z]): Al menos una palabra en mayuscula en la frase.
 * (?=.*[$%&]): Al menos uno de los siguientes símbolos ($, %, or &).
 * [A-Za-z\d$%&]{8,}: Cualquier combinación de caracteres mayúsculas, minúsuclas, dígitos y los símbolos especificados, con un largo mínimo de 8 caracteres.
 * $: Fin string.
 */
@Slf4j
class CustomPasswordValidatorImpl implements ConstraintValidator<CustomPasswordValidator, String> {

    @Value("${password.regex.validator.string}")
    String regex;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.info("El regex que recibo es: "+regex);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
