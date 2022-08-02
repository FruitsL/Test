package com.ktds.j1.repository;



import java.time.LocalDate;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ktds.j1.dto.TodoSearchDTO;
import com.ktds.j1.entity.TodoEntity;

import lombok.extern.log4j.Log4j2;




@SpringBootTest
@Log4j2
public class TodoRepositoryTests {
    
    @Autowired
    TodoRepository repository;

    @Test 
    public void testByAll2() {

        TodoSearchDTO dto = TodoSearchDTO.builder().from(LocalDate.now()).to(LocalDate.now().plusDays(2)).keyword("1").build();

        Pageable pageable = dto.getPage();

        Page<TodoEntity> result = repository.getListByAll2(dto, pageable);

        result.getContent().forEach(todo -> log.info(todo));

    }

    @Test
    public void testByAll() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());

        String keyword = "1";
        LocalDate from  = LocalDate.now();
        LocalDate to  = from.plusDays(2);

        Page<TodoEntity> result = repository.getListByAll(keyword, from,to, pageable);

        result.getContent().forEach(todo -> log.info(todo));

    }


    @Test
    public void testByDueDate() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());

        LocalDate from  = LocalDate.now();
        LocalDate to  = from.plusDays(2);

        Page<TodoEntity> result = repository.getListByDueDate(from,to, pageable);

        result.getContent().forEach(todo -> log.info(todo));

    }

    @Test
    public void testByKeyword() {
        String keyword = "11";

        Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());

        Page<TodoEntity> result = repository.getListByKeyword(keyword, pageable);

        result.getContent().forEach(todo -> log.info(todo));
        

    }

    @Test
    public void testMakeDummies() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            TodoEntity todo = TodoEntity.builder()
            .content("Sample..." + i)
            .dueDate(LocalDate.now().plusDays( i % 10))
            .build();

            repository.save(todo);

        });


    }

}
