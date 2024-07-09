package com.my.mall.controller;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice	
public class Advice {
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<?> none(NoSuchElementException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Map.of("msg", e.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> invalidArg(MethodArgumentNotValidException e) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(Map.of("msg", e.getMessage()));
	}
}