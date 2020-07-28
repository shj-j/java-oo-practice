import java.util.*;
import java.util.Scanner;

public class Operation {
    static LinkedList<String> trendingList = new LinkedList<String>();
    // static Scanner inputTopic = null;
    // Check the trending topics
    public static boolean checkTrending(){
        // if it's empty list, add topic first
        if (trendingList.isEmpty()) {
            System.out.println("There is no trending topics, please add first!");
            addTrending();
            return false;
        }else{  // if not empty, show the trending board
            System.out.println("Here is the trending board: ");
            //  literator the trendinglist, print then out
            Iterator<String> iterator=trendingList.iterator();
            int i = 0;
            while(iterator.hasNext()){
               System.out.println(i+" "+iterator.next());
               i++;
            }
            return true;
        }
    }
    // Add trending
    public static void addTrending(){
        System.out.println("Please enter the trending name: ");
        Scanner ipt = new Scanner(System.in);
        String topicName = ipt.next();
        TrendingLL trending = new TrendingLL(topicName);
        trendingList.add(trending.toString());
        System.out.println("Add Successfully!! \n");
    }

    public static void runOperation(char op){
        switch(op){
            case 'c':
                checkTrending();
                break;
            case 'a' :
                addTrending();
                break;
            case 'v' :
                // voteTrending();
                break;
            case 'b' :
                // butTrending();
                break;
            default:
                System.out.println("Please input valid code");
                break;
        }
    }
}