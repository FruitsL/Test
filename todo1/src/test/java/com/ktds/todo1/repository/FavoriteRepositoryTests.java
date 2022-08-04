package com.ktds.todo1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ktds.todo1.entity.Favorite;
import com.ktds.todo1.entity.Todo1;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class FavoriteRepositoryTests {
    
    @Autowired
    FavoriteRepository repository;

    @Test
    public void testInsertDummies(){

        Long tno = 13299L;

        Todo1 todo1 = Todo1.builder().tno(tno).build();

        for(int i = 0; i < 14; i ++){
            Favorite favorite = Favorite.builder().who("u3").todo1(todo1).build();
            
            repository.save(favorite);
        }
        log.info("");

    }
}
