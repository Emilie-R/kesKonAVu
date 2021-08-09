package Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pseudo;
    private  String email;
    private String passWord;
    private String role;
    private LocalDate creationDate;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<MovieFollowUp> movieFollowUps;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<SerieFollowUp> serieFollowUps;

    //Specific methods
    public void AddMovieFollowUp(MovieFollowUp movieFollowUp){
        movieFollowUps.add(movieFollowUp);
    }
    public void RemoveMovieFollowUp(MovieFollowUp movieFollowUp){
        movieFollowUps.remove(movieFollowUp);
    }
    public void AddSerieFollowUp(SerieFollowUp serieFollowUp){
        serieFollowUps.add(serieFollowUp);
    }
    public void RemoveSerieFollowUp(SerieFollowUp serieFollowUp){
        serieFollowUps.remove(serieFollowUp);
    }

    //constructors
    public User(){
    }

    //getters et setters

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getPseudo ( ) {
        return pseudo;
    }

    public void setPseudo (String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPassWord ( ) {
        return passWord;
    }

    public void setPassWord (String passWord) {
        this.passWord = passWord;
    }

    public String getRole ( ) {
        return role;
    }

    public void setRole (String role) {
        this.role = role;
    }

    public LocalDate getCreationDate ( ) {
        return creationDate;
    }

    public void setCreationDate (LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<MovieFollowUp> getMovieFollowUps ( ) {
        return movieFollowUps;
    }

    public void setMovieFollowUps (List<MovieFollowUp> movieFollowUps) {
        this.movieFollowUps = movieFollowUps;
    }

    public List<SerieFollowUp> getSerieFollowUps ( ) {
        return serieFollowUps;
    }

    public void setSerieFollowUps (List<SerieFollowUp> serieFollowUps) {
        this.serieFollowUps = serieFollowUps;
    }
}
