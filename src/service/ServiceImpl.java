package service;
import model.Movie;
import repository.BaseRepository;
import java.util.List;

public class ServiceImpl implements BaseService {
    private final BaseRepository repo;

    public ServiceImpl(BaseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Movie createMovie(Movie movie) {
        if (movie.getTitle() == null)
            throw new IllegalArgumentException("Movie title is required");
        return repo.save(movie);
    }

    @Override
    public Movie getMovie(int id) { return repo.findById(id); }

    @Override
    public List<Movie> getAllMovies() { return repo.findAll(); }

    @Override
    public void deleteMovie(int id) { repo.deleteById(id); }
}



