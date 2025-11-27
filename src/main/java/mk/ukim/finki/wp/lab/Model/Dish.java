package mk.ukim.finki.wp.lab.Model;

import lombok.Data;


@Data

public class Dish {
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    int preparationTime;

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;

    }

    public Dish() {

    }
}
