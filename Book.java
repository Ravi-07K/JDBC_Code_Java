
package Ravi;
public class Book {
    private String bname;
    private double oldp;
    private double newp;

    public Book(String bname, double oldp, double newp) {
        this.bname = bname;
        this.oldp = oldp;
        this.newp = newp;
    }

    @Override
    public String toString() {
        return "Book{" + "bname=" + bname + ", oldp=" + oldp + ", newp=" + newp + '}';
    }
    
}
