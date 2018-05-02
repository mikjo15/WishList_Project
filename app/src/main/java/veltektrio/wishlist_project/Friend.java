package veltektrio.wishlist_project;

//Bruges til når vi henter listen af venner fra databasen
//Her indlæses både ID og username
//Disse gemmes i denne classe

public class Friend {

    public Friend(){}
    public Friend(String UN){ username=UN;}

    public Friend(String username, String id) {
        this.username = username;
        this.id = id;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {this.id = id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String username;
    public String id;


}
