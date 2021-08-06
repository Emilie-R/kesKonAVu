public enum Status {

    AVOIR("à voir"), VU("visionné");
    private String valeur;

    Status(String valeur)
    {
        this.valeur = valeur;
    }

    public String toString ()
    {
        return valeur;
    }
}
