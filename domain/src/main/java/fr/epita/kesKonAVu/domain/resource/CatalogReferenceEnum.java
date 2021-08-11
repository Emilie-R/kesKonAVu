package fr.epita.kesKonAVu.domain.resource;

public enum CatalogReferenceEnum {
    OMDBAPI ("omdbapi.com"), NONE ("par défaut");

    private String valeur;

    CatalogReferenceEnum(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return valeur;
    }
}
