package fr.epita.kesKonAVu.domain.Enums;

public enum EnumStatus {

    AVOIR("à voir"), VU("visionné");
    private String valeur;

    EnumStatus (String valeur)
    {
        this.valeur = valeur;
    }

    public String toString ()
    {
        return valeur;
    }
}
