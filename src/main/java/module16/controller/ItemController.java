package module16.controller;




import jakarta.validation.Valid;
import module16.model.Item;
import module16.service.ItemService;
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

    // GET /items -> list page
    @GetMapping("/items")
    public String list(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "items/list";
    }

    // GET /items/new -> create form
    @GetMapping("/items/new")
    public String newForm(Model model) {
        model.addAttribute("item", new Item());
        return "items/form";
    }

    // POST /items -> validate, save, redirect
    @PostMapping("/items")
    public String create(@Valid @ModelAttribute("item") Item item,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "items/form";
        }
        itemService.add(item);
        return "redirect:/items";
    }
}
