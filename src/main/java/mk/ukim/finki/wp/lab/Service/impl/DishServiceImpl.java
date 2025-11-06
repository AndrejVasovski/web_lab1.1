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
}
