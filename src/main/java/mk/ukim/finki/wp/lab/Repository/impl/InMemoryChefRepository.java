package mk.ukim.finki.wp.lab.Repository.impl;

import mk.ukim.finki.wp.lab.Model.Chef;
import mk.ukim.finki.wp.lab.Repository.ChefRepository;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryChefRepository implements ChefRepository {
    public List<Chef> findAll() {
        return DataHolder.chefs;
    }


    public Optional<Chef> findById(Long id) {
        return Optional.of(DataHolder
                .chefs
                .stream()
                .filter(chef -> chef.getId().equals(id))
                .findFirst()
                .orElseThrow());
    }

    @Override
    public Chef save(Chef chef) {
        return null;
    }
}
