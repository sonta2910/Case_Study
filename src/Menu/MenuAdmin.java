package Menu;

import Login.ManagerUser;
import product.ManagerProduct;
import product.ManagerProductType;

import java.util.Scanner;

public class MenuAdmin {
    ManagerProductType managerProductType;

    public ManagerProduct getManagerProduct() {
        return managerProduct;
    }

    ManagerProduct managerProduct;


    public MenuAdmin(ManagerProductType managerProductType) {
        this.managerProductType = managerProductType;
        this.managerProduct=new ManagerProduct(managerProductType,this);
    }

    public void adminMenu(){
        Scanner scanner=new Scanner(System.in);
        int choice=-1;
        do {
            System.out.println("**********M-E-N-U A-D-M-I-N**********");
            System.out.println("Press 1 to management product!!!");
            System.out.println("Press 2 to management product type!!!");
            System.out.println("~@~@~@~@~@~@~@~@~@~@~@~@~@~@~@~");
            System.out.println("Enter your choice:");
            choice=Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    managerProduct.productMenu();
                    break;
                case 2:
                    managerProductType.productTypeMenu();
                    break;
            }
            if(choice>2){
                System.out.println(" -------- Can not determined your choice -------");
                System.out.println(" -------- Please choose again -------");
            }
        }while(choice!=0);
    }


}
