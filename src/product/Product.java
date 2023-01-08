package product;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String brand;
    private Double price;
    private String date;
    private ProductType productType;

    public Product() {
    }

    public Product(int id, String name, String brand, Double price, String date, ProductType productType) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.date=date;
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public static final long serialVersionUID = 1L;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return
                "id: " + id +
                ", name: " + name  +
                ", brand: " + brand  +
                ", price: " + price +
                ", date: " + date  +
                ", product type: " + productType ;
    }
}
