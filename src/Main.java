/**
 * @author sam doyle
 * Date: 02/18/2026
 * Description: class with main method used to test invoices
 */

public class Main {
    public static void main() {
        Product apples = new Product("large apples",  6.88);
        Product oranges = new Product("Juicy oranges",  5.90);
        Invoice invoice = new Invoice(new Address("Sams grocery", "Main St.", "Menomonie", "WI", "54751"));
        invoice.addCharge(apples, 5);
        invoice.addCharge(oranges, 5);
        System.out.println(invoice.toString());

    }
}
