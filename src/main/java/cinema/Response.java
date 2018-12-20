package cinema;

import java.util.List;

public class Response {


    private List<Film> films;

    public Response(List<Film> films) {
        this.films = films;
    }


    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
