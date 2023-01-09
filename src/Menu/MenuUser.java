package Menu;

import product.Drugs;
import product.ManagerProduct;
import product.Product;

import java.io.IOException;
import java.util.Scanner;

public class MenuUser {
    private ManagerProduct managerProduct;
    public MenuUser(ManagerProduct managerProduct) {
        this.managerProduct=managerProduct;
    }

    public void userMenu(){

        Scanner scanner=new Scanner(System.in);
        int choice=-1;
        do{
            System.out.println("*-*-*-*-*-*-* M-E-N-U *-*-*-*-*-*-*");
            System.out.println("Press 1 to show all of product in shop!!!");
            System.out.println("Press 2 top show all product of drugs:");
            System.out.println("Press 3 to show all product of powder milk:");
            System.out.println("Press 4 to show all lowest price product:");
            System.out.println("Press 5 to show all highest price product:");
            System.out.println("Press 6 to show all product in price range: :");
            System.out.println("Press 7 to buy: ");
            System.out.println("Press 8 to show your cart:");
            System.out.println("Press 9 to delete product in your cart");
            System.out.println("-#-#-#-#-#-#-#-#-#-#-#-#-#-#-");
            System.out.println("Enter your choice:");
            try{
                choice=Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        managerProduct.displayProduct();
                        break;
                    case 2:
                        managerProduct.displayProductByDrugs();
                        break;
                    case 3:
                        managerProduct.displayProductByPowderMilk();
                        break;
                    case 4:
                        managerProduct.displayProductByMinPrice();
                        break;
                    case 5:
                        managerProduct.displayProductByMaxPrice();
                        break;
                    case 6:
                        managerProduct.findProductByPrice(scanner);
                        break;
                    case 7:
                        String path="cart_product.txt";
                        managerProduct.displayProduct();
                        managerProduct.buyProduct(path,scanner);
                        break;
                    case 8:
                        managerProduct.displayCart();
                        break;
                    case 9:
                        managerProduct.deleteProductCart(scanner);
                        break;
                }
            }catch (NumberFormatException a){
                a.printStackTrace();
            }
        }while (choice!=0);
    }
}
