package com.example.springprojectworkinglistcreateflow.service;

import com.example.springprojectworkinglistcreateflow.entity.Items;
import com.example.springprojectworkinglistcreateflow.dto.ItemForm;
import com.example.springprojectworkinglistcreateflow.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repo;

    // find page for pagination
    public Page<Items> findPage(int page, int size) {
        return repo.findAll(PageRequest.of(page, size, Sort.by("id").ascending()));
    }

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public List<Items> findAll() {
        return repo.findAll();
    }

    public Items add(ItemForm item) {
        Items entity = new Items();
        entity.setName(item.getName());
        entity.setQuantity(item.getQuantity());
        return repo.save(entity);
    }

    public Items findByIdOrThrow(Long id){
        return repo.findById(id).orElseThrow(() ->  new IllegalArgumentException("item not found" + id));

    }

    public Items update(Long id, ItemForm form){
        Items existing = findByIdOrThrow(id);
        existing.setName(form.getName());
        existing.setQuantity(form.getQuantity());
        return repo.save(existing);
    }

    public ItemForm getFormForEdit(Long id) {
        Items item = findByIdOrThrow(id);
        ItemForm form = new ItemForm();
        form.setId(item.getId());      
        form.setName(item.getName());
        form.setQuantity(item.getQuantity());
        return form;
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Item not found " + id);
        }
        repo.deleteById(id);

    }

}
