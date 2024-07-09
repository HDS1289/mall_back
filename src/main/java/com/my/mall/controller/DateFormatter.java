package com.my.mall.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<LocalDate> {
	@Override
	public LocalDate parse(String str, Locale locale) {
		return LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	@Override
	public String print(LocalDate date, Locale locale) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date);
	}
}
