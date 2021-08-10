package fr.epita.kesKonAVu.domain.resource;

public enum ResourceTypeEnum {
    MOVIE("movie"), SERIE("serie");
    private String valeur;

    ResourceTypeEnum(String valeur)
    {
        this.valeur = valeur;
    }

    public String toString ()
    {
        return valeur;
    }
}
