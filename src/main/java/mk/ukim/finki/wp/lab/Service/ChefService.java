package mk.ukim.finki.wp.lab.Service;

import mk.ukim.finki.wp.lab.Model.Chef;
import mk.ukim.finki.wp.lab.Repository.ChefRepository;
import mk.ukim.finki.wp.lab.Repository.DishRepository;

import java.util.List;

public interface ChefService {


    List<Chef> listChefs();

    Chef findById(Long id);

    Chef addDishToChef(Long chefId, String dishId);
}
