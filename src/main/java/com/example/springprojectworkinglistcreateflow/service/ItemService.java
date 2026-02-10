package com.example.springprojectworkinglistcreateflow.service;

import com.example.springprojectworkinglistcreateflow.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {

    private final List<Item> items = new ArrayList<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public List<Item> findAll() {
        return Collections.unmodifiableList(items);
    }

    public void add(Item item) {
        item.setId(idGen.getAndIncrement());
        items.add(item);
    }
}
