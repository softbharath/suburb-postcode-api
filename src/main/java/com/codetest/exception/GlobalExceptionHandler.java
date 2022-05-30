package com.codetest.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codetest.model.CustomErrorResponse;

/**
 * Global exception handler for subrub-postcode-api service
 * 
 * @author bharathkumar
 *
 */

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Value(value = "${suburbPostCode.exception.internalError}")
    private String internalError;
    
    @ExceptionHandler(value = SuburbPostCodeAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> handleSuburbPostCodeAlreadyExistsException(SuburbPostCodeAlreadyExistsException suburbPostCodeAlreadyExistsException) {
    	List<String> errors = new ArrayList<String>();
    	errors.add(suburbPostCodeAlreadyExistsException.getMessage());
        CustomErrorResponse errorRes = new CustomErrorResponse();
        errorRes.setTimestamp(LocalDateTime.now());
        errorRes.setErrors(errors);
        errorRes.setStatus(HttpStatus.BAD_REQUEST.value());
    	return ResponseEntity.badRequest().body(errorRes);
    }
	
    @ExceptionHandler(value = SuburbPostCodeNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleSuburbPostCodeNotFoundException(SuburbPostCodeNotFoundException suburbPostCodeNotFoundException) {
    	List<String> errors = new ArrayList<String>();
    	errors.add(suburbPostCodeNotFoundException.getMessage());
        CustomErrorResponse errorRes = new CustomErrorResponse();
        errorRes.setTimestamp(LocalDateTime.now());
        errorRes.setErrors(errors);
        errorRes.setStatus(HttpStatus.NOT_FOUND.value());
    	return new ResponseEntity<>(errorRes, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
    	List<String> errors = new ArrayList<String>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
                + violation.getMessage());
          }
        CustomErrorResponse errorRes= new CustomErrorResponse();
        errorRes.setTimestamp(LocalDateTime.now());
        errorRes.setErrors(errors);
        errorRes.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
    	return new ResponseEntity<>(errorRes, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
   @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception exception) {
	   List<String> errors = new ArrayList<String>();
	   errors.add(internalError);

       CustomErrorResponse errorRes = new CustomErrorResponse();
       errorRes.setTimestamp(LocalDateTime.now());
       errorRes.setErrors(errors);
       errorRes.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.internalServerError().body(errorRes);
    }

}
