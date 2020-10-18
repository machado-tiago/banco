package br.com.zup.banco.config;

import java.time.DateTimeException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler{

    @Override
    // error handle for @Valid
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
                List<FieldError> errors = ex.getBindingResult().getFieldErrors();
                Map<String, String> bodymsg = new LinkedHashMap<>();
                for (FieldError fieldError : errors) {
                    bodymsg.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return new ResponseEntity<>(bodymsg, headers, HttpStatus.BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        Map<String,String> body = new LinkedHashMap<>();
        if (ex.contains(DateTimeException.class)) {
                body.put("invalid date format", ex.getMostSpecificCause().getLocalizedMessage()+ ". Expected date format: dd/MM/yyyy"); 
    
        } else {
            body.put("error",ex.getMostSpecificCause().getLocalizedMessage()); 
        }
        
        return new ResponseEntity<Object>(body, status);
    }

}