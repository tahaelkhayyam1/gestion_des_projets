package com.gestionprojet.prj.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.gestionprojet.prj.entity.Mission;
import com.gestionprojet.prj.service.IMissionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/mission")
public class MissionController {

    @Autowired
    private IMissionService missionService;

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
        model.addAttribute("mission", new Mission());
        return "registerMissionPage";
    }

    @PostMapping("/save")
    public String saveMission(HttpServletRequest request,
                              @ModelAttribute Mission mission,
                              Model model) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        Mission savedMission = missionService.saveMission(mission);
        String message = "Mission avec l'ID : '" + savedMission.getIdMission() + "' a été enregistrée avec succès !";
        model.addAttribute("message", message);
        return "redirect:getAllMissions";

     }

    @GetMapping("/getAllMissions")
    public String getAllMissions(HttpServletRequest request,
                                 @RequestParam(value = "message", required = false) String message,
                                 Model model) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        List<Mission> missions = missionService.getAllMissions();
        model.addAttribute("list", missions);
        model.addAttribute("message", message);
        return "allMissionsPage";
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
            Mission mission = missionService.getMissionById(id);
            model.addAttribute("mission", mission);
            return "editMissionPage";
        } catch (RuntimeException e) {
            attributes.addAttribute("message", e.getMessage());
            return "redirect:getAllMissions";
        }
    }

    @PostMapping("/update")
    public String updateMission(HttpServletRequest request,
                                @ModelAttribute Mission mission,
                                RedirectAttributes attributes) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        missionService.updateMission(mission);
        attributes.addAttribute("message", "Mission avec l'ID : '" + mission.getIdMission() + "' a été mise à jour avec succès !");
        return "redirect:/mission/getAllMissions";
    }

    @GetMapping("/delete")
    public String deleteMission(HttpServletRequest request,
                                @RequestParam Integer id,
                                RedirectAttributes attributes) {
        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }
        try {
            missionService.deleteMissionById(id);
            attributes.addAttribute("message", "Mission avec l'ID : '" + id + "' a été supprimée avec succès !");
        } catch (RuntimeException e) {
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/mission/getAllMissions";
    }

}
