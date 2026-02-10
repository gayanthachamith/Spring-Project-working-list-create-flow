package com.example.springprojectworkinglistcreateflow.model;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Item {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public Item() {}

    public Item(Long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
