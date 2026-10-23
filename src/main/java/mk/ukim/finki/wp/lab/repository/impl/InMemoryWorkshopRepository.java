package mk.ukim.finki.wp.lab.repository.impl;

import lombok.Data;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryWorkshopRepository implements WorkshopRepository {
    @Override
    public List<Workshop> findAll() {
        return DataHolder.workshops;
    }

    @Override
    public Optional<Workshop> findByTitle(String title) {
        return DataHolder.workshops.stream().filter(x -> x.getTitle().equals(title)).findFirst();
    }

    @Override
    public List<Workshop> search(String text, Double maxPrice) {
        if(text == null || text.isEmpty()){
            return DataHolder.workshops.stream().filter(x -> x.getPrice() <= maxPrice).collect(Collectors.toList());
        }

        String textToLowerCase = text.toLowerCase();

        if(maxPrice == null || maxPrice == 0.0){
            return DataHolder.workshops.stream().filter(x -> x.getTitle().toLowerCase().contains(textToLowerCase)
                    || x.getCategory().toLowerCase().contains(textToLowerCase)).collect(Collectors.toList());
        }
        return DataHolder.workshops.stream().filter(x -> (x.getTitle().toLowerCase().contains(textToLowerCase)
                || x.getCategory().toLowerCase().contains(textToLowerCase)) && x.getPrice() <= maxPrice).collect(Collectors.toList());
    }
}
