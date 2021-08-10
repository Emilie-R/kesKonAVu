package fr.epita.kesKonAVu.domain.Enums;

public enum EnumRoles {

        USER("user"), ADMIN("administrator");
        private String valeur;

        EnumRoles(String valeur)
        {
            this.valeur = valeur;
        }

        public String toString ()
        {
            return valeur;
        }

}
