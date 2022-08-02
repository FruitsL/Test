package com.ktds.j1.entity;

import java.time.LocalDate;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_todo_zerock")
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    private String content;

    private LocalDate dueDate;

    private boolean complete;

}
