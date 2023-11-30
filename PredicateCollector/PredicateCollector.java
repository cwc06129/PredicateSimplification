import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @date 2023-05-19(Fri)
 * @author SoheeJung
 * @name PredicateCollector
 * extrace all the predicates from model.txt's final model part
 */
public class PredicateCollector {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(("/home/jungsohee/ActiveLearning/PredicateSimplication/experiment/test/input.txt")));
        
        String str = reader.readLine();
        // split [ or ] part
        String [] strArr = str.split("\\[|\\]");
        
        predicateHaveState(strArr);
        predicateEvent(strArr);      

        reader.close();
    }

    /**
     * @date 2023-05-19(Fri)
     * @author SoheeJung
     * @name predicateEvent
     */
    public static void predicateEvent(String [] strArr) throws IOException {
        File file1 = new File("/home/jungsohee/ActiveLearning/PredicateSimplication/experiment/test/pred_event.txt");
        PrintWriter writer1 = new PrintWriter(new FileWriter(file1));
        File file2 = new File("/home/jungsohee/ActiveLearning/PredicateSimplication/experiment/test/onlyPred.txt");
        PrintWriter writer2 = new PrintWriter(new FileWriter(file2));

        ArrayList<String> predStrArr = new ArrayList<>();
        for(String s : strArr) {
            // if split string is just space or comma, then continue
            if(s.equals(" ") || s.equals(", ")) continue;
            // Find the contents that is inside of ''
            Pattern p = Pattern.compile("\\'(.*?)\\'");
            Matcher m = p.matcher(s);
            while(m.find()) {
                String found = m.group(1);
                // if split string is just "start", then continue
                if(found.equals("start")) continue;
                // if split string don't contain colon, then continue (all the predicates have colon)
                if(!found.contains(":")) continue;
                predStrArr.add(found);
            }
        }

        // Variable predStrArr : collect all the predicates
        for(String s : predStrArr) {
            // collect event information
            writer1.println(s);
            // split predicate and event that seperate by colon
            String [] temp = s.split(":");
            writer2.println(temp[0]);
        }

        writer1.close();
        writer2.close();
    }

    /**
     * @date 2023-05-19(Fri)
     * @author SoheeJung
     * @name predicateHaveState
     */
    public static void predicateHaveState(String [] strArr) throws IOException {
        File file = new File("/home/jungsohee/ActiveLearning/PredicateSimplication/experiment/test/pred_event_state.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        
        for(String s : strArr) {
            // if split string is just space or comma, then continue
            if(s.equals(" ") || s.equals(", ")) continue;
            
            // collect state information
            String [] stateStr = s.split(", ");
            if(stateStr.length < 3) continue;
            writer.println("ingoing state : " + stateStr[0].trim());
            writer.println("outgoing state : " + stateStr[2].trim());
            

            // Find the contents that is inside of ''
            Pattern p = Pattern.compile("\\'(.*?)\\'");
            Matcher m = p.matcher(s);
            while(m.find()) {
                String found = m.group(1);
                // if split string is just "start", then file write and continue
                if(found.equals("start")) {
                    writer.println("event : start");
                    continue;
                }
                // if split string don't contain colon, then file write and continue (all the predicates have colon)
                if(!found.contains(":")) {
                    writer.println("event : " + found);
                    continue;
                }
                String [] temp = found.split(":");
                // collect predicate & event information
                writer.println("predicate : " + temp[0].trim());
                writer.println("event : " + temp[1].trim());
            }
            writer.println();
        }

        writer.close();
    }
}
