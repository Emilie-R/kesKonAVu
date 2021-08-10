package fr.epita.kesKonAVu.domain.Enums;

public enum EnumsResourceTypes {
    MOVIE("movie"), SERIE("serie");
    private String valeur;

    EnumsResourceTypes (String valeur)
    {
        this.valeur = valeur;
    }

    public String toString ()
    {
        return valeur;
    }
}
