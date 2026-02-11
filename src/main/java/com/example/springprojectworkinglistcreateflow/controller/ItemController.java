package com.example.springprojectworkinglistcreateflow.controller;




import com.example.springprojectworkinglistcreateflow.entity.Items;
import com.example.springprojectworkinglistcreateflow.dto.ItemForm;
import jakarta.validation.Valid;
import com.example.springprojectworkinglistcreateflow.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;

@Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        int size = 10; // 10 elements for page for pagination

        Page<Items> itemsPage = itemService.findPage(page, size);

        model.addAttribute("items", itemsPage.getContent());
        model.addAttribute("itemsPage", itemsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", itemsPage.getTotalPages());
        model.addAttribute("totalItems", itemsPage.getTotalElements());

        return "items/list";
    }

    @GetMapping("/items/new")
    public String newForm(Model model) {
        model.addAttribute("item", new ItemForm());
        return "items/form";
    }

    @PostMapping("/items")
    public String create(@Valid @ModelAttribute("item") ItemForm item,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "items/form";
        }
        itemService.add(item);
        return "redirect:/items";
    }

    @GetMapping("/items/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        try {
            Items entity = itemService.findByIdOrThrow(id);

            ItemForm form = new ItemForm();
            form.setId(entity.getId());
            form.setName(entity.getName());
            form.setQuantity(entity.getQuantity());

            model.addAttribute("item", form);
            model.addAttribute("mode", "edit");

            return "items/form";
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
    }

    @PutMapping("/items/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("item") ItemForm item,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "items/form";
        }
        try {
            itemService.update(id, item);
            return "redirect:/items";
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
    }

    @DeleteMapping("/items/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            itemService.delete(id);
            return "redirect:/items";
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
    }
}
