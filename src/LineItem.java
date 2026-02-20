/**
 * @author Sam Doyle
 * Date: 02/19/2026
 * Description: represents one line item inside an invoice.
 */

public class LineItem {
    private int quantity;  // q
    private Product product;

    public LineItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    /**
     * calculates the total for this line item by multiplying the price of the product by the quantity requested
     * @return total price for this line item
     */
    public double getTotalPrice() {
        return this.product.getPrice()*this.quantity;
    }

    /**
     * Generates a descriptive string for the line item. Makes use of Invoice.FORMAT_STRING to format
     * @return a descriptive string for the line item with the products description, price, and this line items quantity and total price
     */
    @Override
    public String toString() {
        return String.format(Invoice.FORMAT_STRING, this.product.getDescription(), this.product.getPrice(), this.quantity, getTotalPrice());
    }
}
