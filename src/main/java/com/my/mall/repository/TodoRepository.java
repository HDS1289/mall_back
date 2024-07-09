package com.my.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.mall.domain.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	
}
