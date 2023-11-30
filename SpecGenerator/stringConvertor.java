import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class stringConvertor {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new FileReader(("/home/jungsohee/ActiveLearning/PredicateSimplication/experiment/test/onlyPred.txt")));
        String str;

        while((str = reader.readLine()) != null) {
            ArrayList<String> lineList = new ArrayList<String>();

            System.out.println("[Original Predicate]\n" + str);
            
            Pattern p = Pattern.compile("\\w+.\\w+ - \\w+.\\w+");
            Matcher m = p.matcher(str);
            
            while(m.find()) {
                lineList.add(m.group(0));
            }

            Set<String> finalLineList = Set.copyOf(lineList); 

            for(String replaceString : finalLineList) {
                System.out.println("[Replace String]\n" + replaceString);
                str = str.replace(replaceString, "(" + replaceString + ")");
            }

            System.out.println("[Modified Predicate]\n" + str);
        }
    }
}
