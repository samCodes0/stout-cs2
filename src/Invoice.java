import java.util.ArrayList;

public class Invoice {
    private Address billingAddress;
    private ArrayList<LineItem> items;

    public static final String FORMAT_STRING = "%-15s%-10s%-10s%-10s\n"; // describes the format of the invoice to be used with printf with 4 arguments

    public Invoice(Address billingAddress) {
        this.billingAddress = billingAddress;
        items = new ArrayList<LineItem>();
    }

    public void addCharge(Product product, int quantity) {
        items.add(new LineItem(quantity, product));
    }

    public double getTotalDue() {
        double total = 0;
        for (LineItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

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
