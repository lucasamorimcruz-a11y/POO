
public class Filme {
    private String movieName;
    private int releaseDate;
    private double movieLength;

    public String getMovieName() {
        return this.movieName;
    }

    public int getReleaseDate() {
        return this.releaseDate;
    }

    public double getMovieLength() {
        return this.movieLength;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setMovieLength(double movieLength) {
        this.movieLength = movieLength;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Boolean equals(Filme filme) {
        return ((filme.getMovieName().equals(this.movieName) && (filme.getReleaseDate() == this.releaseDate)
                && (filme.getMovieLength() == this.movieLength)));
    }

    public Filme (){
        this.movieName = "";
        this.releaseDate = -1;
        this.movieLength = -1;
    }

    public Filme(String movieName, int releaseDate) {
        this.movieName = movieName;
        this.releaseDate = releaseDate;
    }

    public Filme(String movieName, double movieLength) {
        this.movieName = movieName;
        this.movieLength = movieLength;
    }
}
