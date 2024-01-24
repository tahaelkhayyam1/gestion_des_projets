package com.gestionprojet.prj.service.impl;

import com.gestionprojet.prj.entity.Tache;
import com.gestionprojet.prj.repository.TacheRepository;
import com.gestionprojet.prj.service.ITacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TacheServiceImpl implements ITacheService {

    private final TacheRepository tacheRepository;

    @Autowired
    public TacheServiceImpl(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    @Override
    public Tache saveTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    @Override
    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    @Override
    public Tache getTacheById(Integer id) {
        return tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tache with ID: " + id + " not found."));
    }

    @Override
    public void deleteTacheById(Integer id) {
        tacheRepository.deleteById(id);
    }

    @Override
    public Tache updateTache(Tache tache) {
        if (tacheRepository.existsById(tache.getIdTache())) {
            return tacheRepository.save(tache);
        } else {
            throw new RuntimeException("Tache with ID: " + tache.getIdTache() + " not found for update.");
        }
    }
}
