package model;

public class Session {
    private int id;
    private Movie movie;
    private Hall hall;

    public Session(int id, Movie movie, Hall hall) {
        this.id = id;
        this.movie = movie;
        this.hall = hall;
    }

}
