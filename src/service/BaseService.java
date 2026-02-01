package service;
import model.Movie;
import java.util.List;

public interface BaseService {
    Movie createMovie(Movie movie);

    Movie getMovie(int id);

    List<Movie> getAllMovies();

    void deleteMovie(int id);
}
