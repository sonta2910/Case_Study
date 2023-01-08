package product;

import Login.ManagerAdmin;
import Menu.MenuAdmin;
import crud.ICrud;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ManagerProduct implements ICrud<Product> {
    String path1 = "/Users/HieuHip/IdeaProjects/CaseStudy/src/product.txt";
    String path2 = "/Users/HieuHip/IdeaProjects/CaseStudy/src/cart_product.txt";
    ManagerProductType managerProductType;
    private String inPattern = "^[A-Z a-z 0-9]{3,15}$";
    private String datePattern = "^[0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{4}$";
    public ArrayList<Product> listProduct;
    public ArrayList<Product> listBuyProduct=new ArrayList<>();
    private String numberForm = "^[0-9]%";
    int id;
    MenuAdmin menuAdmin;
    public ManagerProduct(ManagerProductType managerProductType,MenuAdmin menuAdmin) {
        listProduct = new ArrayList<>();
        listProduct = read(path1);
        listBuyProduct = read(path2);
        if(!listProduct.isEmpty()) {
            id = listProduct.get(listProduct.size() - 1).getId();
        } else{id=0;}
        this.managerProductType=managerProductType;
        this.menuAdmin=menuAdmin;
    }

    public boolean regexInPattern(String input, String pattern) {
        return Pattern.compile(pattern).matcher(input).matches();
    }

    public boolean regexDatePattern(String input, String pattern) {
        return Pattern.compile(pattern).matcher(input).matches();
    }


    @Override
    public Product create(Scanner scanner) {
        Product product = null;
        managerProductType = new ManagerProductType();
        String name;
        String brand;
        String date;
        Double price = 0.0;
        boolean check = false;
        ProductType productType;
        id++;
        do {
            System.out.println("Enter name of product:");
            name = scanner.nextLine();
            if (regexInPattern(name, inPattern)) {
                check = true;
            } else {
                System.out.println("~@~@~@~ Product name must be between 3-15 characters ~@~@~@~ \n   ----- Please re-enter ------");
            }
        } while (!check);
        check = false;
        do {
            System.out.println("Please enter product's brand:");
            brand = scanner.nextLine();
            if (regexInPattern(brand, inPattern)) {
                check = true;
            } else {
                System.out.println("~@~@~@~ Product brand name must be between 3-15 characters ~@~@~@~ \n------ Please re-enter -------");
            }
        } while (!check);
        check = false;
        do {
            try {
                System.out.println("Please enter product's price:");
                price = Double.parseDouble(scanner.nextLine());
                check = true;
            } catch (NumberFormatException a) {
                a.getMessage();
                System.out.println("-_-_-_-_- Product price must be number -_-_-_-_\n-_-_-_-_- Please re enter -_-_-_-_-");
            }

        } while (!check);
        check = false;
        do {
            System.out.println("Please enter time import product:");
            date = scanner.nextLine();
            if (regexDatePattern(date, datePattern)) {
                check = true;
            } else {
                System.out.println("~@~@~@~ Date import product must be dd/mm/yyyy ~@~@~@~ \n------- Please re-enter -------");
            }
        } while (!check);
        check = false;
        productType = managerProductType.choiceProductType(scanner);
        do {
            System.out.println("Please choice category of product:  ");
            System.out.println("1.Drugs");
            System.out.println("2.Powder Milk");
            System.out.println("^-^-^-^-^-^-^-^-^-^");
            System.out.println("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                check = true;
                switch (choice) {
                    case 1:
                        System.out.println("Please enter amount of tablets:");
                        int tablets = Integer.parseInt(scanner.nextLine());
                        product = new Drugs(id, name, brand, price, date, productType, tablets);
                        System.out.println("*-*Product had been added*-*\n*-*Please press 4 to show all of your product*-*");
                        return product;
                    case 2:
                        System.out.println("Please enter weight of powder milk:");
                        int weight = Integer.parseInt(scanner.nextLine());
                        product = new PowderMilk(id, name, brand, price, date, productType, weight);

                        System.out.println("~@~@~@~@~@~@~ Product had been added~@~@~@~@~@~@~\n------- Please press 4 to show all your product --------");
                        return product;
                }
            } catch (NumberFormatException a) {
                a.printStackTrace();
            }

        } while (!check);
        return product;

    }

    @Override
    public void add(String path, Scanner scanner) {
        Product addProduct = create(scanner);
        listProduct.add(addProduct);
        write(path, listProduct);
    }

    @Override
    public void edit(Scanner scanner) {

        boolean frag = false;
        Product product=null;
        Drugs drugs;
        try {
            System.out.println("Enter id of product you want to edit:");
            int productId = Integer.parseInt(scanner.nextLine());
            for (Product a : listProduct) {
                if (a.getId() == productId) {
                    product = a;
                    String name;
                    String brand;
                    String date;
                    Double price = 0.0;
                    boolean check = false;
                    ProductType productType1;
                    do {
                        System.out.println("Enter new name of product:");
                        name = scanner.nextLine();
                        if (regexInPattern(name, inPattern)) {
                            check = true;
                        } else {
                            System.out.println("~@~@~ Product name must be between 3-15 characters ~@~@~\n            Please re-enter!");
                        }
                        a.setName(name);
                    } while (!check);
                    check = false;
                    do {
                        System.out.println("Please enter new product's brand:");
                        brand = scanner.nextLine();
                        if (regexInPattern(brand, inPattern)) {
                            check = true;
                        } else {
                            System.out.println("~@~@~ Product brand name must be between 3-15 characters ~@~@~\n            Please re-enter!");
                        }
                        a.setBrand(brand);
                    } while (!check);
                    check = false;
                    do {
                        try {
                            System.out.println("Please enter new product's price:");
                            price = Double.parseDouble(scanner.nextLine());
                            check = true;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        a.setPrice(price);
                    } while (!check);
                    check = false;
                    do {
                        System.out.println("Please enter new time import product:");
                        date = scanner.nextLine();
                        if (regexDatePattern(date, datePattern)) {
                            check = true;
                        } else {
                            System.out.println("Wrong date format!\nPlease re-enter!!!");
                        }
                        a.setDate(date);
                    } while (!check);
                    check = false;
                    System.out.println("Please enter new type of product:");
                    productType1 = managerProductType.choiceProductType(scanner);
                    a.setProductType(productType1);
                }
            }
            write(path1,listProduct);
        } catch (NumberFormatException a) {
            a.printStackTrace();
        }
    }

    @Override
    public void delete(Scanner scanner) {
        Product product1 = null;
        boolean check = false;
        do {
            try {
                System.out.println("Enter id of product you want to delete:");
                int productId = Integer.parseInt(scanner.nextLine());
                for (Product a : listProduct) {
                    if (productId == a.getId()) {
                        product1 = a;
                        check = true;
                        break;
                    }
                }
                if(!check){
                    System.out.println("ID not found");
                    return;
                }
                listProduct.remove(product1);
                System.out.println("Delete successful!!!");
                write(path1, listProduct);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } while (!check);
    }


    public void displayProduct() {
        for (Product a : listProduct) {
            System.out.println(a);
            }
        if (listProduct.isEmpty()) {
            System.out.println("There is no product in shop!!\nPlease create new one and comeback then! ");
        }
        }

    public void displayProductByDrugs() {
        boolean check = false;
        System.out.println("All of product of drugs:");
        for (Product a : listProduct) {
            if (a instanceof Drugs) {
                System.out.println(a);
                check = true;
            }
        }
        if(!check){
            System.out.println("~@~@~ There is no product of drug in your shop ~@~@~");
        }
    }

    public void displayProductByPowderMilk() {
        boolean frag = false;
        System.out.println("All of product of powder milk:");
        for (Product a : listProduct) {
            if (a instanceof PowderMilk) {
                System.out.println(a);
                frag = true;
            }
        }
        if(!frag){
            System.out.println("There is no product of powder milk in your shop");
        }
    }

    public void displayProductByMaxPrice() {
        boolean flag = false;
        System.out.println("All of highest price product:");
        double maxPrice = 0;
        for (Product b : listProduct) {
            if (b.getPrice() > maxPrice) {
                maxPrice = b.getPrice();
                flag = true;
            }
        }
        flag= false;
        for (Product c : listProduct) {
            if (c.getPrice() == maxPrice) {
                System.out.println(c);
                flag = true;
            }
        }
        if(!flag){
            System.out.println("~@~@~ There is no product have highest price in shop ~@~@~");
        }
    }

    public void displayProductByMinPrice() {
        boolean check = false;
        System.out.println("All of lowest price product:");
        double minPrice = 999999999;
        for (Product a : listProduct) {
            if (a.getPrice() < minPrice) {
                minPrice = a.getPrice();
                check = true;
            }
        }
        check= false;
        for (Product b : listProduct) {
            if (b.getPrice() == minPrice) {
                System.out.println(b);
                check = true;
            }
        }
        if(!check){
            System.out.println("~@~@~ There is no product have lowest product in shop ~@~@~");
        }
    }

    public void findProductByPrice(Scanner scanner) {
        boolean check = false;
        System.out.println("Please enter range of price you want to find:");
        System.out.println("Min price is:");
        double minPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Max price is: ");
        double maxPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("All of product in a price range is:");
        for (Product a : listProduct) {
            if (a.getPrice() >= minPrice && a.getPrice() <= maxPrice) {
                System.out.println(a);
                check = true;
            }
        }
        if(!check){
            System.out.println("*-*-*-*There is no product in range*-*-*-*");
        }
    }

    public ArrayList<Product> buyProduct(String path, Scanner scanner) {

            System.out.println("Enter id of product you want to buy:");
            int productId = Integer.parseInt(scanner.nextLine());
            for (Product a : listProduct) {
                if (a.getId() == productId) {
                    listBuyProduct.add(a);
                    write(path, listBuyProduct);
                }
            }
        System.out.println("        ~~~~~~~Thanks!~~~~~~~\n ~@~@~ Your product have been added to your cart ~@~@~");
        return listBuyProduct;
    }

    public void displayCart() {
        System.out.println("*-*-*-* YOUR CART *-*-*-*");
        this.listBuyProduct = this.read(path2);
        for (Product a : listBuyProduct) {
            System.out.println(a);
        }
        if(listBuyProduct.isEmpty()){
            System.out.println("*-*-* There is no product in your cart *-*-*");
        }
    }

    public void productMenu() {
        Scanner scanner = new Scanner(System.in);
        this.listProduct = this.read(path1);
        int choice = -1;
        do {
            System.out.println("What do you want to do with your product?");
            System.out.println("Press 1 to create new product!");
            System.out.println("Press 2 to delete product!");
            System.out.println("Press 3 to edit product!");
            System.out.println("Press 4 to show all product!");
            System.out.println("----------------------------");
            System.out.println("Press 0 to back to admin menu");
            System.out.println("*^*^*^*^*^*^*^*^*^*^*^*^*");
            System.out.println("Enter your choice:");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Please enter information of product!");
                    add(path1, scanner);
                    break;
                case 2:
                    displayProduct();
                    delete(scanner);
                    break;
                case 3:
                    displayProduct();
                    edit(scanner);
                    break;
                case 4:
                    System.out.println("ALL PRODUCT:");
                    displayProduct();
                    break;
            }
            if(choice>4){
                System.out.println(" ------- Can not determined your choice -------");
                System.out.println(" ------- Please choose again -------");
            }
        } while (choice != 0);
    }

    public void write(String path, ArrayList<Product> listProduct) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listProduct);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    public ArrayList<Product> read(String path) {
        ArrayList<Product> listProduct = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(path);
            if (fileInputStream.available() != 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                listProduct = (ArrayList<Product>) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (Exception a) {
            a.printStackTrace();
        }

        return listProduct;
    }
public void deleteProductCart(Scanner scanner){
    boolean check = false;
    displayCart();
    System.out.println("        -------------------");
    Product productCart=null;
    System.out.println("Enter id of product in your cart you want to delete:");
    try{
        int productTypeId=Integer.parseInt(scanner.nextLine());
        for (Product a:listBuyProduct) {
            if(productTypeId==a.getId()){
                productCart=a;
                listBuyProduct.remove(productCart);
                check = true;
                System.out.println("------------- Deleted----------");
                break;
            }

        }
        if(!check){
            System.out.println("~@~@~@~@~ ID not found ~@~@~@~@~");
        }
        write(path2,listBuyProduct);
    }catch (NumberFormatException a){
        a.printStackTrace();
    }

}
}
