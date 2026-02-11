package com.example.springprojectworkinglistcreateflow.repository;

import com.example.springprojectworkinglistcreateflow.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Items, Long> {
    Long findIdByName(String name);

}
