package fr.epita.kesKonAVu.domain.user;

public enum UserRoleEnum {

        USER("user"), ADMIN("administrator");
        private String valeur;

        UserRoleEnum(String valeur)
        {
            this.valeur = valeur;
        }

        public String toString ()
        {
            return valeur;
        }

}
