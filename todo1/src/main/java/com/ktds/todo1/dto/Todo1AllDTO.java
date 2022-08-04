package com.ktds.todo1.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Todo1AllDTO {
    private Long tno;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dueDate;
    private boolean complete;
    private long likeCount;

    private String uuid;
    private String fileName;
    private boolean img;
}
