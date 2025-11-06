package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.extern.apachecommons.CommonsLog;
import mk.ukim.finki.wp.lab.Model.Chef;
import mk.ukim.finki.wp.lab.Model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Chef> chefs = new ArrayList<>();

    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {
        // ----- Dishes -----
        dishes.add(new Dish("1", "Tavche Gravche", "Macedonian", 45));
        dishes.add(new Dish("2", "Sarma", "Macedonian", 90));
        dishes.add(new Dish("3", "Chocolate Mousse Cake", "French", 60));
        dishes.add(new Dish("4", "Macaroni", "French", 30));
        dishes.add(new Dish("5", "Pasta Carbonara", "Italian", 25));

        // ----- Chefs -----
        chefs.add(new Chef(1L, "Kire", "Petrov", "Specialist in traditional Macedonian cuisine with over 15 years of experience in preparing homemade dishes."));
        chefs.add(new Chef(2L, "Elena", "Markova", "Professional pastry chef with a passion for modern desserts and French patisserie."));
        chefs.add(new Chef(3L, "Antonio", "Rossi", "Italian chef who combines tradition with modern Italian gastronomy."));
        chefs.add(new Chef(4L, "Amina", "Hassan", "Middle Eastern chef known for her aromatic spices and creative flavor combinations."));
        chefs.add(new Chef(5L, "Nikola", "Georgiev", "Contemporary chef who blends European techniques with local ingredients to create unique dishes."));
    }

}
