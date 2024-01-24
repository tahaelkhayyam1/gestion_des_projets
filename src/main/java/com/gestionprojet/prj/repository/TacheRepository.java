package com.gestionprojet.prj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionprojet.prj.entity.Tache;

public interface TacheRepository extends JpaRepository<Tache, Integer> {
    // Custom query methods can be defined here if needed
}
