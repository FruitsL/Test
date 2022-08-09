package com.ktds.todo1.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(exclude = "todo1")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo1File {
    @Id
    private String uuid;
    private String fileName;
    private boolean img;
    private int ord;
    @ManyToOne(fetch = FetchType.LAZY)
    private Todo1 todo1;
}
