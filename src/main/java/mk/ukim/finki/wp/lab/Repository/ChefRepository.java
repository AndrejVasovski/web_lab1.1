package mk.ukim.finki.wp.lab.Repository;

import com.sun.jdi.InterfaceType;
import mk.ukim.finki.wp.lab.Model.Chef;

import java.util.List;
import java.util.Optional;

public interface ChefRepository {
    List<Chef> findAll();
    Optional<Chef> findById(Long id);
    Chef save(Chef chef);
}