package PredicateCollector;

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
        BufferedReader reader = new BufferedReader(new FileReader(("/home/jungsohee/ActiveLearning/PredicateSimplication/PredicateCollector/input.txt")));
        File file = new File("/home/jungsohee/ActiveLearning/PredicateSimplication/PredicateCollector/output.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        
        String str = reader.readLine();
        // split [ or ] part
        String [] strArr = str.split("\\[|\\]");
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
            // split predicate and event that seperate by colon
            String [] temp = s.split(":");
            writer.println(temp[0]);
        }

        reader.close();
        writer.close();
    }
}
