package com.gestionprojet.prj.entity;
 
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tache")
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Tache")
    private Integer idTache;

    @Column(name = "Nom_Tache")
    private String nomTache;

    
    public Integer getIdTache() {
        return idTache;
    }

    // Setter for idTache
    public void setIdTache(Integer idTache) {
        this.idTache = idTache;
    }

    // Getter for nomTache
    public String getNomTache() {
        return nomTache;
    }

    // Setter for nomTache
    public void setNomTache(String nomTache) {
        this.nomTache = nomTache;
    }
 
    }
