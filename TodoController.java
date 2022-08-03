package com.ktds.todo1.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktds.todo1.dto.PageRequestDTO;
import com.ktds.todo1.dto.Todo1DTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    
    @PostMapping("/new")
    public Map<String, Long> register(@RequestBody Todo1DTO todo1DTO){
        
        log.info(todo1DTO);
        return Map.of("tno", 123L);
    }
    
    @GetMapping("/{tno}")
    public Todo1DTO get(@PathVariable("tno") Long tno){

        Todo1DTO dto = new Todo1DTO();
        dto.setTno(tno);
        dto.setDueDate(LocalDate.now());
        dto.setComplete(true);
        return dto;

    }

    @DeleteMapping("/{tno}")
    public Map<String, String> delete(@PathVariable("tno") Long tno){
        log.info("tno:" + tno);

        return Map.of("result", "success");
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(
        @PathVariable("tno") Long tno, 
        @RequestBody Todo1DTO todo1DTO){

            todo1DTO.setTno(tno);
            
            log.info(todo1DTO);
            return Map.of("result", "success");
    }

    @GetMapping("/list")
    public List<Todo1DTO> getList(PageRequestDTO pageRequestDTO){
        
        log.info(pageRequestDTO);

        return null;
    }
}
