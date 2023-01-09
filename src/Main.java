import Login.Admin;
import Login.ManagerAdmin;
import Login.ManagerUser;
import Login.User;
import Menu.MenuAdmin;
import Menu.MenuUser;
import product.ManagerProduct;
import product.ManagerProductType;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        ManagerProductType managerProductType=new ManagerProductType();
        MenuAdmin menuAdmin=new MenuAdmin(managerProductType);
        ManagerAdmin managerAdmin = new ManagerAdmin(menuAdmin);
        MenuUser menuUser= new MenuUser(menuAdmin.getManagerProduct());
        ManagerUser managerUser = new ManagerUser(menuUser);
        int choice=-1;

    do {
        try{
            System.out.println("*-*-*-*Welcome to SonDepTrai MegaMart*-*-*-*");
            System.out.println("How do you want to log in?");
            System.out.println("1.Admin  ");
            System.out.println("2.Guest  ");
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            System.out.println("Enter your choice:");
            choice= Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    managerAdmin.loginAdmin();
                    break;
                case 2:
                    managerUser.loginUser(scanner);
                    break;
            }
            if(choice>2){
                System.out.println(" -------- Can not determined your choice -------");
                System.out.println(" -------- Please choose again -------");
            }
        }catch ( NumberFormatException e){
            e.getMessage();
            System.out.println("-------- Can not determined your choice -------");
        }

    }while (choice!=0);
}
}

