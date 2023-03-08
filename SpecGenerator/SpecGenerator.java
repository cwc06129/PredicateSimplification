/**
 * 2023-02-24(Fri) SoheeJung
 * Filename : SpecGenerator.java
 */
package SpecGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SpecGenerator {
    // attribute
    private Predicate predicate;

    // Constructor using String input
    public SpecGenerator(String input) {
        String[] preprocessedInput = preprocessingInputString(input);
        this.predicate = new Predicate(preprocessedInput);
    }

    // Constructor using variable
    public SpecGenerator(Predicate predicate) {
        this.predicate = predicate;
    }

    // original method
    /**
     * @date 2023-02-24(Fri)
     * @author SoheeJung
     * @name preprocessingInputString
     * @param inputString : predicate from generated model (complex predicate string)
     * @return result : preprocessed predicate string array (not sygus form)
     */
    public String[] preprocessingInputString(String inputString) {
        String[] result;
        
        inputString = inputString.replaceAll("\\(", "\\( ");
        inputString = inputString.replaceAll("\\)", " \\)");
        inputString = inputString.replaceAll("!", "! ");

        result = inputString.split(" ");
        
        return result;
    }

    /**
     * @date 2023-02-28(Tue)
     * @author SoheeJung
     * @name makeSpecFile : generate spec file (.sl file)
     * @param prefixString : prefix precessed string
     * @param variables : variables that define in prefixString
     * @throws IOException
     */
    public void makeSpecFile(String prefixString, ArrayList<Variable> variables) throws IOException {
        File file = new File("./output.sl");
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        // file contents
        printWriter.print("(set-logic LIA)\n\n");
        printWriter.print("(synth-fun simplify ( ");
        
        // temporary varaible select
        char ch = 'a';
        for(int i=1; i<=variables.size(); i++) {
            printWriter.print("(" + ch + " Int) ");
            ch += 1;
        }

        printWriter.print(") Bool\n");

        // Start part (StartBool part)
        printWriter.print(
            "\t((Start Bool (\n" +
            "\t\ttrue\n" +
            "\t\tfalse\n" +
            "\t\t(and Start Start)\n" +
            "\t\t(or Start Start)\n" +
            "\t\t(not Start)\n" +
            "\t\t(= StartInt StartInt)\n" +
            "\t\t(> StartInt StartInt)\n" +
            "\t\t(< StartInt StartInt)\n" +
            "\t\t(>= StartInt StartInt)\n" + 
            "\t\t(<= StartInt StartInt)\n" +
        "\t))\n"
        );

        // StartInt part
        printWriter.print("\t(StartInt Int (\n");

        ch = 'a';
        for(int i=1; i<=variables.size(); i++) {
            printWriter.print("\t\t" + ch + "\n");
            ch += 1;
        }
        for(int i=-1; i<=9; i++) {
            printWriter.print("\t\t" + String.valueOf(i) + "\n");
        }

        printWriter.print(
            "\t\t(+ StartInt StartInt)\n" +
            "\t\t(- StartInt StartInt)\n" +
            "\t))\n" +
            "\t)\n" +
            ")\n\n"
        );

        // declaration variable part
        for(Variable v : variables) {
            printWriter.print("(declare-var " + v.getName() + " Int)\n");
        }
        
        // constraint part
        printWriter.print("\n(constraint (= (simplify ");
        for(Variable v : variables) {
            printWriter.print(v.getName() + " ");
        }
        printWriter.print(")\n");

        // prefix constraint string(condition) part
        printWriter.print("\t" + prefixString + "\n");

        printWriter.print("\t))\n\n");
        printWriter.print("(check-synth)");

        printWriter.close();
    }
    
    /**
     * @date 2023-03-08(Wed)
     * @author SoheeJung
     * @name eusolverConnector : execute eusolver using command line
     * @param filepath : .sl file path
     * @throws IOException
     */
    public void eusolverConnector(String filepath) throws IOException {
        Process process = null;
        String str = null;

        try {
            System.out.println("**********    Eusolver Start    **********");
            process = Runtime.getRuntime().exec("eusolver/eusolver" + " " + filepath);
            BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while((str = stdOut.readLine()) != null) {
                System.out.println(str);
            }
            System.out.println("**********    Eusolver Finish    **********\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // main
    public static void main(String[] args) throws IOException {
        // Get Input String
        String input = "((!(rt_input.number > 7) and (rt_input.number > 1) and !(4 == rt_input.number) and (rt_input.number >= 6) and !(7 > rt_input.number)) or (!(rt_input.number > 7) and (rt_input.number > 1) and !(4 == rt_input.number) and !(rt_input.number >= 6)))";

        // Make instance OR object
        SpecGenerator spec = new SpecGenerator(input);
        Predicate pred = spec.getPredicate();
        PredicateElement predElement = pred.getPredicateRoot();

        /************* traverse해서 최하단부터 simplify하는 과정을 구현하면 됨. 2023-03-08(수) **************/
        // pred.traverse(predElement);

        // make spec file (.sl file)
        spec.makeSpecFile(pred.printPrefix(predElement), pred.getVariables());
        System.out.println("prefix : " + pred.printPrefix(predElement));
        
        // eusolver execution
        // spec.eusolverConnector(input);
    }

    // Getter & Setter
    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }
}
