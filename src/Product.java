/**
 * @author Sam Doyle
 * Date: 02/19/2026
 * Description: represents a product with a description and price
 */

public class Product {
    private String description;  // brief string describing the product, or just the name of the product
    private double price;

    public Product(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
