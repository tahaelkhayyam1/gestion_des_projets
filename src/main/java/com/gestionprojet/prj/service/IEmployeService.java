package com.gestionprojet.prj.service;

import java.util.List;
import com.gestionprojet.prj.entity.Employe;

public interface IEmployeService {
    Employe saveEmploye(Employe employe);
    List<Employe> getAllEmployes();
    Employe getEmployeByCin(String cin);
    void deleteEmployeByCin(String cin);
    Employe updateEmploye(Employe employe);
}
