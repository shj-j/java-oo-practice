public class TrendingLL {
    String name;
    int votes;
    float price;
   
    public TrendingLL(String topicname){
        name = topicname;
        votes = 0;
        price = 0;
    }
    public String toString() {
        return name + " " + votes;
    }
     
    // public String getName(){
    //     return name;
    // }

    // public int getVote(){
    //     return votes;
    // }
    // public float getPrice() {
    //     return price;
    // }
}