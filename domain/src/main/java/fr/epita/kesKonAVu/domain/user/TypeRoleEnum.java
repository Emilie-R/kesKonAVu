package fr.epita.kesKonAVu.domain.user;

public enum TypeRoleEnum {

        USER("user"), ADMIN("administrator");
        private String valeur;

        TypeRoleEnum(String valeur)
        {
            this.valeur = valeur;
        }

        public String toString ()
        {
            return valeur;
        }

}
