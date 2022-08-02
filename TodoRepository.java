package com.ktds.j1.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ktds.j1.dto.TodoSearchDTO;
import com.ktds.j1.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity,Long> {
    

    @Query("select t from TodoEntity t where t.content like %:keyword% ")
    Page<TodoEntity> getListByKeyword(@Param("keyword") String keyword, Pageable pageable);


    @Query("select t from TodoEntity t where t.dueDate between :from and :to  ")
    Page<TodoEntity> getListByDueDate(
                    @Param("from") LocalDate from, 
                    @Param("to") LocalDate to, 
                    Pageable pageable);

    @Query("select t from TodoEntity t where (t.dueDate between :from and :to) and t.content like %:keyword%  ")
    Page<TodoEntity> getListByAll(
        @Param("keyword") String keyword,
                    @Param("from") LocalDate from, 
                    @Param("to") LocalDate to, 
                    Pageable pageable);
    
    @Query("select t from TodoEntity t where (t.dueDate between :#{#dto.from} and :#{#dto.to}) and t.content like %:#{#dto.keyword}%  ")
    Page<TodoEntity> getListByAll2(
        @Param("dto") TodoSearchDTO dto,Pageable pageable);

}
