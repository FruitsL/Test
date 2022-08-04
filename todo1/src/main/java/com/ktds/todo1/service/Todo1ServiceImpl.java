package com.ktds.todo1.service;

import com.ktds.todo1.dto.PageRequestDTO;
import com.ktds.todo1.dto.PageResponseDTO;
import com.ktds.todo1.dto.Todo1AllDTO;
import com.ktds.todo1.dto.Todo1CountDTO;
import com.ktds.todo1.dto.Todo1DTO;
import com.ktds.todo1.entity.Todo1;
import com.ktds.todo1.repository.Todo1Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class Todo1ServiceImpl implements Todo1Service{

    private final Todo1Repository repository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(Todo1DTO dto) throws RuntimeException {

        Todo1 todo1 = modelMapper.map(dto, Todo1.class);
        repository.save(todo1);

        return todo1.getTno();
    }

    @Override
    public Todo1DTO getOne(Long tno) throws RuntimeException {

        Optional<Todo1> result = repository.findById(tno);

        Todo1 todo1 = result.orElseThrow();

        return modelMapper.map(todo1, Todo1DTO.class);
    }

    @Override
    public PageResponseDTO<Todo1CountDTO> getList(PageRequestDTO pageRequestDTO) throws RuntimeException {

        Page<Todo1CountDTO> result= repository.listWithCount(pageRequestDTO);
        return PageResponseDTO.<Todo1CountDTO>withAll()
        .dtoList(result.getContent())
        .total((int)result.getTotalElements())
        .pageRequestDTO(pageRequestDTO)
        .build();
    }

    @Override
    public PageResponseDTO<Todo1AllDTO> getListAll(PageRequestDTO pageRequestDTO) throws RuntimeException {
        Page<Todo1AllDTO> result= repository.listWithAll(pageRequestDTO);
        return PageResponseDTO.<Todo1AllDTO>withAll()
        .dtoList(result.getContent())
        .total((int)result.getTotalElements())
        .pageRequestDTO(pageRequestDTO)
        .build();
    }
}
