package com.ktds.todo1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Todo1DTO {

    private Long tno;
    private String title;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dueDate;
    private boolean complete;

}
