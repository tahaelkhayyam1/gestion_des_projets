package com.gestionprojet.prj.entity;

import jakarta.persistence.*;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Site")
    private Integer idSite;

    @Column(name = "Nom_Site")
    private String nomSite;

    private String location;

    @Column(name = "Adresse_Site")
    private String adresseSite;

    private String ville;

    // Getter for idSite
    public Integer getIdSite() {
        return idSite;
    }

    // Setter for idSite
    public void setIdSite(Integer idSite) {
        this.idSite = idSite;
    }

    // Getter for nomSite
    public String getNomSite() {
        return nomSite;
    }

    // Setter for nomSite
    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Setter for location
    public void setLocation(String location) {
        this.location = location;
    }

    // Getter for adresseSite
    public String getAdresseSite() {
        return adresseSite;
    }

    // Setter for adresseSite
    public void setAdresseSite(String adresseSite) {
        this.adresseSite = adresseSite;
    }

    // Getter for ville
    public String getVille() {
        return ville;
    }

    // Setter for ville
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    
}

