package com.banking.accountService.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            ErrorCodeEnum errorCode,
            Exception ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                errorCode.getStatus().value(),
                errorCode.getCode(),
                errorCode.getMessage(),
                request.getRequestURI()
        );

        log.error("Error occurred: {}", errorCode.getCode(), ex);

        return new ResponseEntity<>(error, errorCode.getStatus());
    }

	
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException ex, HttpServletRequest request) {
	
        return buildErrorResponse(ex.getErrorCode(), ex, request);
				
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFound(AccountNotFoundException ex, HttpServletRequest request) {
	
        return buildErrorResponse(ex.getErrorCode(), ex, request);
				
	}
	
	
	@ExceptionHandler(InvalidOperationException.class)
	public ResponseEntity<ErrorResponse> handleInvalidOperationException(InvalidOperationException ex, HttpServletRequest request) {
		
        return buildErrorResponse(ex.getErrorCode(), ex, request);
		
	}
	
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(InsufficientBalanceException ex, HttpServletRequest request){
		
        return buildErrorResponse(ex.getErrorCode(), ex, request);

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request){
		
        return buildErrorResponse(ErrorCodeEnum.INTERNAL_ERROR, ex, request);

	}
 

	
}
