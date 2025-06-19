package com.gic.book.management.reponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

public class ResponseHandlerTest {
    @Test
    void responseBuilder_shouldBuildResponseCorrectly() {
        String message = "Success";
        HttpStatus status = HttpStatus.OK;
        Object data = List.of("One", "Two");

        ResponseEntity<Object> response = ResponseHandler.responseBuilder(message, status, data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Success", body.get("message"));
        assertEquals(status, body.get("httpStatus"));
        assertEquals(data, body.get("data"));
    }

    @Test
    void validationErrorBuilder_shouldReturnBadRequestWithErrorMessages() {
        BindingResult mockBindingResult = mock(BindingResult.class);

        List<FieldError> fieldErrors = List.of(
            new FieldError("object", "field1", "Field1 is required"),
            new FieldError("object", "field2", "Field2 must be valid")
        );

        when(mockBindingResult.getFieldErrors()).thenReturn(fieldErrors);

        ResponseEntity<Object> response = ResponseHandler.validationErrorBuilder(mockBindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Validation failed", body.get("message"));
        assertEquals(HttpStatus.BAD_REQUEST, body.get("httpStatus"));
        assertEquals(List.of("Field1 is required", "Field2 must be valid"), body.get("errors"));
    }

    @Test
    void conflict_shouldReturnConflictResponse() {
        String message = "Conflict occurred";

        ResponseEntity<Object> response = ResponseHandler.conflict(message);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Conflict occurred", body.get("message"));
        assertEquals(HttpStatus.CONFLICT, body.get("httpStatus"));
    }

    @Test
    void created_shouldReturnCreatedResponse() {
        String message = "Created successfully";
        Object data = Map.of("id", 1);

        ResponseEntity<Object> response = ResponseHandler.created(message, data);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Created successfully", body.get("message"));
        assertEquals(HttpStatus.CREATED, body.get("httpStatus"));
        assertEquals(data, body.get("data"));
    }

    @Test
    void notFound_shouldReturnNotFoundResponse() {
        String message = "Resource not found";

        ResponseEntity<Object> response = ResponseHandler.notFound(message);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Resource not found", body.get("message"));
        assertEquals(HttpStatus.NOT_FOUND, body.get("httpStatus"));
    }

    @Test
    void success_shouldReturnSuccessResponse() {
        String message = "Operation successful";
        Object data = List.of("a", "b");

        ResponseEntity<Object> response = ResponseHandler.success(message, data);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("Operation successful", body.get("message"));
        assertEquals(HttpStatus.OK, body.get("httpStatus"));
        assertEquals(data, body.get("data"));
    }
}
