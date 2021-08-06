public class Episode {
    private int id;
    private int number;
    private String title;
    private String externalId;
    private Status status;

    public Episode(){

    }

    public Episode (int id, int number, String title, String externalId) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.externalId = externalId;
    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getNumber ( ) {
        return number;
    }

    public void setNumber (int number) {
        this.number = number;
    }

    public String getTitle ( ) {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getExternalId ( ) {
        return externalId;
    }

    public void setExternalId (String externalId) {
        this.externalId = externalId;
    }

    public Status getStatus ( ) {
        return status;
    }

    public void setStatus (Status status) {
        this.status = status;
    }
}
