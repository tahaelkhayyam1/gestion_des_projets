package com.gestionprojet.prj.service.impl;
 
import com.gestionprojet.prj.entity.Site;
import com.gestionprojet.prj.repository.SiteRepository;
import com.gestionprojet.prj.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements ISiteService {

    private final SiteRepository siteRepository;

    @Autowired
    public SiteServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public Site saveSite(Site site) {
        return siteRepository.save(site);
    }

    @Override
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    @Override
    public Site getSiteById(Integer id) {
        return siteRepository.findById(id).orElseThrow(() -> 
                new RuntimeException("Site with ID: " + id + " not found."));
    }

    @Override
    public void deleteSiteById(Integer id) {
        siteRepository.deleteById(id);
    }

    @Override
    public Site updateSite(Site site) {
        if (siteRepository.existsById(site.getIdSite())) {
            return siteRepository.save(site);
        } else {
            throw new RuntimeException("Site with ID: " + site.getIdSite() + " not found.");
        }
    }
}
