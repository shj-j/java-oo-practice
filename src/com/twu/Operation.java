import java.util.*;
import java.util.stream.Collectors;

public class Operation {
    static LinkedList<TrendingLL> trendingList = new LinkedList<TrendingLL>();
    static LinkedList<UserLL> userList = new LinkedList<UserLL>();
    static List<TrendingLL> sortList = new LinkedList<TrendingLL>();
    // Check the trending board
    public static void checkTrending(){ 
        // if it's empty list, add topic first
        if (trendingList.isEmpty()) {
            System.out.println("There is no trending topics, please add first! \n");
        }else{  // if not empty, show the trending board
            System.out.println("Here is the trending board: ");
            //  literator the trendinglist, print then out
            int i = 1;
            sortList = trendingList.stream().sorted((a,b)->b.votes-a.votes).sorted((a,b)-> b.price-a.price).collect(Collectors.toList());
            for (TrendingLL t: sortList){ //print out sorting list
               System.out.println("No."+i+":  "+t);
               i++;
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

    public static void buyTrending(){
        System.out.println("Please enter the trending you want to buy: ");
        Scanner iptname = new Scanner(System.in);
        String topicName = iptname.nextLine();
        // check if the trending exist
        if(trendingList.stream().filter(c -> c.name.equals(topicName)).count() == 0) {
            System.out.println("Trending not exist, please change or add trending!!\n");

        }else{ // if is exist
            System.out.println("Please enter the rank you want to buy: ");
            Scanner iptrank = new Scanner(System.in);
            int buyRank = Integer.parseInt(iptrank.next());

            System.out.println("Please enter the price you want to give: ");
            Scanner iptprice = new Scanner(System.in);
            int buyPrice = Integer.parseInt(iptprice.next());

            int i=0;
            Boolean sup = false;
            int votes = 0;
            TrendingLL repTopic = null; // 
            TrendingLL deleteTopic = null; // the trending that want to buy rank, delete it and change the rank place topic to this topic
            // get the info of the buy trending
            for(TrendingLL t : trendingList){
                if (t.name.equals(topicName)){
                    votes = t.votes;
                    sup = t.issuper;
                    deleteTopic = t;
                };
            }  
            // find the trending (rank == buy rank-1) that need to replace, 
            for (TrendingLL t : sortList){
                if(i == (buyRank-1)){
                    if(t.price < buyPrice){
                        repTopic = t;
                        t.price = buyPrice;
                        t.name = topicName;
                        t.votes = deleteTopic.votes;
                        t.issuper = sup; // replace all the info, 
                        //and then deletet the trending that want to buy
                        sortList.remove(deleteTopic);
                        trendingList.remove(deleteTopic);
                        System.out.println("Success!!\n");
                        break;                        
                    }else{
                        System.out.println("Fail to buy because the price is not high enough!!\n");
                    }
                }else{
                    i++;
                }
            }
            // replace the original place trending to buy trending
            for (TrendingLL t : trendingList){
                if(t == repTopic){
                    t.price = buyPrice;
                    t.name = topicName;
                    t.votes = votes;
                    t.issuper = sup;
                }
            }
            
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
                buyTrending();
                break;
            default:
                System.out.println("Please input valid code");
                break;
        }
    }
}