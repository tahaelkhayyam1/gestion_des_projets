package com.gestionprojet.prj.service.impl;

import com.gestionprojet.prj.entity.Mission;
import com.gestionprojet.prj.repository.MissionRepository;
import com.gestionprojet.prj.service.IMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionServiceImpl implements IMissionService {

    private final MissionRepository missionRepository;

    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public Mission saveMission(Mission mission) {
        return missionRepository.save(mission);
    }

    @Override
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    @Override
    public Mission getMissionById(Integer id) {
        return missionRepository.findById(id).orElseThrow(() -> 
                new RuntimeException("Mission with ID: " + id + " not found."));
    }

    @Override
    public void deleteMissionById(Integer id) {
        missionRepository.deleteById(id);
    }

    @Override
    public Mission updateMission(Mission mission) {
        if (missionRepository.existsById(mission.getIdMission())) {
            return missionRepository.save(mission);
        } else {
            throw new RuntimeException("Mission with ID: " + mission.getIdMission() + " not found.");
        }
    }
}
