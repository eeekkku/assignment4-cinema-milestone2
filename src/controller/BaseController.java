package controller;
import model.Movie;
import service.BaseService;
import java.util.List;

public class BaseController {
    private final BaseService service;

    public BaseController(BaseService service) {
        this.service = service;
    }

    public Movie createMovie(String title, int duration, String genre) {
        return service.createMovie(new Movie(title, duration, genre));
    }

    public Movie getMovie(int id) {
        return service.getMovie(id);
    }

    public List<Movie> listMovies() {
        return service.getAllMovies();
    }

    public void deleteMovie(int id) {
        service.deleteMovie(id);
    }
}
