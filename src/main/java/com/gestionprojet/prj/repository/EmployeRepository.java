package com.gestionprojet.prj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionprojet.prj.entity.Employe;

public interface EmployeRepository extends JpaRepository<Employe, String> {
    // Custom query methods can be defined here if needed
}
