package com.gestionprojet.prj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionprojet.prj.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
    // Custom query methods can be defined here
}
