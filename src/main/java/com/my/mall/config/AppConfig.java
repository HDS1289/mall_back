package com.my.mall.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.my.mall.controller.DateFormatter;

@Configuration
public class AppConfig implements WebMvcConfigurer {
	@Bean
	public ModelMapper getMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setFieldMatchingEnabled(true)
			.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
			.setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new DateFormatter());
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")
				.maxAge(300)
				.allowedHeaders("Authorization", "Cache-Control", "Content-Type");
	}
}
// entity에 있는 필드와 DTO에 있는 필드에서 같은 것끼리 매핑하게 한다.