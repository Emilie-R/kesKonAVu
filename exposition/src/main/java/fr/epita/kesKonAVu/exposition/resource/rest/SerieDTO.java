package fr.epita.kesKonAVu.exposition.resource.rest;

public class SerieDTO extends ResourceDTO{
    private int numberOfSeasons;
    private int numberOfEpisodes;
    public SerieDTO(){}

    public int getNumberOfSeasons ( ) {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons (int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfEpisodes ( ) {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes (int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
}
