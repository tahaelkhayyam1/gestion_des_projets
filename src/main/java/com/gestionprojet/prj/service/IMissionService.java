package com.gestionprojet.prj.service;

import java.util.List;
import com.gestionprojet.prj.entity.Mission;

public interface IMissionService {
    Mission saveMission(Mission mission);
    List<Mission> getAllMissions();
    Mission getMissionById(Integer id);
    void deleteMissionById(Integer id);
    Mission updateMission(Mission mission);
}
