package mk.ukim.finki.wp.lab.Service;

import mk.ukim.finki.wp.lab.Model.Dish;
import mk.ukim.finki.wp.lab.Repository.DishRepository;

import java.util.List;

public interface DishService {

    List<Dish> listDishes();
    Dish findByDishId(String dishId);
}
