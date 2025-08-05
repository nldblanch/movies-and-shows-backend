package com.version1.movies_and_shows_backend.controllers;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.dtos.MediaDTO;
import com.version1.movies_and_shows_backend.services.GenreService;
import com.version1.movies_and_shows_backend.services.GenreStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreStatsService genreStatsService;
    // GET /genres
    @GetMapping
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }


    // GET /genres/{name}
    @GetMapping("/{name}")
    public GenreDTO getGenreByName(@PathVariable String name) {
        return genreService.getGenreByName(name);
    }

//    // GET /genres/{name}/movies
//    @GetMapping("/{name}/movies")
//    public List<MediaDTO> getGenreMovies(@PathVariable String name) {
//        return genreService.getMoviesByGenre(name);
//    }
//
//    // GET /genres/{name}/shows
//    @GetMapping("/{name}/shows")
//    public List<MediaDTO> getGenreShows(@PathVariable String name) {
//        // Assuming the service method is implemented to filter shows by genre
//        return genreService.getShowsByGenre(name); // Adjust this method to filter shows if needed
//    }

         //GET /genres/{name}/top10movies
         @GetMapping("/{name}/top10movies")
         public List<MediaDTO> getTop10MoviesByGenre(@PathVariable String name) {
             return genreService.getTop10MoviesByGenre(name);
         }
         //GET /genres/{name}/top10shows
         @GetMapping("/{name}/top10shows")
         public List<MediaDTO> getTop10ShowsByGenre(@PathVariable String name)
         {
             return genreService.getTop10ShowsByGenre(name);
         }


    @PostMapping("/generate-stats")
    public ResponseEntity<String> generateAllStats() {
        genreStatsService.generateAllStats();
        return ResponseEntity.ok("Genre stats generated successfully.");
    }
    // GET /genres/{name}/analytics
//    @GetMapping("/{name}/analytics")
//    public GenreAnalytics getGenreAnalytics(@PathVariable String name) {
//        return genreService.getGenreAnalytics(name);
//    }

    //
}
