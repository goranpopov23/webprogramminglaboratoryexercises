package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryRegistrationRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryWorkshopRepository;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final InMemoryRegistrationRepository _registrationRepository;
    private final InMemoryWorkshopRepository _workshopRepository;

    public RegistrationServiceImpl(InMemoryRegistrationRepository repository, InMemoryWorkshopRepository wrepository){
        _registrationRepository = repository;
        _workshopRepository = wrepository;
    }

    @Override
    public Registration register(String workshopTitle, String participantName, String participantEmail, int numberOfSeats) {
        if(workshopTitle == null || workshopTitle.isEmpty() || participantName == null || participantName.isEmpty() ||
        participantEmail == null || participantEmail.isEmpty() ||  numberOfSeats < 1){
            throw new IllegalArgumentException("Invalid inputs for reservation.");
        }

        Workshop workshop = this._workshopRepository
                .findByTitle(workshopTitle)
                .orElseThrow(() -> new RuntimeException("Workshop not found"));

        if(numberOfSeats > workshop.getCapacity()){
            throw new IllegalArgumentException("Not enough free seats.");
        }

        Registration registration = new Registration(workshopTitle,participantName,participantEmail,numberOfSeats);

        workshop.setCapacity(workshop.getCapacity() - numberOfSeats);

        return this._registrationRepository.save(registration);
    }
}
