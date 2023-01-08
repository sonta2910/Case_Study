package product;

public class PowderMilk extends Product{
    private int weight;

    public PowderMilk(int weight) {
        this.weight = weight;
    }

    public PowderMilk(int id, String name, String brand, Double price,String date, ProductType productType, int weight) {
        super(id, name, brand, price,date, productType);
        this.weight = weight;
    }

    public PowderMilk() {
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public PowderMilk(int id, String name, String brand, Double price, String date, ProductType productType) {
        super(id, name, brand, price, date, productType);
    }

    @Override
    public String toString() {
        return "PowderMilk -" +
                " " + super.toString()+", "+"weight=" + weight +"g";
    }
}
