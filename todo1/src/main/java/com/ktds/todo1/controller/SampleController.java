package com.ktds.todo1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

    @GetMapping("/doA")
    public Map<String,Long> doA() {
        return Map.of("tno", 12L);
    }
}
