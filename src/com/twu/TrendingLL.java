public class TrendingLL {
    String name;
    int votes;
    float price;
    Boolean issuper;
   
    public TrendingLL(String topicname){
        name = topicname;
        votes = 0;
        price = 0;
        issuper = false; 
    }
    public String toString() {
        return name + " " + votes;
    }
}