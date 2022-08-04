package com.ktds.todo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktds.todo1.entity.Todo1File;

public interface Todo1FileRepository extends JpaRepository<Todo1File, String> {
    
}
