/**
 * 2023-02-24(Fri) SoheeJung
 * Filename : PredicateElement.java
 */
package SpecGenerator;

public class PredicateElement {
    // Original attribute
    private String value;
    // Element of parse tree
    private PredicateElement parent;
    private PredicateElement leftchild;
    private PredicateElement rightchild;
    // 2023-04-04(Tue) SoheeJung
    // if this node is visited, then check this value.
    // if this node is variable type, then setting variable.
    private boolean check;
    private Variable variable;

    // Constructor
    public PredicateElement() {
        this.value = "";
        this.parent = null;
        this.leftchild = null;
        this.rightchild = null;
        this.check = false;
        this.variable = null;
    }

    // Constructor using variables
    public PredicateElement(String value, PredicateElement parent, PredicateElement leftchild, PredicateElement rightchild, boolean check, Variable variable) {
        this.value = value;
        this.parent = parent;
        this.leftchild = leftchild;
        this.rightchild = rightchild;
        this.check = check;
        this.variable = variable;
    }
    
    // Getter & Setter
    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public PredicateElement getParent() {
        return parent;
    }
    public void setParent(PredicateElement parent) {
        this.parent = parent;
    }
    public PredicateElement getLeftchild() {
        return leftchild;
    }
    public void setLeftchild(PredicateElement leftchild) {
        this.leftchild = leftchild;
    }
    public PredicateElement getRightchild() {
        return rightchild;
    }
    public void setRightchild(PredicateElement rightchild) {
        this.rightchild = rightchild;
    }
    public boolean getCheck() {
        return check;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
    public Variable getVariable() {
        return variable;
    }
    public void setVariable(Variable variable) {
        this.variable = variable;
    }
}
