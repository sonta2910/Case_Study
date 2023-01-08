package product;

public class Drugs extends Product{
    private int tablets;

    public Drugs(int tablets) {
        this.tablets = tablets;
    }

    public Drugs(int id, String name, String brand, Double price, String date, ProductType productType, int tablets) {
        super(id, name, brand, price,date, productType);
        this.tablets = tablets;
    }

    public Drugs(int id, String name, String brand, Double price, String date, ProductType productType) {
        super(id, name, brand, price, date, productType);
    }

    public Drugs() {
    }

    public int getTablets() {
        return tablets;
    }

    public void setTablets(int tablets) {
        this.tablets = tablets;
    }

    @Override
    public String toString() {
        return "Drugs -" +

                " " + super.toString()+", " +"tablets : " + tablets + '\'';
    }
}
