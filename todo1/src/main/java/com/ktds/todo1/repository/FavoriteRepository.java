package com.ktds.todo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktds.todo1.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
    

}
