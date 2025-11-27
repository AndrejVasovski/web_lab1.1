package mk.ukim.finki.wp.lab.web.controler;

import mk.ukim.finki.wp.lab.Model.Chef;
import mk.ukim.finki.wp.lab.Model.Dish;
import mk.ukim.finki.wp.lab.Repository.DishRepository;
import mk.ukim.finki.wp.lab.Service.ChefService;
import mk.ukim.finki.wp.lab.Service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }
    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("dishes", this.dishService.listDishes());
        return "listDishes";
    }

    @GetMapping({"/add"})
    public String saveDish(@RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime){
        this.dishService.create(dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }
    @GetMapping({" /edit/{id}"})
    public String editDish(@PathVariable Long id, @RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime){
        this.dishService.update(id, dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @GetMapping({"/delete/{id}"})
    public String deleteDish(@PathVariable Long id) {
        this.dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping({"/choose"})
    public String showDishChoosePage(@RequestParam Long chefId, Model model) {
        Chef chef = this.chefService.findById(chefId);
        model.addAttribute("chef", chef);
        model.addAttribute("dishes", this.dishService.listDishes());
        return "selectDishForChef";
    }

    @PostMapping({"/add-to-chef"})
    public String addDishToChef(@RequestParam Long chefId, @RequestParam String dishId, Model model) {
        Chef chef = this.chefService.addDishToChef(chefId, dishId);
        model.addAttribute("chef", chef);
        return "chefDetails";
    }

    @GetMapping({"/add-to-chef"})
    public String showAddDishToChefPage(@RequestParam Long chefId, Model model) {
        Chef chef = this.chefService.findById(chefId);
        if (chef == null) {
            return "redirect:/chefs?error=ChefNotFound";
        } else {
            model.addAttribute("chef", chef);
            model.addAttribute("dishes", this.dishService.listDishes());
            return "selectDishForChef";
        }
    }

    @GetMapping({"/dish-form/{id}"})
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = this.dishService.findById(id);
        if (dish == null) {
            return "redirect:/dishes?error=DishNotFound";
        } else {
            model.addAttribute("dish", dish);
            model.addAttribute("action", "/dishes/edit/" + id);
            return "dish-form";
        }
    }
    @GetMapping({"/dish-form"})
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("action", "/dishes/add");
        return "dish-form";
    }



}
