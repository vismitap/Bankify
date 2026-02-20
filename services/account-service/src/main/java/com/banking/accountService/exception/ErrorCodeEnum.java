package com.banking.accountService.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum {

	ACCOUNT_NOT_FOUND("ACC-001", "Account not found", HttpStatus.NOT_FOUND),

    INVALID_ACCOUNT_ID("ACC-002", "Invalid account id", HttpStatus.BAD_REQUEST),

    INSUFFICIENT_BALANCE("ACC-003", "Insufficient balance", HttpStatus.CONFLICT),

    ACCOUNT_ALREADY_CLOSED("ACC-004", "Account already closed", HttpStatus.CONFLICT),

    INTERNAL_ERROR("SYS-001", "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	
    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCodeEnum(String code, String message, HttpStatus status) {
    	this.code = code;
    	this.message = message;
    	this.status = status;
	}
	public String getCode(){ return code; }
    public String getMessage(){ return message; }
    public HttpStatus getStatus(){ return status; }

}
