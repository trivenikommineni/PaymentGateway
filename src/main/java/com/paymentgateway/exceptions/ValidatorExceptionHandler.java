package com.paymentgateway.exceptions;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.paymentgateway.dto.StandardError;

import jakarta.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidatorExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> notValid(MethodArgumentNotValidException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError err = new StandardError(Instant.now(), status.value(),
				e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
						.collect(Collectors.toSet()).toString().replaceAll("\\[*]*", ""),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MerchantValidationException.class)
	public ResponseEntity<StandardError> BusinessException(MerchantValidationException ex, HttpServletRequest request) {

		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(Instant.now(), status.value(),
				ex.getMessage().replaceAll("\\[*]*", ""),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> GlobalException(Exception ex, HttpServletRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError(Instant.now(), status.value(),
				ex.getMessage().replaceAll("\\[*]*", ""),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
