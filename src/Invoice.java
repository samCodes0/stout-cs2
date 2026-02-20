import java.util.ArrayList;

public class Invoice {
    private Address billingAddress;
    private ArrayList<LineItem> items;


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
        String out = billingAddress.getName() + "\n";
        out += billingAddress.getStreet() + "\n";
        out += billingAddress.getCity() + ", " + billingAddress.getState() + " " + billingAddress.getZip() + "\n\n";
        out += "Description\t\tPrice\tQty\tTotal\n";
        for (LineItem item : items) {
            out += item.toString() + "\n";
        }
        return out;
    }
}
