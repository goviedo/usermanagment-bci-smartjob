package cl.goviedo.usermanagment.controllersadvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingControllerAdvice {
    
	@ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            error.getErrors().add(new Violation(violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getErrors().add(new Violation(fieldError.getDefaultMessage()));
        }
        return error;
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ValidationErrorResponse genericException(DataIntegrityViolationException e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.getErrors().add(new Violation("Email/Usuario ya existe. El e-mail es el usuario y único para cada usuario"));
		return error;
	}
   
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ValidationErrorResponse genericException(Exception e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.getErrors().add(new Violation(e.getMessage()));
		return error;
	}

}
