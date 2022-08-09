package com.ktds.todo1.repository.search;

import org.springframework.data.domain.Page;

import com.ktds.todo1.dto.PageRequestDTO;
import com.ktds.todo1.dto.Todo1AllDTO;
import com.ktds.todo1.dto.Todo1CountDTO;

public interface Todo1Search {
    Page<Todo1CountDTO> listWithCount(PageRequestDTO pageRequestDTO);
    
    Page<Todo1AllDTO> listWithAll(PageRequestDTO pageRequestDTO);

}
