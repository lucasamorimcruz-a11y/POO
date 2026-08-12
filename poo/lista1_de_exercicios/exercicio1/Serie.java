public class Serie {
    private String seriesName;
    private int amountOfEpisodes;
    private int seasonReleaseDate;

    public String getSeriesName() {
        return seriesName;
    }

    public int getAmountOfEpisodes() {
        return amountOfEpisodes;
    }

    public int getSeasonReleaseDate() {
        return seasonReleaseDate;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public void setAmountOfEpisodes(int amountOfEpisodes) {
        this.amountOfEpisodes = amountOfEpisodes;
    }

    public void setSeasonReleaseDate(int seasonReleaseDate) {
        this.seasonReleaseDate = seasonReleaseDate;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Boolean equals(Serie serie) {
        return (this.amountOfEpisodes == serie.getAmountOfEpisodes()) && (this.seriesName.equals(serie.getSeriesName()))
                && this.amountOfEpisodes == serie.getAmountOfEpisodes();
    }

    public Serie(String seriesName, int amountOfEpisodes) {
        this.seriesName = seriesName;
        this.amountOfEpisodes = amountOfEpisodes;
    }

    public Serie(int amountOfEpisodes, int seasonReleaseDate) {
        this.amountOfEpisodes = amountOfEpisodes;
        this.seasonReleaseDate = seasonReleaseDate;
    }
}
