package mk.ukim.finki.wp.lab.Repository;

import mk.ukim.finki.wp.lab.Model.Chef;
import mk.ukim.finki.wp.lab.Model.Dish;

import java.util.List;
import java.util.Optional;

public interface  DishRepository {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
}
