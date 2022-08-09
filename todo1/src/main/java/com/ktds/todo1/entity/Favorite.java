package com.ktds.todo1.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "tbl_favorite", indexes = {
    @Index(name = "idx_favorite_todo", columnList = "todo1_tno")
})
public class Favorite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    
    @ManyToOne(fetch = FetchType.LAZY)
    private Todo1 todo1;

    private String who;
    
    @CreationTimestamp
    private LocalDate ftime;



}
