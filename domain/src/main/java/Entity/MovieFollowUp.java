package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class MovieFollowUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private LocalDate creationDate;
    private LocalDate lastModificationDate;
    private int note;
    private Movie movie;
    private User user;

    public MovieFollowUp (){

    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getNote ( ) {
        return note;
    }

    public void setNote (int note) {
        this.note = note;
    }

    public LocalDate getLastModificationDate ( ) {
        return lastModificationDate;
    }

    public void setLastModificationDate (LocalDate lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }
    public User getUser ( ) {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public String getStatus ( ) {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public LocalDate getCreationDate ( ) {
        return creationDate;
    }

    public void setCreationDate (LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Movie getMovie ( ) {
        return movie;
    }

    public void setMovie (Movie movie) {
        this.movie = movie;
    }
}
