package com.gestionprojet.prj.service;

import com.gestionprojet.prj.entity.Login;
import com.gestionprojet.prj.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    
    @Autowired
    private LoginRepository loginRepository;

    public Login login(String username, String password) {
        return loginRepository.findByUsernameAndPassword(username, password);
    }
}