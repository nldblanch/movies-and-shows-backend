package com.version1.movies_and_shows_backend.controllers;

import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Site;
import com.version1.movies_and_shows_backend.services.SiteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/site")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @Transactional
    @GetMapping("/sites")
    public List<Site> getAllSites() {
        return siteService.getAllSites();
    }

    @Transactional
    @GetMapping("/sites/{name}/media")
    public List<Media> getMediaBySite(@PathVariable String name) {
        return siteService.getMediaBySite(name);
    }

    // will use if add description or other fields to site
    @Transactional
    @GetMapping("/sites/{name}")
    public Site getSiteByName(@PathVariable String name) {
        return siteService.getByName(name);
    }

    // get /sites/{name}/top10movies

    // get /sites/{name}/top10shows
}
