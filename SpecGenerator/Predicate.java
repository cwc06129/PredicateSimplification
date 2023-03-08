/**
 * 2023-02-24(Fri) SoheeJung
 * Filename : Predicate.java
 */
package SpecGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class Predicate {
    // attribute
    private PredicateElement predicateRoot;
    private ArrayList<Variable> variables;

    // Constructor
    public Predicate(String[] predicateInput) {
        this.predicateRoot = makeParseTree(predicateInput);
    }

    // Constructor using variable
    public Predicate(PredicateElement predicateRoot) {
        this.predicateRoot = predicateRoot;
    }

    // original method
    /**
     * @date 2023-02-28(Tue)
     * @author SoheeJung
     * @name isNumeric
     * @param str : input string
     * @return true / false : if str is numeric, then return true. If not, then return false.
     */
    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @date 2023-02-24(Fri)
     * @author SoheeJung
     * @name makeParseTree
     * @param predicateList : preprocessing input data string list (split from space)
     * @return root : root of parse tree
     */
    public PredicateElement makeParseTree(String[] predicateList) {
        // required stack : operator stack / parsetree stack (predicateElement stack)
        /**
         * 2023-02-27(Mon)
         * SoheeJung
         * Fix the algorithm of parsetree generate part
         * <Algorithm>
         * 1. Read predicateList from the beginning.
         *      1-1. If it is a variable or constant, create a predicateElement node and put it in value. (leftchild = null / rightchild = null) Then push to the parsetree stack.
         *      1-2. If it is a operator, push to the operator stack.
         *      1-3. If it is a closing parentheses, pop from the operator stack.
         *          1-3-1. Check whether the operator is unary or binary, pop the parsetree stack as many as the required number of operands, 
         *                  and push the created parsetree to parsetree stack.
         *          1-3-2. Check the operator is "!", the parsetree's leftchild should be null, the parsetree's rightchild should be previous one.
         */
        PredicateElement root = null;
        ArrayList<String> operator_list = new ArrayList<String>(Arrays.asList("and", "or", "!", ">", "<", "==", ">=", "<=", "-", "+", "*"));
        Stack<String> operatorStack = new Stack<String>();
        Stack<PredicateElement> predElementStack = new Stack<PredicateElement>();
        Set<String> variable_name = new HashSet<String>();
        ArrayList<Variable> variable_list = new ArrayList<Variable>();

        for(int i = 0; i < predicateList.length; i++) {
            // Case1 : "("
            if(predicateList[i].equals("(")) operatorStack.push("(");
            
            // Case2 : logical_operator
            else if(operator_list.contains(predicateList[i])) {
                operatorStack.push(predicateList[i]);
            }
            // Case3 : ")"
            else if(predicateList[i].equals(")")) {
                String operator = "";
                ArrayList<String> operator_sub_list = new ArrayList<String>(Arrays.asList(">", "<", "==", ">=", "<=", "-", "+", "*"));
                ArrayList<String> and_or_operator = new ArrayList<String>(Arrays.asList("and", "or"));
                while(true) {
                    operator = operatorStack.pop();
                    
                    /**
                     * 2023-02-28(Tue)
                     * SoheeJung
                     * When we make parsetree, consider the starting parentheses
                     */
                    if(operatorStack.empty()) break;

                    if(operator.equals("(")) {
                        // Exception1 : check the peek is "!"
                        if(operatorStack.peek().equals("!")) {
                            operatorStack.pop();
                            PredicateElement operand = predElementStack.pop();
                            PredicateElement negation_element = new PredicateElement("not", null, null, operand);
                            operand.setParent(negation_element);
                            predElementStack.push(negation_element);
                        }
                        break;
                    }

                    // case3-1 : operator ">", "<", "==", ">=", "<="
                    if(operator_sub_list.contains(operator)) {
                        if(operator.equals("==")) {
                            PredicateElement operand_2 = predElementStack.pop();
                            PredicateElement operand_1 = predElementStack.pop();
                            PredicateElement equal_element = new PredicateElement("=", null, operand_1, operand_2);
                            operand_2.setParent(equal_element);
                            operand_1.setParent(equal_element);
                            predElementStack.push(equal_element);
                        } else {
                            PredicateElement operand_2 = predElementStack.pop();
                            PredicateElement operand_1 = predElementStack.pop();
                            PredicateElement relation_element = new PredicateElement(operator, null, operand_1, operand_2);
                            operand_2.setParent(relation_element);
                            operand_1.setParent(relation_element);
                            predElementStack.push(relation_element);
                        }
                    } 

                    /**
                     * 2023-02-28(Tue)
                     * SoheeJung
                     * remove negation handling part
                     */

                    // // case3-2 : operator "!"
                    // else if(operator.equals("!")) {
                    //     PredicateElement operand = predElementStack.pop();
                    //     predElementStack.push(new PredicateElement("not", null, operand));
                    // }

                    // case3-3 : operator "and", "or"
                    else if(and_or_operator.contains(operator)) {
                        PredicateElement operand_2 = predElementStack.pop();
                        PredicateElement operand_1 = predElementStack.pop();
                        PredicateElement and_or_element = new PredicateElement(operator, null, operand_1, operand_2);
                        operand_2.setParent(and_or_element);
                        operand_1.setParent(and_or_element);
                        predElementStack.push(and_or_element);
                    }
                }
                
                /**
                 * 2023-02-28(Tue)
                 * SoheeJung
                 * remove exception handling part (because we consider starting parentheses)
                 */

                // // If operatorStack is empty, then continue.
                // if(operatorStack.empty()) continue;

                // // Exception1 : check the peek is "!"
                // if(operatorStack.peek().equals("!")) {
                //     operatorStack.pop();
                //     PredicateElement operand = predElementStack.pop();
                //     predElementStack.push(new PredicateElement("not", null, operand));
                // }

                // // Exception2 : check the peek is "and", "or"
                // else if(and_or_operator.contains(operatorStack.peek())) {
                //     String and_or = operatorStack.pop();
                //     PredicateElement operand_2 = predElementStack.pop();
                //     PredicateElement operand_1 = predElementStack.pop();
                //     predElementStack.push(new PredicateElement(and_or, operand_1, operand_2));
                // }
            } 
            // Case4 : variable or constant
            else {
                predElementStack.push(new PredicateElement(predicateList[i], null, null, null));

                if(!isNumeric(predicateList[i])) {
                    variable_name.add(predicateList[i]);
                }
            }
        }

        // final parsetree saving
        root = predElementStack.pop();

        // final set variables
        Iterator<String> it = variable_name.iterator();

        while(it.hasNext()) {
            variable_list.add(new Variable(it.next()));
        }
        setVariables(variable_list);

        return root;
    }

    public void traverse(PredicateElement root) {
        if(root != null) {
            if((root.getValue().equals("and")) && (!(root.getLeftchild().getValue().equals("and")) && !(root.getRightchild().getValue().equals("and")))) {
                System.out.println(printPrefix(root));
            }
            traverse(root.getLeftchild());
            traverse(root.getRightchild());
        }
    }

    /**
     * @date 2023-02-26(Sun)
     * @author SoheeJung
     * @name printInfix
     * @param root : predicate root node
     * @return infixString : inorder parsing string
     */
    public String printInfix(PredicateElement root) {
        String infixString = "";

        if(root != null) {
            infixString += printInfix(root.getLeftchild());
            infixString += root.getValue() + " ";
            infixString += printInfix(root.getRightchild());
        }

        return infixString;
    }

    /**
     * @date 2023-02-26(Sun)
     * @author SoheeJung
     * @name printPrefix
     * @param root : predicate root node
     * @return prefixString : prefix parsing string
     */
    public String printPrefix(PredicateElement root) {
        String prefixString = "";
        ArrayList<String> operator_list = new ArrayList<String>(Arrays.asList("and", "not", "or", ">", "<", "=", ">=", "<=", "-", "+", "*"));

        if(root != null) {
            if(operator_list.contains(root.getValue())) {
                prefixString += "( ";
                prefixString += root.getValue();
                prefixString += " ";
                prefixString += printPrefix(root.getLeftchild());
                prefixString += printPrefix(root.getRightchild());
                prefixString += ") ";
            } else {
                prefixString += root.getValue();
                prefixString += " ";
                prefixString += printPrefix(root.getLeftchild());
                prefixString += printPrefix(root.getRightchild());
            }
        }

        return prefixString;
    } 

    // Getter & Setter
    public PredicateElement getPredicateRoot() {
        return predicateRoot;
    }

    public void setPredicateRoot(PredicateElement predicateRoot) {
        this.predicateRoot = predicateRoot;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }
}
