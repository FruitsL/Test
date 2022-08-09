package com.ktds.todo1.repository;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ktds.todo1.dto.PageRequestDTO;
import com.ktds.todo1.entity.Todo1;
import com.ktds.todo1.entity.Todo1File;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class Todo1RepositoryTests {
    @Autowired
    Todo1Repository repository;

    @Autowired
    Todo1FileRepository fileRepository;

    @Test
    public void testAll() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        repository.listWithAll(pageRequestDTO);
    }

    @Test
    public void insertFiles() {
        Long[] tnoArr = {13299L, 13297L, 13294L, 13292L, 13290L};
        for(Long tno : tnoArr) {
            Todo1 todo1 = Todo1.builder().tno(tno).build();
            for (int i = 0; i < 3; i++) {
                Todo1File file = Todo1File.builder()
                    .ord(i)
                    .todo1(todo1)
                    .fileName(tno+"'s attached file"+i)
                    .uuid(UUID.randomUUID().toString()).build();
                    fileRepository.save(file);
            } // inner end
        } // outer end
    }

    @Test
    public void testWithCount(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        repository.listWithCount(pageRequestDTO)
        .getContent().forEach(dto -> log.info(dto));

    }
}
