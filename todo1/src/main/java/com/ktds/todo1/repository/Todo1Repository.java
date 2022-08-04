package com.ktds.todo1.repository;

import com.ktds.todo1.entity.Todo1;
import com.ktds.todo1.repository.search.Todo1Search;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Todo1Repository extends JpaRepository<Todo1, Long>, Todo1Search {
}
