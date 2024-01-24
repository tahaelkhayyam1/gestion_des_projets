package com.gestionprojet.prj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employe")
public class Employe {

    @Id
    @Column(name = "cin")
    private String cin;


    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "no_tel")
    private String noTel;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "recrute")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recrute;
    
    @Column(name = "salaire")
    private double salaire;

    // Getters
    public String getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNoTel() {
        return noTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public Date getRecrute() {
        return recrute;
    }

    public double getSalaire() {
        return salaire;
    }

    // Setters
    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setRecrute(Date recrute) {
        this.recrute = recrute;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
}
