package com.gestionprojet.prj.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.gestionprojet.prj.entity.Site;
import com.gestionprojet.prj.service.ISiteService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/site")
public class SiteController {

    @Autowired
    private ISiteService siteService;

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
        model.addAttribute("site", new Site());
        return "registerSitePage";
    }

    @PostMapping("/save")
    public String saveSite(HttpServletRequest request,
                           @ModelAttribute Site site,
                           Model model) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        Site savedSite = siteService.saveSite(site);
        String message = "Site avec l'ID : '" + savedSite.getIdSite() + "' a été enregistré avec succès !";
        model.addAttribute("message", message);
        return "redirect:/site/getAllSites";
    }

    @GetMapping("/getAllSites")
    public String getAllSites(HttpServletRequest request,
                              @RequestParam(value = "message", required = false) String message,
                              Model model) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        List<Site> sites = siteService.getAllSites();
        model.addAttribute("list", sites);
        model.addAttribute("message", message);
        return "allSitesPage";
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
            Site site = siteService.getSiteById(id);
            model.addAttribute("site", site);
            return "editSitePage";
        } catch (RuntimeException e) {
            attributes.addAttribute("message", e.getMessage());
            return "redirect:/site/getAllSites";
        }
    }

    @PostMapping("/update")
    public String updateSite(HttpServletRequest request,
                             @ModelAttribute Site site,
                             RedirectAttributes attributes) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        siteService.updateSite(site);
        attributes.addAttribute("message", "Site avec l'ID : '" + site.getIdSite() + "' a été mis à jour avec succès !");
        return "redirect:/site/getAllSites";
    }

    @GetMapping("/delete")
    public String deleteSite(HttpServletRequest request,
                             @RequestParam Integer id,
                             RedirectAttributes attributes) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        try {
            siteService.deleteSiteById(id);
            attributes.addAttribute("message", "Site avec l'ID : '" + id + "' a été supprimé avec succès !");
        } catch (RuntimeException e) {
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/site/getAllSites";
    }
}
