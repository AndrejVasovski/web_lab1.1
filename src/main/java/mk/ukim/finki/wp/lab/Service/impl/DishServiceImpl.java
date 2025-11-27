package mk.ukim.finki.wp.lab.Service.impl;

import mk.ukim.finki.wp.lab.Model.Dish;
import mk.ukim.finki.wp.lab.Repository.DishRepository;
import mk.ukim.finki.wp.lab.Service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return  dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElseThrow();

    }
    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        if (dishId != null && !dishId.isEmpty() && name != null && !name.isEmpty() && cuisine != null && !cuisine.isEmpty()) {
            Dish dish = new Dish(dishId, name, cuisine, preparationTime);
            return this.dishRepository.save(dish);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        if(dishId !=null && !dishId.isEmpty() && name != null && !name.isEmpty() && cuisine != null && !cuisine.isEmpty()) {
            Dish dish = this.findByDishId(dishId);
            dish.setName(name);
            dish.setDishId(dishId);
            dish.setCuisine(cuisine);
            dish.setPreparationTime(preparationTime);
            return this.dishRepository.save(dish);
        }else  {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void delete(Long id) {
        this.dishRepository.deleteById(id);

    }
}
