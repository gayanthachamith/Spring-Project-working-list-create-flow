package com.example.springprojectworkinglistcreateflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Items {

    @Id
    private Long id;

    private String name;

    private Integer quantity;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
