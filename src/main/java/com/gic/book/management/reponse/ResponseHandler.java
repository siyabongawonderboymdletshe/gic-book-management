package com.gic.book.management.reponse;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseHandler {

    public static ResponseEntity<Object> responseBuilder(
            String message, HttpStatus httpStatus, Object responseObject
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", httpStatus);
        response.put("data", responseObject);

        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> validationErrorBuilder(BindingResult result) 
    {
        List<String> errors = result.getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Validation failed");
        response.put("httpStatus", HttpStatus.BAD_REQUEST);
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> conflict(String message) 
    {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", HttpStatus.CONFLICT);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    public static ResponseEntity<Object> created(String message, Object data) 
    {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", HttpStatus.CREATED);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public static ResponseEntity<Object> notFound(String message) 
    {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<Object> success(String message, Object data) 
    {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", HttpStatus.OK);
        response.put("data", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
