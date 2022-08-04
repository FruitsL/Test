package com.ktds.todo1.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.ktds.todo1.dto.PageRequestDTO;
import com.ktds.todo1.dto.Todo1AllDTO;
import com.ktds.todo1.dto.Todo1CountDTO;
import com.ktds.todo1.entity.QFavorite;
import com.ktds.todo1.entity.QTodo1;
import com.ktds.todo1.entity.QTodo1File;
import com.ktds.todo1.entity.Todo1;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

public class Todo1SearchImpl extends QuerydslRepositorySupport implements Todo1Search{

    public Todo1SearchImpl(){
        super(Todo1.class);
    }

    @Override
    public Page<Todo1CountDTO> listWithCount(PageRequestDTO pageRequestDTO) {

        QTodo1 todo1 = QTodo1.todo1;
        QFavorite favorite = QFavorite.favorite;

        JPQLQuery<Todo1> query = from(todo1);
        query.leftJoin(favorite).on(favorite.todo1.eq(todo1));
        query.groupBy(todo1);

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() -1,
        pageRequestDTO.getSize(), Sort.by("tno").descending());

        getQuerydsl().applyPagination(pageable, query);

        // JPQLQuery<Tuple> tupleJPQLQuery = query.select(todo1, favorite.countDistinct());

        JPQLQuery<Todo1CountDTO> dtoQuery = query.select(
            Projections.bean(Todo1CountDTO.class,
                todo1.tno,
                todo1.title,
                todo1.dueDate,
                todo1.complete,
                favorite.countDistinct().as("likeCount")
            )
        );

        List<Todo1CountDTO> list = dtoQuery.fetch();

        long totalCount = dtoQuery.fetchCount();

        return new PageImpl<>(list, pageable, totalCount);
    }

    @Override
    public Page<Todo1AllDTO> listWithAll(PageRequestDTO pageRequestDTO) {
        QTodo1 todo1 = QTodo1.todo1;
        QFavorite favorite = QFavorite.favorite;
        QTodo1File file = QTodo1File.todo1File;

        JPQLQuery<Todo1> query = from(todo1);
        query.leftJoin(favorite).on(favorite.todo1.eq(todo1));
        query.leftJoin(file).on(file.todo1.eq(todo1));

        query.where(file.isNull().or(file.ord.eq(0)));
        query.groupBy(todo1);

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(), Sort.by("tno").descending());
        getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<Todo1AllDTO> dtoQuery = 
            query.select(Projections.bean(
                Todo1AllDTO.class, 
                todo1.tno,
                todo1.title,
                todo1.dueDate,
                todo1.complete,
                favorite.countDistinct().as("likeCount"),
                file.fileName,
                file.img,
                file.uuid
            ));

            List<Todo1AllDTO> list = dtoQuery.fetch();
            long totalCount = dtoQuery.fetchCount();
            return new PageImpl<>(list, pageable, totalCount);
    }
}
