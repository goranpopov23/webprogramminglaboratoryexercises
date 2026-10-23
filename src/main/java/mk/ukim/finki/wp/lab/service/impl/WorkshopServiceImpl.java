package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryWorkshopRepository;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopServiceImpl implements WorkshopService {
    private final InMemoryWorkshopRepository _workshopRepository;

    public WorkshopServiceImpl(InMemoryWorkshopRepository repository){
        _workshopRepository = repository;
    }

    @Override
    public List<Workshop> listAll() {
        return this._workshopRepository.findAll();
    }

    @Override
    public List<Workshop> search(String text, Double maxPrice) {
        return this._workshopRepository.search(text,maxPrice);
    }
}
