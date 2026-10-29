package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.RegistrationRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryRegistrationRepository implements RegistrationRepository {
    @Override
    public Registration save(Registration registration) {
        DataHolder.registrations.remove(registration);
        DataHolder.registrations.add(registration);
        return registration;
    }

    @Override
    public List<Registration> findAll() {
        return DataHolder.registrations;
    }

    @Override
    public List<Registration> findByWorkshopCategory(String category) {
        List<String> workshopTitles = DataHolder.workshops.stream().filter(x -> x.getCategory().equals(category)).map(Workshop::getTitle).toList();
        return DataHolder.registrations.stream().filter(r -> workshopTitles.stream().anyMatch(title -> r.getWorkshopTitle().contains(title)))
                .toList();
    }
}
