import java.util.List;

public class User {
    private int id;
    private String pseudo;
    private  String email;
    private String passWord;
    private String role;
    private List<Movie> movies;
    private List<Serie> series;
    private List<Evaluation> evaluations;
    private List<UserProgression> userProgressions;


    //Specific methods : à coder ***********!!!!!!!!!!!!!!
    public void Evaluate(){
    }
    public void ModifyUEvaluation(){
    }
    public void DeleteEvaluation(){
    }

    //constructors
    public User(){
    }

    public User (int id, String pseudo, String email, String passWord, String role) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.passWord = passWord;
        this.role = role;
    }
    //getters et setters
    public int getId ( ) { return id; }

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

    public List<Movie> getMovies ( ) {
        return movies;
    }

    public void setMovies (List<Movie> movies) {
        this.movies = movies;
    }

    public List<Serie> getSeries ( ) {
        return series;
    }

    public void setSeries (List<Serie> series) {
        this.series = series;
    }

    public List<Evaluation> getEvaluations ( ) {
        return evaluations;
    }

    public void setEvaluations (List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public List<UserProgression> getUserProgressions ( ) {
        return userProgressions;
    }

    public void setUserProgressions (List<UserProgression> userProgressions) {
        this.userProgressions = userProgressions;
    }
}
