package com.banking.accountService.exception;

import java.time.LocalDateTime;

public record ErrorResponse( 
		LocalDateTime timestamp,
		int statusCode,
		String error,
		String message,
		String path
) {

}
