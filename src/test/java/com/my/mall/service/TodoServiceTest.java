package com.my.mall.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.mall.domain.dto.PageRequestDto;
import com.my.mall.domain.dto.TodoDto;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoServiceTest {
	@Autowired private TodoService todoService;
	
	@Test
	public void getTodo() {
		log.info(todoService.getTodo(2L));
	} // 1L로 지정하면 이미 삭제되었으므로 에러가 뜬다.
	
	@Test
	public void addTodo() {
		TodoDto todoDto = TodoDto.builder()
				.title("제목")
				.writer("작성자")
				.dueDate(LocalDate.now())
				.build();
		todoService.addTodo(todoDto);
	}
	
	@Test // 작성자는 바꾸는 로직이 없다.
	public void fixTodo() {
		TodoDto todoDto = TodoDto.builder()
				.todoNo(101L)
				.title("제목.수정")
				.dueDate(LocalDate.now().plusDays(1))
				.build();
		todoService.fixTodo(todoDto);
		log.info(todoService.getTodo(101L));
	}
	
	@Test
	public void delTodo() {
		todoService.delTodo(101L);
	}
	
	@Test
	public void getTodos() {
		PageRequestDto request = PageRequestDto.builder()
				.page(2)
				.size(10)
				.build();
		log.info(todoService.getTodos(request));
	}
}
