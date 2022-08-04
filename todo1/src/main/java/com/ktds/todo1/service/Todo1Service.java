package com.ktds.todo1.service;

import com.ktds.todo1.dto.PageRequestDTO;
import com.ktds.todo1.dto.PageResponseDTO;
import com.ktds.todo1.dto.Todo1AllDTO;
import com.ktds.todo1.dto.Todo1CountDTO;
import com.ktds.todo1.dto.Todo1DTO;

public interface Todo1Service {

    Long register(Todo1DTO dto)throws RuntimeException;

    Todo1DTO getOne(Long tno)throws RuntimeException;

    PageResponseDTO<Todo1CountDTO> getList(PageRequestDTO pageRequestDTO)throws RuntimeException;

    PageResponseDTO<Todo1AllDTO> getListAll(PageRequestDTO pageRequestDTO) throws RuntimeException;
}
