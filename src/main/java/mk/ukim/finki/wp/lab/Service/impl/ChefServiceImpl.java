package mk.ukim.finki.wp.lab.Service.impl;

import mk.ukim.finki.wp.lab.Model.Chef;
import mk.ukim.finki.wp.lab.Model.Dish;
import mk.ukim.finki.wp.lab.Repository.ChefRepository;
import mk.ukim.finki.wp.lab.Repository.DishRepository;
import mk.ukim.finki.wp.lab.Service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return this.chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = chefRepository.findById(chefId)
                .orElseThrow(() -> new RuntimeException("Chef not found"));

        Dish dish = dishRepository.findByDishId(dishId);
        if (dish == null){
            throw new RuntimeException("Dish not found");
        }

        chef.getDishes().add(dish);

        return chefRepository.save(chef);
    }
    }




