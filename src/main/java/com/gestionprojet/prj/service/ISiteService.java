package com.gestionprojet.prj.service;

import java.util.List;
import com.gestionprojet.prj.entity.Site;

public interface ISiteService {
    Site saveSite(Site site);
    List<Site> getAllSites();
    Site getSiteById(Integer id);
    void deleteSiteById(Integer id);
    Site updateSite(Site site);
}
