package mk.ukim.finki.wp.lab.Repository.impl;

import mk.ukim.finki.wp.lab.Model.Dish;
import mk.ukim.finki.wp.lab.Repository.DishRepository;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Dish> findById(Long id) {
        return DataHolder.dishes.stream().filter(dish -> dish.getId().equals(id)).findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        if(dish.getId() == null){
            this.deleteById(dish.getId());
        }else{
            long nextId = DataHolder.dishes.stream().mapToLong(Dish::getId).max().orElse(0L) + 1L;
            dish.setId(nextId);
        }
        DataHolder.dishes.add(dish);
        return dish;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.dishes.removeIf(dish -> dish.getId().equals(id));

    }


}
