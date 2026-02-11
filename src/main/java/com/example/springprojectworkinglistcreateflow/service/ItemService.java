package com.example.springprojectworkinglistcreateflow.service;

import com.example.springprojectworkinglistcreateflow.entity.Items;
import com.example.springprojectworkinglistcreateflow.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repo;


    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public List<Items> findAll() {
        return repo.findAll();
    }

    public Items add(Items item) {
        return repo.save(item);
    }

}
