package com.ktds.todo1.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageRequestDTO {

    private int page;
    private int size;

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    public void setPage(int page) {
        this.page = page <= 0 ? 1: page;
    }

    public void setSize(int size) {
        this.size = size < 10? 10: size > 100? 100:size;
    }
}
