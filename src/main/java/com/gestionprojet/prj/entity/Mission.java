package com.gestionprojet.prj.entity;
 
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Mission")
    private Integer idMission;

    @Column(name = "Nom_Mission")
    private String nomMission;

    
    public Integer getIdMission() {
        return idMission;
    }

    // Setter for idMission
    public void setIdMission(Integer idMission) {
        this.idMission = idMission;
    }

    // Getter for nomMission
    public String getNomMission() {
        return nomMission;
    }

    // Setter for nomMission
    public void setNomMission(String nomMission) {
        this.nomMission = nomMission;
    }
 
    }
