public class UserLL {
    String name;
    int votes;
    public UserLL(String username){
        name = username;
        votes = 10;
    }
    public String toString() {
        return name + " " + votes;
    }
}