package com.my.mall.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.mall.domain.dto.PageRequestDto;
import com.my.mall.domain.dto.PageResponseDto;
import com.my.mall.domain.dto.TodoDto;
import com.my.mall.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("todo")
public class TodoController {
   private final TodoService todoService;
   
   @GetMapping("{todoNo}")
   public TodoDto getTodo(@PathVariable(name="todoNo") Long todoNo) {
      return todoService.getTodo(todoNo);
   }
   
   @GetMapping("list")
   public PageResponseDto<TodoDto> getTodos(PageRequestDto request) {
      return todoService.getTodos(request);
   }
   
   @PostMapping("add")
   public Map<String, String> addTodo(@RequestBody TodoDto todoDto) {
      todoService.addTodo(todoDto);
      return Map.of("result", "TODO 추가.");
   }
   
   @PutMapping("fix")
   public Map<String, String> fixTodo(@RequestBody TodoDto todoDto) {
      todoService.fixTodo(todoDto);
      return Map.of("result", "TODO 수정.");
   }
   
   @DeleteMapping("del/{todoNo}")
   public Map<String, String> delTodo(@PathVariable(name="todoNo") Long todoNo) {
      todoService.delTodo(todoNo);
      return Map.of("result", "TODO 삭제.");
   }
}

