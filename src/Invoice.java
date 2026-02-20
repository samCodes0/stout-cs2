import java.util.ArrayList;

/**
 * @author Sam Doyle
 * Date: 02/19/2026
 * Description: An invoice with a billing address and a list of line items (see LineItem class)
 */
public class Invoice {
    private Address billingAddress;
    private ArrayList<LineItem> items;

    public static final String FORMAT_STRING = "%-15s%-10s%-10s%-10s\n"; // describes the format of the invoice to be used with printf with 4 arguments

    public Invoice(Address billingAddress) {
        this.billingAddress = billingAddress;
        items = new ArrayList<LineItem>();
    }

    /**
     * add a specified quantity of a product to the invoice
     * @param product the product to add (see Product class)
     * @param quantity the amount of this product to add to the invoice
     */
    public void addCharge(Product product, int quantity) {
        items.add(new LineItem(quantity, product));
    }

    /**
     * calculates the total by adding up the total price of all the line items in items
     * @return the total due from this invoice
     */
    public double getTotalDue() {
        double total = 0;
        for (LineItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    /**
     * generates a descriptive string for this invoice, containing the billing address information,
     * and a table describing the line items in the invoice. Finishes by printing the total amount due from the invoice
     * @return
     */
    @Override
    public String toString() {
        // TODO: add amount due at the end of the invoice

        String out = String.format("%s\n%s\n%s, %s, %s\n\n", billingAddress.getName(), billingAddress.getStreet(), billingAddress.getCity(), billingAddress.getState(), billingAddress.getZip());
        out += String.format(FORMAT_STRING, "Description", "price", "Qty", "Total");
        for (LineItem item : items) {
            out += item.toString();
        }
        // append the total due to the end of the invoice
        out += String.format("Amount Due: $%.2f", getTotalDue());
        return out;
    }
}
