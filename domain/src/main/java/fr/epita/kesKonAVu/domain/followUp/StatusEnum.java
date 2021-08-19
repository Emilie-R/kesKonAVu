package fr.epita.kesKonAVu.domain.followUp;

public enum StatusEnum {

    AVOIR("à voir"), VU("visionné");
    private String valeur;

    StatusEnum(String valeur)
    {
        this.valeur = valeur;
    }

    public String toString ()
    {
        return valeur;
    }
}
