package com.gestionprojet.prj.service;

import java.util.List;

import com.gestionprojet.prj.entity.Tache;

public interface ITacheService {
    Tache saveTache(Tache tache);
    List<Tache> getAllTaches();
    Tache getTacheById(Integer id);
    void deleteTacheById(Integer id);
    Tache updateTache(Tache tache);
}
