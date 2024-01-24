package com.gestionprojet.prj.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.gestionprojet.prj.entity.Tache;
import com.gestionprojet.prj.service.ITacheService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tache")
public class TacheController {

    @Autowired
    private ITacheService tacheService;

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
        model.addAttribute("tache", new Tache());
        return "registerTachePage";
    }

    @PostMapping("/save")
    public String saveTache(HttpServletRequest request,
                            @ModelAttribute Tache tache,
                            Model model) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        Tache savedTache = tacheService.saveTache(tache);
        String message = "Tâche avec l'ID : '" + savedTache.getIdTache() + "' a été enregistrée avec succès !";
        model.addAttribute("message", message);
        return "registerTachePage";
    }

    @GetMapping("/getAllTaches")
    public String getAllTaches(HttpServletRequest request,
                               @RequestParam(value = "message", required = false) String message,
                               Model model) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        List<Tache> taches = tacheService.getAllTaches();
        model.addAttribute("list", taches);
        model.addAttribute("message", message);
         return "allTachesPage";

    }

    @GetMapping("/edit")
    public String getEditPage(HttpServletRequest request,
                              Model model,
                              RedirectAttributes attributes,
                              @RequestParam Integer id) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        try {
            Tache tache = tacheService.getTacheById(id);
            model.addAttribute("tache", tache);
            return "editTachePage";
        } catch (RuntimeException e) {
            attributes.addAttribute("message", e.getMessage());
            return "redirect:/tache/getAllTaches";
        }
    }

    @PostMapping("/update")
    public String updateTache(HttpServletRequest request,
                              @ModelAttribute Tache tache,
                              RedirectAttributes attributes) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        tacheService.updateTache(tache);
        attributes.addAttribute("message", "Tâche avec l'ID : '" + tache.getIdTache() + "' a été mise à jour avec succès !");
        return "redirect:/tache/getAllTaches";
    }

    @GetMapping("/delete")
    public String deleteTache(HttpServletRequest request,
                              @RequestParam Integer id,
                              RedirectAttributes attributes) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        try {
            tacheService.deleteTacheById(id);
            attributes.addAttribute("message", "Tâche avec l'ID : '" + id + "' a été supprimée avec succès !");
        } catch (RuntimeException e) {
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/tache/getAllTaches";
    }
}
