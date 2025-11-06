package mk.ukim.finki.wp.lab.Repository.impl;

import mk.ukim.finki.wp.lab.Model.Dish;
import mk.ukim.finki.wp.lab.Repository.DishRepository;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryDishRepository implements DishRepository {
    public List<Dish> findAll(){
            return DataHolder.dishes;
    }
    public Dish findByDishId(String dishId){
        return DataHolder
                .dishes
                .stream().
                filter(dish -> dish.getDishId().equals(dishId))
                .findFirst()
                .orElseThrow();
    }


}
