import java.util.*;
import java.util.stream.Collectors;

public class Operation {
    static LinkedList<TrendingLL> trendingList = new LinkedList<TrendingLL>();
    static LinkedList<UserLL> userList = new LinkedList<UserLL>();
    // Check the trending board
    public static void checkTrending(){ 
        // if it's empty list, add topic first
        if (trendingList.isEmpty()) {
            System.out.println("There is no trending topics, please add first! \n");
        }else{  // if not empty, show the trending board
            System.out.println("Here is the trending board: ");
            //  literator the trendinglist, print then out
            int i = 1;
            // sort the trendinglist
            Collections.sort(trendingList, new VotesAmount());
            for (TrendingLL t: trendingList){ //print out sorting list
               System.out.println("No."+i+":  "+t);
            }
            System.out.println(" ");
        }
    }
    // Add trending
    public static void addTrending( Boolean supertrending){
        System.out.println("Please enter the trending name: ");
        Scanner ipt = new Scanner(System.in);
        String topicName = ipt.nextLine();
        //  if it's empty list (first login), then create new object and add to list
        if (trendingList.isEmpty()){
            TrendingLL trending = new TrendingLL(topicName);
            if(supertrending) trending.issuper = true;
            trendingList.add(trending);
            System.out.println("Add Successful!! \n");
        
        }else{   // Check if trending exist, or create new object and add to list
            if(trendingList.stream().filter(c -> c.name.equals(topicName)).count() == 0){
                TrendingLL trending = new TrendingLL(topicName);
                if(supertrending) trending.issuper = true;   //check if it's super trending
                trendingList.add(trending);
                System.out.println("Add Successful!! \n");
            }else{  // if exist, print exist
                if(trendingList.stream().filter(c -> c.name.equals(topicName)).count() > 0) 
                    System.out.println("Trending exist!\n");
            }

        }
    }
    // Vote for trending
    public static void voteTrending(String userinfo){
        String username = userinfo.split(" ")[0];
        String uservote = userinfo.split(" ")[1];

        System.out.println("You have ( "+uservote+" ) votes! ");
        System.out.println("Please enter the trending you want to vote: ");
        Scanner iptname = new Scanner(System.in);
        String topicName = iptname.nextLine();
        if(trendingList.stream().filter(c -> c.name.equals(topicName)).count() == 0) {
            System.out.println("Trending not exist, please change or add trending!!\n");

        }else{
            System.out.println("Please enter the number you want to vote: ");
            Scanner iptnum = new Scanner(System.in);
            int voteNum = Integer.parseInt(iptnum.next());
            // update the user's votes and trending votes
            userList.forEach(u->{
                if(u.name.equals(username)){
                    if(u.votes < voteNum){
                        System.out.println("Sorry your vote's amount not enough! \n");
                    }else{  //decrease the user votes amount
                        u.votes -= voteNum;
                        // increase the trending vote amount 
                        trendingList.forEach(t -> {
                            if(t.name.equals(topicName)){
                                t.votes += t.issuper ? voteNum*2 : voteNum; 
                            }
                        });
                        System.out.println("Vote Successful!! \n");
                    }
                }
            });
        }
    }
  
    public static void runOperation(char op, UserLL user){
        // check if user is in the list,if not add to list, else keep original data
        if(userList.isEmpty()) userList.add(user);
        else if(userList.stream().filter(c -> c.name.equals(user.name)).count() == 0){
            userList.add(user);
        }
        String userinfo = userList.stream().filter(c -> c.name.equals(user.name)).collect(Collectors.toList()).get(0).toString();

        switch(op){
            case 'c':
                checkTrending();
                break;
            case 'a' :
                addTrending(false);
                break;
            case 's' :
                addTrending(true);
                break;
            case 'v' :
                voteTrending(userinfo);
                break;
            case 'b' :
                // buyTrending();
                break;
            default:
                System.out.println("Please input valid code");
                break;
        }
    }
}
class VotesAmount implements Comparator<TrendingLL>{
         @Override
         public int compare(TrendingLL t1, TrendingLL t2) {
             if(t1.votes < t2.votes){
                return 1;
            } else {
                 return -1;
             }
         }
 }