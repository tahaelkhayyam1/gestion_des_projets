package com.gestionprojet.prj.service.impl;

import com.gestionprojet.prj.entity.Employe;
import com.gestionprojet.prj.repository.EmployeRepository;
import com.gestionprojet.prj.service.IEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;

    @Autowired
    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe getEmployeByCin(String cin) {
        return employeRepository.findById(cin).orElseThrow(() -> 
                new RuntimeException("Employe with CIN: " + cin + " not found."));
    }

    @Override
    public void deleteEmployeByCin(String cin) {
        employeRepository.deleteById(cin);
    }

    @Override
    public Employe updateEmploye(Employe employe) {
        if (employeRepository.existsById(employe.getCin())) {
            return employeRepository.save(employe);
        } else {
            throw new RuntimeException("Employe with CIN: " + employe.getCin() + " not found.");
        }
    }
}
