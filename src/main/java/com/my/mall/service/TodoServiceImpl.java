package com.my.mall.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.mall.domain.dto.PageRequestDto;
import com.my.mall.domain.dto.PageResponseDto;
import com.my.mall.domain.dto.TodoDto;
import com.my.mall.domain.entity.Todo;
import com.my.mall.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional // DB관리는 JPA ==> insert작업이 100번 많이 이루어질 때?? ==> 계좌에서 -500만원, 친구계좌에서 +500만원 되어야 transaction이 이뤄진다.
@RequiredArgsConstructor // final 필드를 초기화시키는 내용의 생성자를 준비한다.
public class TodoServiceImpl implements TodoService {
	private final ModelMapper modelMapper;
	private final TodoRepository todoRepository;
	
	@Override
	public TodoDto getTodo(Long todoNo) {
		Optional<Todo> result = todoRepository.findById(todoNo);
		Todo todo = result.orElseThrow();
		TodoDto todoDto = modelMapper.map(todo, TodoDto.class);
		return todoDto;
	}
	
	@Override
	public void addTodo(TodoDto todoDto) {
		Todo todo = modelMapper.map(todoDto, Todo.class);
		todoRepository.save(todo);
	}
	
	@Override
	public void fixTodo(TodoDto todoDto) {
		Optional<Todo> result = todoRepository.findById(todoDto.getTodoNo());
		Todo todo = result.orElseThrow();
		
		todo.setTitle(todoDto.getTitle());
		todo.setDueDate(todoDto.getDueDate());
		todo.setDone(todoDto.isDone());
		
		todoRepository.save(todo);
	}
	
	@Override
	public void delTodo(Long todoNo) {
		todoRepository.deleteById(todoNo);
	}
	
	@Override
	public PageResponseDto<TodoDto> getTodos(PageRequestDto request) {
		Pageable pageable = PageRequest.of(
				request.getPage() - 1,
				request.getSize(),
				Sort.by("todoNo").descending());
		Page<Todo> page = todoRepository.findAll(pageable);
		List<TodoDto> todos = page.getContent().stream()
				.map(todo -> modelMapper.map(todo, TodoDto.class))
				.collect(Collectors.toList());
		
		long totTodoCnt = page.getTotalElements();
		PageResponseDto<TodoDto> response = PageResponseDto.<TodoDto>builder()
				.items(todos)
				.request(request)
				.totItemCnt(totTodoCnt)
				.build();
		
		return response;
	}
}
 // 작업 관리의 롤백 처리 기능을 갖고 있는 어노테이션 ==> @Transactional(서비스 상에서 트랜잭선에 민감한 경우)