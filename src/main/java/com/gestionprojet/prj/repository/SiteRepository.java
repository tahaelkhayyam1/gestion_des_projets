package com.gestionprojet.prj.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionprojet.prj.entity.Site;

public interface SiteRepository extends JpaRepository<Site, Integer> {
    // Custom query methods can be defined here
}
