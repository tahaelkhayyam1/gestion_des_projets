package com.gestionprojet.prj.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.gestionprojet.prj.entity.Employe;
import com.gestionprojet.prj.service.IEmployeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/employe")
public class EmployeController {

    @Autowired
    private IEmployeService employeService;

    private boolean isLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("loggedInUser") != null;
    }

    @GetMapping("/")
    public String showHomePage(HttpServletRequest request) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        return "homePage";
    }

    @GetMapping("/register")
    public String showRegistration(HttpServletRequest request, Model model) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        model.addAttribute("employe", new Employe());
        return "registerEmployePage";
    }

    @PostMapping("/save")
    public String saveEmploye(HttpServletRequest request,
                              @ModelAttribute Employe employe,
                              Model model) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        Employe savedEmploye = employeService.saveEmploye(employe);
        String message = "Employé avec le CIN : '" + savedEmploye.getCin() + "' a été enregistré avec succès !";
        model.addAttribute("message", message);
        return "redirect:/employe/getAllEmployes";
    }

    @GetMapping("/getAllEmployes")
    public String getAllEmployes(HttpServletRequest request,
                                 @RequestParam(value = "message", required = false) String message,
                                 Model model) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        List<Employe> employes = employeService.getAllEmployes();
        model.addAttribute("list", employes);
        model.addAttribute("message", message);
        return "allEmployesPage";
    }

    @GetMapping("/edit")
    public String getEditPage(HttpServletRequest request,
                              Model model,
                              RedirectAttributes attributes,
                              @RequestParam String cin) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        try {
            Employe employe = employeService.getEmployeByCin(cin);
            model.addAttribute("employe", employe);
            return "editEmployePage";
        } catch (RuntimeException e) {
            attributes.addAttribute("message", e.getMessage());
            return "redirect:/employe/getAllEmployes";
        }
    }

    @PostMapping("/update")
    public String updateEmploye(HttpServletRequest request,
                                @ModelAttribute Employe employe,
                                RedirectAttributes attributes) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        employeService.updateEmploye(employe);
        attributes.addAttribute("message", "Employé avec le CIN : '" + employe.getCin() + "' a été mis à jour avec succès !");
        return "redirect:/employe/getAllEmployes";
    }

    @GetMapping("/delete")
    public String deleteEmploye(HttpServletRequest request,
                                @RequestParam String cin,
                                RedirectAttributes attributes) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        try {
            employeService.deleteEmployeByCin(cin);
            attributes.addAttribute("message", "Employé avec le CIN : '" + cin + "' a été supprimé avec succès !");
        } catch (RuntimeException e) {
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/employe/getAllEmployes";
    }
}
