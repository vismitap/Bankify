package com.banking.accountService.exception;

public class InvalidOperationException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ErrorCodeEnum errorCode;
	 
	public InvalidOperationException(ErrorCodeEnum errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
	
	 public ErrorCodeEnum getErrorCode(){
	        return errorCode;
	    }
}
