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

    // Constructor
    public PredicateElement() {
        this.value = "";
        this.leftchild = null;
        this.rightchild = null;
    }

    // Constructor using variables
    public PredicateElement(String value, PredicateElement parent, PredicateElement leftchild, PredicateElement rightchild) {
        this.value = value;
        this.parent = parent;
        this.leftchild = leftchild;
        this.rightchild = rightchild;
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
}
