package fr.epita.kesKonAVu.exposition.member.rest;

public class MemberDTOLight {

    private String pseudo;
    private String email;
    private String password;

    public MemberDTOLight() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}