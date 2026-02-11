package com.example.springprojectworkinglistcreateflow.controller;




import com.example.springprojectworkinglistcreateflow.entity.Items;
import jakarta.validation.Valid;
import com.example.springprojectworkinglistcreateflow.model.Item;
import com.example.springprojectworkinglistcreateflow.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String list(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "items/list";
    }

    @GetMapping("/items/new")
    public String newForm(Model model) {
        model.addAttribute("item", new Items());
        return "items/form";
    }

    @PostMapping("/items")
    public String create(@Valid @ModelAttribute("item") Items item,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "items/form";
        }
        itemService.add(item);
        return "redirect:/items";
    }
}
