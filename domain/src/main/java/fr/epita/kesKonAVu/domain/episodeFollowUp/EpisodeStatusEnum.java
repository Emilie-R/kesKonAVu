package fr.epita.kesKonAVu.domain.episodeFollowUp;

public enum EpisodeStatusEnum {

    AVOIR("à voir"), VU("visionné");
    private String valeur;

    EpisodeStatusEnum(String valeur)
    {
        this.valeur = valeur;
    }

    public String toString ()
    {
        return valeur;
    }
}
