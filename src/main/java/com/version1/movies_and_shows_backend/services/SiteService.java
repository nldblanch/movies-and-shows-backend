package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Site;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import com.version1.movies_and_shows_backend.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {


    @Autowired
    SiteRepository siteRepository;

    @Autowired
    MediaRepository mediaRepository;

    public List<Site> getAllSites(){ return siteRepository.findAll();}

    public Site getByName(String name) {
        return siteRepository.findByNameIgnoreCase(name).orElse(null);
    }
    public Site save(Site site) {
        return siteRepository.save(site);
    }

    // get media by site
    public List<Media> getMediaBySite(String siteName) {
        return mediaRepository.findBySites_NameIgnoreCase(siteName);
    }

}
