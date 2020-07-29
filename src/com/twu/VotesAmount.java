import java.util.*;

public class VotesAmount implements Comparator<TrendingLL>{
        // @Override
        public int compare(TrendingLL t1, TrendingLL t2) {
            if(t1.votes < t2.votes){
               return 1;
           } else {
                return -1;
            }
        }
}