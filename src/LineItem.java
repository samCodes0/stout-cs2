public class LineItem {
    private int quantity;
    private Product product;

    public LineItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public double getTotalPrice() {
        return this.product.getPrice()*this.quantity;
    }

    @Override
    public String toString() {
        return String.format(Invoice.FORMAT_STRING, this.product.getDescription(), this.product.getPrice(), this.quantity, getTotalPrice());
    }
}
