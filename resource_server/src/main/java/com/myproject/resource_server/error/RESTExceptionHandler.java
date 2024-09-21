package com.myproject.resource_server.error;

import com.myproject.resource_server.error.exception.InvalidArgumentException;
import com.myproject.resource_server.error.exception.ResourceFetchException;
import com.myproject.resource_server.error.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RESTExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RESTExceptionHandler.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<APIErrorResponse> handleError404(Exception e) {
        return buildResponseEntity(new APIErrorResponse(HttpStatus.NOT_FOUND, "Requested resource does not exist", HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<APIErrorResponse> handleIO(Exception e) {
        LOGGER.error("Exception Caused By: ", e);
        return buildResponseEntity(new APIErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred in IO streams.", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<APIErrorResponse> handleAccessDenied(Exception e) {
        return buildResponseEntity(new APIErrorResponse(HttpStatus.UNAUTHORIZED, "Access denied.", HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<APIErrorResponse> handleInvalidArgumentException(Exception e) {
        return buildResponseEntity(new APIErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(ResourceFetchException.class)
    public ResponseEntity<APIErrorResponse> handleResourceFetchException(Exception e) {
        LOGGER.error("Exception Caused By: ", e);
        return buildResponseEntity(new APIErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleResourceNotFoundException(Exception e) {
        return buildResponseEntity(new APIErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        StringBuilder stringBuilder = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> stringBuilder.append(String.format("%s : %s ", ((FieldError) error).getField(), error.getDefaultMessage())));
        return buildResponseEntity(new APIErrorResponse(HttpStatus.BAD_REQUEST, stringBuilder.toString(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIErrorResponse> handleError(Exception e) {
        LOGGER.error("Exception Caused By: ", e);
        return buildResponseEntity(new APIErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getClass().getName() + " " + e.getMessage(), 500));
    }

    private ResponseEntity<APIErrorResponse> buildResponseEntity(APIErrorResponse apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
