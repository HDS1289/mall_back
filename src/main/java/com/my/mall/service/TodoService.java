package com.my.mall.service;

import com.my.mall.domain.dto.PageRequestDto;
import com.my.mall.domain.dto.PageResponseDto;
import com.my.mall.domain.dto.TodoDto;

public interface TodoService {
	TodoDto getTodo(Long todoNo);
	void addTodo(TodoDto todoDto);
	void fixTodo(TodoDto todoDto);
	void delTodo(Long todoNo);
	PageResponseDto<TodoDto> getTodos(PageRequestDto request);
}
