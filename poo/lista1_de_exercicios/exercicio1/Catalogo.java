/**
 * Catalogo
 */
public class Catalogo {
    private int numberOfMovies;
    private int numberOfSeries;
    private int portugueseMovies;

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public int getNumberOfSeries() {
        return numberOfSeries;
    }

    public int getPortugueseMovies() {
        return portugueseMovies;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public void setNumberOfSeries(int numberOfSeries) {
        this.numberOfSeries = numberOfSeries;
    }

    public void setPortugueseMovies(int portugueseMovies) {
        this.portugueseMovies = portugueseMovies;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (obj instanceof Catalogo){
            Catalogo catalogo = (Catalogo) obj;
            return this.numberOfSeries == catalogo.getNumberOfSeries() && this.portugueseMovies == catalogo.getPortugueseMovies() && this.numberOfMovies == catalogo.getNumberOfMovies();
        }
        return false;
    }

    public Catalogo(){

    }
    public Catalogo(int numberOfMovies, int numberOfSeries) {
        this.numberOfMovies = numberOfMovies;
        this.numberOfSeries = numberOfSeries;
    }
    public Catalogo(int portugueseMovies) {
        this.portugueseMovies = portugueseMovies;
    }

}