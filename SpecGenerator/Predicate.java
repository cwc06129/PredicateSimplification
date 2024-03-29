/**
 * 2023-02-24(Fri) SoheeJung
 * Filename : Predicate.java
 */
// package SpecGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Predicate {
    // attribute
    private PredicateElement predicateRoot;
    private ArrayList<Variable> variables;
    private Set<PredicateElement> leavesParent;

    // Constructor
    public Predicate(String[] predicateInput) {
        this.predicateRoot = makeParseTree(predicateInput);
        // 2023-04-05(Wed) SoheeJung
        // variable addition to predicateElement
        insertVariableToParsetree(getPredicateRoot());
        this.leavesParent = new HashSet<PredicateElement>();
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
                            PredicateElement negation_element = new PredicateElement("not", null, null, operand, false, null);
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
                            PredicateElement equal_element = new PredicateElement("=", null, operand_1, operand_2, false, null);
                            operand_2.setParent(equal_element);
                            operand_1.setParent(equal_element);
                            predElementStack.push(equal_element);
                        } else {
                            PredicateElement operand_2 = predElementStack.pop();
                            PredicateElement operand_1 = predElementStack.pop();
                            PredicateElement relation_element = new PredicateElement(operator, null, operand_1, operand_2, false, null);
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
                        PredicateElement and_or_element = new PredicateElement(operator, null, operand_1, operand_2, false, null);
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
                //     predElem entStack.push(new PredicateElement("not", null, operand));
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
                if(!isNumeric(predicateList[i])) {
                    variable_name.add(predicateList[i]);
                    predElementStack.push(new PredicateElement(predicateList[i], null, null, null, false, new Variable(null)));
                } else {
                    predElementStack.push(new PredicateElement(predicateList[i], null, null, null, false, null));
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

    /**
     * @date 2023-04-04(Tue)
     * @author SoheeJung
     * @name insertVariableToParsetree
     * @param root
     * insert Variable to variable predicateElement.
     */
    public void insertVariableToParsetree(PredicateElement root) {
        if(root != null) {
            insertVariableToParsetree(root.getLeftchild());
            for(Variable v : getVariables()) {
                if(v.getName().equals(root.getValue())) {
                    root.setVariable(v);
                    break;
                }
            }
            insertVariableToParsetree(root.getRightchild());
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

    /**
     * @date 2023-03-08(Wed)
     * @Author SoheeJung
     * @name findAllLeaves : find and save all leaf nodes's parent node
     * @param root : tree root node
     */
    public void findAllLeavesParent(PredicateElement root) {
        if(root == null) return;

        // if node is leaf node, then save arraylist
        if((root.getLeftchild() == null) && (root.getRightchild() == null)) {
            // 2023-03-17(Fri) SoheeJung
            // Another error : if both child is leaf nodes, then add leaves Parent. if not, don't do that. but current code add all the leaf nodes to leavesParent.
            leavesParent.add(root.getParent());

            // 2023-03-31(Fri) SoheeJung
            // Fix error
            if((root.getParent().getLeftchild() != null) && (root.getParent().getRightchild() != null) && 
            ((root.getParent().getLeftchild().getLeftchild() != null) || (root.getParent().getLeftchild().getRightchild() != null) 
            || (root.getParent().getRightchild().getLeftchild() != null) || (root.getParent().getRightchild().getRightchild() != null))) {
                leavesParent.remove(root.getParent());
            } 
            return;
        }
        
        // left child check recursively
        if(root.getLeftchild() != null) findAllLeavesParent(root.getLeftchild());

        // right child check recursively
        if(root.getRightchild() != null) findAllLeavesParent(root.getRightchild());
    }

    /**
     * @date 2023-04-04(Tue)
     * @author SoheeJung
     * @name findNoCheckORNode
     * @param root : root of parse tree
     * @return not checked or node
     */
    public PredicateElement findNoCheckORNode(PredicateElement root) {
        PredicateElement temp = root;
        PredicateElement finalOrNode = new PredicateElement();
        
        while(temp.getValue().equals("or")) {
            if(temp.getCheck() == false) {
                temp.setCheck(true);
                return temp;
            }
            else {
                temp = temp.getRightchild();
            }
        }

        return temp;
    }

    /**
     * @date 2023-04-04(Tue)
     * @author SoheeJung
     * @name collectUnitPredicate
     * @param root : root of AND Node
     * collect all the unit predicate from And node
     */
    public ArrayList<ArrayList<String>> collectUnitPredicate(PredicateElement root) {
        PredicateElement temp = root;
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

        while(temp.getValue().equals("and")) {
            if(temp.getCheck() == false) {
                temp.setCheck(true);
                ArrayList<String> varAndprefix = new ArrayList<String>();
                findWhichVariable(temp.getLeftchild(), varAndprefix);
                varAndprefix.add(printPrefix(temp.getLeftchild()));
                result.add(varAndprefix);
            }
            else {
                temp = temp.getRightchild();
            }
        }
        ArrayList<String> varAndprefix = new ArrayList<String>();
        findWhichVariable(temp, varAndprefix);
        varAndprefix.add(printPrefix(temp));
        result.add(varAndprefix);

        return result;
    }

    /**
     * @date 2023-04-05(Wed)
     * @author SoheeJung
     * @name findWhichVariable
     * @param root
     * find the varaible predicateElement from and Node parse tree
     */
    public void findWhichVariable(PredicateElement root, ArrayList<String> vars) {
        if(root != null) {
            if(root.getVariable() != null) vars.add(root.getValue());
            findWhichVariable(root.getLeftchild(), vars);
            findWhichVariable(root.getRightchild(), vars);
        }
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

    public Set<PredicateElement> getLeavesParent() {
        findAllLeavesParent(predicateRoot);
        return leavesParent;
    }

    public void setLeavesParent(Set<PredicateElement> leavesParent) {
        this.leavesParent = leavesParent;
    }
}
