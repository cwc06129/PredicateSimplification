/**
 * 2023-02-24(Fri) SoheeJung
 * Filename : SpecGenerator.java
 */
package SpecGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

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
     * @param filepath : output spec file path
     * @param prefixString : prefix precessed string
     * @param variables : variables that define in prefixString
     * @throws IOException
     */
    public void makeSpecFile(String filepath, String prefixString, ArrayList<Variable> variables) throws IOException {
        File file = new File(filepath);
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        // file contents
        printWriter.print("(set-logic LIA)\n\n");
        printWriter.print("(synth-fun simplify ( ");
        
        // 2023-03-08(Wed) SoheeJung
        // Deletion this part : Using real variable
        // // temporary varaible select
        // char ch = 'a';
        // for(int i=1; i<=variables.size(); i++) {
        //     printWriter.print("(" + ch + " Int) ");
        //     ch += 1;
        // }

        // 2023-03-08(Wed) SoheeJung
        // real variable get
        for(Variable var : variables) {
            printWriter.print("(" + var.getName() + " Int) ");
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

        // 2023-03-08(Wed) SoheeJung
        // Deletion this part : Using real variable
        // ch = 'a';
        // for(int i=1; i<=variables.size(); i++) {
        //     printWriter.print("\t\t" + ch + "\n");
        //     ch += 1;
        // }

        // 2023-03-08(Wed) SoheeJung
        // real variable get
        for(Variable var : variables) {
            printWriter.print("\t\t" + var.getName() + "\n");
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
     * @throws InterruptedException
     */
    public String eusolverConnector(String filepath) throws IOException, InterruptedException {
        Process process = null;
        String str = null;
        String result = null;

        try {
            System.out.println("**********    Eusolver Start    **********");
            process = Runtime.getRuntime().exec("eusolver/eusolver" + " " + filepath);

            // if (!process.waitFor(30, TimeUnit.SECONDS)) {
            //     process.destroy();
            //     process.destroyForcibly();
            //     System.out.println("Timeout occurred!!");
            //     return null;
            // }
            
            // if(process.waitFor(15, TimeUnit.SECONDS)) {
            //     BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
            //     while((str = stdOut.readLine()) != null) {
            //         System.out.println(str);
            //         if(!str.contains("simplify")) {
            //             result = str.trim();
            //             // Last parenthesis elimination
            //             result = result.substring(0, result.length() - 1);
            //         }
            //     }
            //     System.out.println("**********    Eusolver Finish    **********\n");
            //     return result;
            // }
            // else {
            //     // 2023-03-16(Thu) SoheeJung
            //     // kill zombie eusolver process
            //     String killComment = ("killall python3.6");
            //     Process p = Runtime.getRuntime().exec(killComment.split(" "));
            //     p.waitFor();
            //     System.out.println("Timeout occurred !!\n");
            //     return null;
            // }
            
            // 2023-03-31(Fri) SoheeJung
            // No-timeout eusolver execution
            BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while((str = stdOut.readLine()) != null) {
                System.out.println(str);
                if(!str.contains("simplify")) {
                    result = str.trim();
                    // Last parenthesis elimination
                    result = result.substring(0, result.length() - 1);
                }
            }
            System.out.println("**********    Eusolver Finish    **********\n");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @date 2023-03-09(Thu)
     * @Author SoheeJung
     * @name getElapsedTime
     * @param start : process start time
     * @param end : process end time
     */
    public static String getElapsedTime(long start, long end) {
        long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

        long different = end - start;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;
		
		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;
		
		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;
		
		long elapsedSeconds = different / secondsInMilli;
		
		return String.format("Total Conversion Time : %d days, %d hours, %d minutes, %d seconds%n", elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
    }

    // main
    public static void main(String[] args) throws IOException, InterruptedException {
        // 2023-03-31(Fri) SoheeJung
        // experiment line addition (input from file / output to file)
        BufferedReader reader = new BufferedReader(new FileReader(("./experiment/copy.txt")));
        PrintWriter writer = new PrintWriter(new FileWriter("./experiment/predicate_triangle_output.txt"));

        String str;
        Integer index = 19;
        while((str = reader.readLine()) != null) {
            // 2023-03-09(Thu) SoheeJung
            // Time Checking (start time)
            long startTime = System.currentTimeMillis();

            // Get Input String
            String input = str;
            writer.println(String.valueOf(index++) + ".");
            writer.println("[original predicate]");
            writer.println(input);

            // Make instance OR object
            SpecGenerator spec = new SpecGenerator(input);
            Predicate pred = spec.getPredicate();
            PredicateElement predElement = pred.getPredicateRoot();

            /**
             * 2023-04-04(Tue) SoheeJung
             * <Change the algorithm>
             * 1. no consider or / consider and
             * 2. if variable is same, then put same queue / if variable is different, then put defferent queue
             * 3. element in same queue, make spec file and then run EUsolver synthesis.
             */
            while(true) {
                PredicateElement orNode = pred.findNoCheckORNode(predElement);

                if((orNode.getValue().equals("or"))) {    
                    ArrayList<String> result = new ArrayList<>();
                    PredicateElement andNode = orNode.getLeftchild();
                    // make variable list
                    ArrayList<ArrayList<String>> unitPredicates = pred.collectUnitPredicate(andNode);
                    ArrayList<String> varlist = spec.collectAllvarFromAndNode(unitPredicates);
                    // make queue list (from variable information)
                    ArrayList<Queue<String>> queuelist = spec.makeQueueList(varlist.size());
                    spec.insertQueueElement(unitPredicates, queuelist, varlist);
                    // run EUsolver
                    for(int i = 0; i < queuelist.size(); i++) {
                        // if eucheck is false, then there is only one predicate.
                        // if eucheck is true, then there are many predicate in queuelist.
                        boolean eucheck = false;

                        while(true) {
                            if(queuelist.get(i).size() == 1) {
                                if(eucheck == false) {
                                    // run EUsolver first, and then get string from final string, and then add final result list
                                    // make spec file (.sl file)
                                    spec.makeSpecFile("./output.sl", queuelist.get(i).poll(), pred.getVariables());
        
                                    // eusolver execution
                                    String eusolver_result = spec.eusolverConnector("./output.sl");
                                    result.add(eusolver_result);
                                } else {
                                    result.add(queuelist.get(i).poll());
                                }
                                break;
                            } else {
                                eucheck = true;
                                String str1 = queuelist.get(i).poll();
                                String str2 = queuelist.get(i).poll();
                                String inputEusolver = "( and " + str1 + " " + str2 + ")";
                                // make spec file (.sl file)
                                spec.makeSpecFile("./output.sl", inputEusolver, pred.getVariables());
    
                                // eusolver execution
                                String eusolver_result = spec.eusolverConnector("./output.sl");
                                queuelist.get(i).add(eusolver_result);
                            }
                        }
                    }

                    // final result from all the variable's EUsolver synthesis.
                    String final_result = "";

                    for(int i = 0; i < (result.size() - 1); i++) {
                        final_result += "(and ";
                        final_result += result.get(i);
                        final_result += " ";
                    }
                    final_result += result.get(result.size() - 1);
                    for(int i = 0; i < (result.size() - 1); i++) {
                        final_result += ")";
                    }

                    // setting final result string to orNode's leftchild
                    PredicateElement euResultNode = new PredicateElement();
                    euResultNode.setValue(final_result);
                    euResultNode.setParent(orNode);
                    orNode.setLeftchild(euResultNode);
                    
                } else {
                    ArrayList<String> result = new ArrayList<>();
                    //pred.collectUnitPredicate(orNode);
                    // make variable list
                    ArrayList<ArrayList<String>> unitPredicates = pred.collectUnitPredicate(orNode);
                    ArrayList<String> varlist = spec.collectAllvarFromAndNode(unitPredicates);
                    // make queue list (from variable information)
                    ArrayList<Queue<String>> queuelist = spec.makeQueueList(varlist.size());
                    spec.insertQueueElement(unitPredicates, queuelist, varlist);

                    // run EUsolver
                    for(int i = 0; i < queuelist.size(); i++) {
                        while(true) {
                            if(queuelist.get(i).size() == 1) {
                                // run EUsolver first, and then get string from final string, and then add final result list
                                // make spec file (.sl file)
                                spec.makeSpecFile("./output.sl", queuelist.get(i).poll(), pred.getVariables());
    
                                // eusolver execution
                                String eusolver_result = spec.eusolverConnector("./output.sl");
                                result.add(eusolver_result);
                                break;
                            } else {
                                String str1 = queuelist.get(i).poll();
                                String str2 = queuelist.get(i).poll();
                                String inputEusolver = "( and " + str1 + " " + str2 + ")";
                                // make spec file (.sl file)
                                spec.makeSpecFile("./output.sl", inputEusolver, pred.getVariables());
    
                                // eusolver execution
                                String eusolver_result = spec.eusolverConnector("./output.sl");
                                queuelist.get(i).add(eusolver_result);
                            }
                        }
                    }

                    // final result from all the variable's EUsolver synthesis.
                    String final_result = "";

                    for(int i = 0; i < (result.size() - 1); i++) {
                        final_result += "(and ";
                        final_result += result.get(i);
                        final_result += " ";
                    }
                    final_result += result.get(result.size() - 1);
                    for(int i = 0; i < (result.size() - 1); i++) {
                        final_result += ")";
                    }

                    // setting final result string to orNode's rightchild
                    if(orNode.getParent() != null) {
                        PredicateElement euResultNode = new PredicateElement();
                        euResultNode.setValue(final_result);
                        euResultNode.setParent(orNode.getParent());
                        orNode.getParent().setRightchild(euResultNode);
                    } 
                    // if there is no or Node, then make this result root node.
                    else {
                        orNode.setValue(final_result);
                        orNode.setLeftchild(null);
                        orNode.setRightchild(null);
                    }

                    break;
                }
            }

            writer.println("\n[Final output]");
            System.out.println("Final result : " + pred.printPrefix(predElement) + "\n");
            writer.println(pred.printPrefix(predElement));

            long endTime = System.currentTimeMillis();
            
            writer.println("\n[Total time]");
            System.out.println(getElapsedTime(startTime, endTime));
            writer.println(getElapsedTime(startTime, endTime) + "\n");

            
            // /**
            //  * 2023-03-08(Wed) SoheeJung
            //  * <Algorithm>
            //  * 1. Make a prefix from the lowest predicate element
            //  * 2. Generate spec file(.sl file) using prefix
            //  * 3. Regenerate tree based on eusolver result
            //  *      3-1. eusolver result does not necessarily require each element to be make of each tree element.
            //  */
            // int mergePredicateCount = 1;

            // while(true) {
            //     System.out.println("##########      " + String.valueOf(mergePredicateCount) + " merge start     ###########");

            //     // 2023-03-09(Thu) SoheeJung
            //     // Initialize leavesParent set then get new leavesParent set.
            //     pred.setLeavesParent(new HashSet<PredicateElement>());
            //     Set<PredicateElement> leavesParent = pred.getLeavesParent();
            //     if(leavesParent.contains(predElement)) break;

            //     for(PredicateElement leafParent : leavesParent) {
            //         // leafParent is "-" OR "+", then make this prefix form leaf node.
            //         // leafParent is "or", then just merge all node. (using prefix form)
            //         if(leafParent.getValue().equals("-") || leafParent.getValue().equals("+") || leafParent.getValue().equals("or")) {
            //             leafParent.setValue(pred.printPrefix(leafParent));
            //             leafParent.setLeftchild(null);
            //             leafParent.setRightchild(null);
            //         }
            //         else {
            //             // make spec file (.sl file)
            //             spec.makeSpecFile("./output.sl", pred.printPrefix(leafParent), pred.getVariables());

            //             // eusolver execution
            //             String eusolver_result = spec.eusolverConnector("./output.sl");
                        
            //             // convert leafParent value from original to generated eusolver result.
            //             // and make leafParent to leaf node.
            //             if(eusolver_result != null) {
            //                 leafParent.setValue(eusolver_result);
            //                 leafParent.setLeftchild(null);
            //                 leafParent.setRightchild(null);
            //             } 
            //             // 2023-03-17(Fri) SoheeJung
            //             // predicate merge part (if eusolver result is null, then merge predicate.)
            //             else {
            //                 leafParent.setValue(pred.printPrefix(leafParent));
            //                 leafParent.setLeftchild(null);
            //                 leafParent.setRightchild(null);
            //             }
            //         }
            //     }

            //     System.out.println("##########      " + String.valueOf(mergePredicateCount) + " merge finish     ###########\n");
            //     mergePredicateCount++;
            // }



        //     writer.println("\n[Final output]");
        //     System.out.println("final result : " + pred.printPrefix(predElement));
        //     writer.println(pred.printPrefix(predElement));

        //     long endTime = System.currentTimeMillis();
            
        //     writer.println("\n[Total time]");
        //     System.out.println(getElapsedTime(startTime, endTime));
        //     writer.println(getElapsedTime(startTime, endTime) + "\n");
        }

        reader.close();
        writer.close();
    }

    // public method
    /**
     * @date 2023-04-05(Wed)
     * @author SoheeJung
     * @name collectAllvarFromAndNode
     * @param varlist
     * collect variables from and node parse tree's unit predicateElement (no duplication)
     */
    public ArrayList<String> collectAllvarFromAndNode(ArrayList<ArrayList<String>> varlist) {
        HashSet<String> set = new HashSet<String>();

        for(ArrayList<String> strlist : varlist) {
            for(int i = 0; i < (strlist.size() - 1); i++) {
                set.add(strlist.get(i));
            }
        }

        ArrayList<String> result = new ArrayList<>(set);

        return result;
    }

    /**
     * @date 2023-04-05(Wed)
     * @author SoheeJung
     * @param length
     * make as many queue arraylist as user want.
     */
    public ArrayList<Queue<String>> makeQueueList(Integer length) {
        ArrayList<Queue<String>> result = new ArrayList<>();

        for(int i = 0; i < length; i++) {
            result.add(new LinkedList<String>());
        }

        return result;
    }

    /**
     * @date 2023-04-06(Wed)
     * @author SoheeJung
     * @name insertQueueElement
     * @param list
     * @param queuelist
     * @param varlist
     * if the variable is same, then save them same queue.
     * if the variable is different, then save them defferent queue.
     */
    public void insertQueueElement(ArrayList<ArrayList<String>> list, ArrayList<Queue<String>> queuelist, ArrayList<String> varlist) {
        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < varlist.size(); j++) {
                if(list.get(i).get(list.get(i).size() - 1).contains(varlist.get(j))) {
                    queuelist.get(j).add(list.get(i).get(list.get(i).size() - 1));
                }
            }
        }
    }

    // Getter & Setter
    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }
}
