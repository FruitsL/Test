package com.ktds.todo1.controller;


import com.ktds.todo1.dto.PageRequestDTO;
import com.ktds.todo1.dto.PageResponseDTO;
import com.ktds.todo1.dto.Todo1AllDTO;
import com.ktds.todo1.dto.Todo1CountDTO;
import com.ktds.todo1.dto.Todo1DTO;
import com.ktds.todo1.service.Todo1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/todo")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TodoController {

    private final Todo1Service service;

    @GetMapping("/list")
    public PageResponseDTO<Todo1AllDTO> getList(PageRequestDTO pageRequestDTO){

        log.info(pageRequestDTO);

        return service.getListAll(pageRequestDTO);
    }

    @PostMapping("/new")
    public Map<String, Long> register(@RequestBody Todo1DTO todo1DTO){
        log.info(todo1DTO);
        return Map.of("tno", service.register(todo1DTO));
    }

    @GetMapping("/{tno}")
    public Todo1DTO get(@PathVariable("tno") Long tno){

        return service.getOne(tno);
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> delete(@PathVariable("tno")Long tno){

        log.info("tno:" + tno);

        return Map.of("result", "success");
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(
            @PathVariable("tno")Long tno,
            @RequestBody Todo1DTO todo1DTO){
        todo1DTO.setTno(tno);

        log.info(todo1DTO);

        return Map.of("result", "success");
    }

}
