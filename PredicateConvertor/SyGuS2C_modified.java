package synth.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class SyGuS2C {
    private String code;
    private String cCode = null;
    private long branches = 0; // init value is necessary
    private ArrayList<ParseTree> conditions = new ArrayList<>();
    private HashSet<String> usedGrammars = new HashSet<>();
    private HashMap<String, Integer> opCounter = new HashMap<>();
    private final List<String> unary = Arrays.asList("not", "!");
    private final List<String> binary = Arrays.asList("+", "-", "*", "/", "div", "%", "<", "<=", ">", ">=", "=", "or", "and", "||", "&&", "==");
    private final List<String> ternary = Arrays.asList("ite", "if");

    public SyGuS2C(String code) {
        String endStr = ")) ";
        int end = code.indexOf(endStr);
        int retype = -1, code_s = 0;
        retype = code.indexOf("Int ", end);
        code_s = retype + "Int ".length();
        code = code.substring(code_s, code.length()-1);
        code = code.replaceAll("\\(", "");
        code = code.replaceAll("\\)", " )");
        this.code = code;
    }

    public SyGuS2C() {}

    private String convertCCode() {
        cCode = "";
        ParseTree root = makeParseTree();
        traverse(root);
        if (!cCode.contains("if")) return "return " + cCode + ";";   
        String[] tokens = cCode.split(" ");
        String res = "";
        for (int i = 0; i < tokens.length; i++) {
            String current = tokens[i], lookup = null;
            if (current.length() == 0) continue;
            if (i + 1 < tokens.length) lookup = tokens[i + 1];
            if (lookup == null) {
                res += "return " + current + "; ";
                break;
            }
            if (current.equals("else") && lookup.equals("if")) {
                i = i + 2;
                res += "else if" + tokens[i] + " ";
            }
            else if (current.equals("if")) {
                i++;
                res += "if" + tokens[i] + " ";
            }
            else if (current.equals("else")) {
                i++;
                res += "else return " + tokens[i] + "; ";
            }
            else {
                res += "return " + current + "; ";
            }
        }
        return cCode = res;
    }

    public String getCode() {
        return code;
    }

    public void setSygusCode(String code) {
        this.code = code;
    }

    public long getComplexity() {
        return branches;
    }

    public String getCCode(String output){
        if (cCode == null) cCode = convertCCode();
        while (cCode.contains("return")) {
            cCode = cCode.replaceFirst("return", output + " =");
        }

        int stmtIndex = cCode.indexOf(":");
        while (stmtIndex != -1) {
            branches++;
            stmtIndex = cCode.indexOf(":", stmtIndex + 1);
        }

        return cCode;
    }

    /**
     * <p> 2023-01-11 Yoel Kim
     * <p> this method is for predicate (boolean expression). 
     * @return the Boolean expression where the returns and semicolons are removed
     */
    public String getBooleanCode() {
        if (cCode == null) cCode = convertCCode();
        while (cCode.contains("return ")) {
            cCode = cCode.replaceFirst("return ", "");
        }
        while (cCode.contains(";")) {
            cCode = cCode.replaceFirst(";", "");
        }
        return cCode;
    }

    public ParseTree makeParseTree() {
        String tempCode = code.concat("");
        tempCode = tempCode.replaceAll(" \\)", "");
        String[] tokens = tempCode.split(" ");
        Stack<ParseTree> ParseTreeStack = new Stack<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (unary.contains(token)) {
                ParseTree node = new ParseTree("!");
                node.setFirst(ParseTreeStack.pop());
                ParseTreeStack.push(node);
            }

            else if (binary.contains(token)) {
                usedGrammars.add(token);
                if (opCounter.containsKey(token)) {
                    opCounter.put(token, opCounter.get(token) + 1);
                }
                else opCounter.put(token, 1);

                if (token.equals("or")) token = "||";
                if (token.equals("and")) token = "&&";
                if (token.equals("=")) token = "==";
                if (token.equals("div")) token = "/";
                ParseTree node = new ParseTree(token);
                node.setFirst(ParseTreeStack.pop());
                node.setSecond(ParseTreeStack.pop());
                ParseTreeStack.push(node);

                if (node.getFirst().getOp().equals("if")) {
                    setTernary(node.getFirst());
                }
                if (node.getSecond().getOp().equals("if")) {
                    setTernary(node.getSecond());
                }
                if (node.getFirst().getValue() != null && node.getSecond().getValue() != null) {
                    switch (node.getOp()) {
                        case "+": node.setValue(node.getFirst().getValue() + node.getSecond().getValue()); opCounter.put("+", opCounter.get("+") - 1); break;
                        case "-": node.setValue(node.getFirst().getValue() - node.getSecond().getValue()); opCounter.put("-", opCounter.get("-") - 1); break;
                        case "*": node.setValue(node.getFirst().getValue() * node.getSecond().getValue()); opCounter.put("*", opCounter.get("*") - 1); break;
                        case "/": node.setValue(node.getFirst().getValue() / node.getSecond().getValue()); opCounter.put("/", opCounter.get("/") - 1); break;
                        case "%": node.setValue(node.getFirst().getValue() % node.getSecond().getValue()); opCounter.put("%", opCounter.get("%") - 1); break;
                    }
                    node.setOp(node.getValue().toString());
                    node.setFirst(null); node.setSecond(null);
                }
            }

            // for if-then-else operator
            else if (ternary.contains(token)) {
                ParseTree node = new ParseTree("if");
                node.setFirst(ParseTreeStack.pop());
                conditions.add(node.getFirst());
                node.setSecond(ParseTreeStack.pop());
                node.setThird(ParseTreeStack.pop());         
                ParseTreeStack.push(node);
                branches++;
            }
            // for constant
            else if (isDigit(token)) {
                ParseTree node = new ParseTree(token);
                node.setValue(Integer.parseInt(token));
                ParseTreeStack.push(node);
            }
            // for variable
            else {
                //complexity++;
                ParseTree node = new ParseTree(token);
                ParseTreeStack.push(node);
            }    
        }
        return ParseTreeStack.pop();
    }

    private void traverse(ParseTree node) {
        if (node == null) return;
        String data = node.getOp();
        if (data == null || data.isBlank()) return;

        if (ternary.contains(data)) {
            cCode += data + " ";
            traverse(node.getFirst());
            cCode += " ";
            traverse(node.getSecond());
            cCode += " else ";
            traverse(node.getThird());
            cCode += " ";
        }
        else if (binary.contains(data)) {
            if (node.getFirst() == null || node.getSecond() == null) {
                cCode += data;
            }
            else {
                cCode += "(";
                traverse(node.getFirst());
                cCode += data;
                traverse(node.getSecond());
                cCode += ")";
            } 
        }
        else if (unary.contains(data)){
            if (node.getFirst() == null) cCode += data;
            else {
                cCode += "(" + data;
                traverse(node.getFirst());
                cCode += ")";
            }
            
        }
        else if (data.equals("?")) {
            cCode += "(";
            traverse(node.getFirst());
            cCode += data;
            traverse(node.getSecond());
            cCode += ":";
            traverse(node.getThird());
            cCode += ")";
        }
        else {
            usedGrammars.add(data);
            if (data.charAt(0) != '-') cCode += data;
            else cCode += "(" + data + ")";
        }
        
    }

    private void setTernary(ParseTree node) {
        if (node == null) return;
        if (node.getOp() == "if") node.setOp("?");
        setTernary(node.getFirst());
        setTernary(node.getSecond());
        setTernary(node.getThird());
    }

    private boolean isDigit(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public HashSet<String> getUsedGrammars() {
        for (String op: opCounter.keySet()) {
            if (opCounter.get(op) <= 0) usedGrammars.remove(op);
        }
        return usedGrammars;
    }
}
