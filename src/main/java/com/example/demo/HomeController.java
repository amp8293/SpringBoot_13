package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String loadData(Model model) {

        Movie movie = new Movie();
        movie.setTitle("Emoji Movie");
        movie.setYear(2017);
        movie.setDescription("About Emojis...");
        movieRepository.save(movie);

        Actor actor = new Actor();
        actor.setName("Sandra Bullock");
        actor.setRealname("Sandra Mae Bullowski");
        actor.addMovie(movie);
        actorRepository.save(actor);

        Actor a = new Actor();
        a.setName("Mary Smith");
        a.setRealname("Mary Real Smith");
        actorRepository.save(a);

        // doing the actor.addMovie() did not update the MOVIE_ACTOR table
        movie.addActor(a);
        movie.addActor(actor);
        movieRepository.save(movie);

        model.addAttribute("actors",actorRepository.findAll());
        model.addAttribute("movies",movieRepository.findAll());
        return "index";
    }
}
