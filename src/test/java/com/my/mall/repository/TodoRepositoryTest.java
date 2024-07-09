package com.my.mall.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.my.mall.domain.entity.Todo;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class TodoRepositoryTest {
	@Autowired private TodoRepository todoRepository;
	
	@Test
	public void insertTodo() {
		for(int i = 1; i <= 100; i++) {
			Todo todo = Todo.builder()
					.title("title " + i)
					.dueDate(LocalDate.now().plusDays(i))
					.writer("writer " + i)
					.build();
			todoRepository.save(todo);
		}
	}
	
	@Test
	public void selctTodo() {
		Long todoNo = 1L;
		Optional<Todo> result = todoRepository.findById(todoNo);
		Todo todo = result.orElseThrow();
		log.info(todo);
	}
	
	@Test
	public void updateTodo() {
		Long todoNo = 1L;
		Optional<Todo> result = todoRepository.findById(todoNo);
		Todo todo = result.orElseThrow();
		todo.setTitle("제목 1");
		todo.setDone(true);
		todo.setDueDate(LocalDate.now());
		
		todoRepository.save(todo);
	}
	
	@Test
	public void deleteTodo() {
		Long todoNo = 1L;
		todoRepository.deleteById(todoNo);
	}
	
	@Test
	public void selectTodos() {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("todoNo").descending());
		Page<Todo> page = todoRepository.findAll(pageable);
		log.info("Total: " + page.getTotalElements());
		page.getContent().stream().forEach(todo -> log.info(todo));
	}
}
