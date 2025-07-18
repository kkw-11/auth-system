package com.security.auth.exception;

import com.security.auth.dto.common.ApiResponse;
import com.security.auth.dto.common.ResponseCode;
import com.security.auth.exception.exceptions.DuplicateEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateEmailException(DuplicateEmailException ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(ResponseCode.DUPLICATE_EMAIL.getHttpStatus())
                .body(ApiResponse.error(ResponseCode.DUPLICATE_EMAIL));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(ResponseCode.INVALID_INPUT));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(ResponseCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ApiResponse.error(ResponseCode.INTERNAL_SERVER_ERROR));
    }
}
