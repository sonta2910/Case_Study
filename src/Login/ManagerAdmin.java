package Login;

import Menu.MenuAdmin;

import java.util.Scanner;

public class ManagerAdmin {
    MenuAdmin menuAdmin ;

    public ManagerAdmin(MenuAdmin menuAdmin) {
        this.menuAdmin = menuAdmin;
    }

    public void loginAdmin() {
        Scanner scanner = new Scanner(System.in);
        int count = 3;
        int count1 = 3;
        boolean check = false;
        do{
            System.out.println("Your username:");
            String username = scanner.nextLine();
            System.out.println("Your password:");
            String password = scanner.nextLine();
            if (username.equals("admin123@gmail.com")) {
               if(password.equals("sondeptrai2910")){
                   System.out.println("~@~@~@~@~ Welcome ADMIN ~@~@~@~@~");
                   menuAdmin.adminMenu();
                   check = true;
                   break;
               }else {
                   while (!password.equals("sondeptrai2910")) {
                       System.out.println("-_-_-_- Wrong password -_-_-_-\n-_-_-_- Please re enter -_-_-_-");
                       count1--;
                       System.out.println("!!! You have " + count1 + " time to enter !!!");
                       System.out.println("Your password:");
                       password = scanner.nextLine();
                       if (count1 == 1) {
                           System.out.println("    ~@~@~@~ Out of entry ~@~@~@~");
                           System.out.println("           ----------");
                           check = true;
                           break;
                       }
                   }
               }
            }else{
                System.out.println("     Admin account do not exist ~T.T~");
                count--;
                System.out.println("     You have "+count+" time to enter");
                System.out.println("     --------------------------------");
                if(count==0){
                    System.out.println("         ~~~~~Out of entry >.<~~~~~\n     *-*-*You will be taken back to the homepage*-*-*");
                    System.out.println("          -----------------------");
                    check = true;
                }
            }
        }while (!check);
    }
}
