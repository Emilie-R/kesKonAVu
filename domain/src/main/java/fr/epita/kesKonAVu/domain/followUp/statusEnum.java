package fr.epita.kesKonAVu.domain.followUp;

public enum statusEnum {

    AVOIR("à voir"), VU("visionné");
    private String valeur;

    statusEnum(String valeur)
    {
        this.valeur = valeur;
    }

    public String toString ()
    {
        return valeur;
    }
}
