package Login;

import Menu.MenuUser;
import crud.ICrud;
import jdk.jfr.internal.tool.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ManagerUser implements ICrud<User> {
    String path = "/Users/HieuHip/IdeaProjects/CaseStudy/src/User.txt";
    private String patternGmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private String patternPhoneNumber = "^[0][0-9]{9}$";
    private String patternAddress = "^[A-Z a-z 0-9]+$";
    private String patternPassword = "^[A-Z][a-zA-Z0-9]{7,10}$";
    int id;
    private ArrayList<User> listUser;
    MenuUser menuUser;

    public ManagerUser(MenuUser menuUser) {
        listUser = new ArrayList<>();
        listUser.addAll(read(path));
        if(!listUser.isEmpty()) {
            id = listUser.get(listUser.size() - 1).getId();
        } else{id=0;}
        this.menuUser = menuUser;
    }

    public ArrayList<User> getListUser() {
        return listUser;
    }


    public String checkGender(Scanner scanner) {
        int choice = -1;
        System.out.println("1.Male");
        System.out.println("2.Female");
        System.out.println("3.Others");
        System.out.println("Enter your choice:");
        choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                return "Male";
            case 2:
                return "Female";
            default:
                return "Others";
        }

    }

    public boolean regexGmail(String input, String pattern) {
        return Pattern.compile(pattern).matcher(input).matches();
    }

    public boolean regexPhoneNumber(String input, String pattern) {
        return Pattern.compile(pattern).matcher(input).matches();
    }

    public boolean regexAddress(String input, String pattern) {
        return Pattern.compile(pattern).matcher(input).matches();
    }

    public boolean regexPassword(String input, String pattern) {
        return Pattern.compile(pattern).matcher(input).matches();
    }


    @Override
    public User create(Scanner scanner) {
        String address;
        String password;
        String phoneNumber;
        String gmail;
        User user = null;
        boolean check = false;
        id++;
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        System.out.println("Please enter your age:");
        int age = Integer.parseInt(scanner.nextLine());
        do {
            System.out.println("Please enter your address:");
            address = scanner.nextLine();
            if (regexAddress(address, patternAddress)) {
                check = true;
            } else {
                System.out.println("Wrong address format!\nPlease re-enter!");
            }
        } while (!check);
        check = false;
        System.out.println("Please enter your gender");
        String gender = checkGender(scanner);
        do {
            System.out.println("Please enter your phone number:");
            phoneNumber = scanner.nextLine();
            if (regexPhoneNumber(phoneNumber, patternPhoneNumber)) {
                check = true;
            } else {
                System.out.println("Wrong phone format!\nPlease re-enter!");
            }
        } while (!check);
        check = false;
        do {
            System.out.println("Please enter your gmail:");
            gmail = scanner.nextLine();
            boolean frag = false;
            for (User a : listUser) {
                if (a.getGmail().equals(gmail)) {
                    System.out.println("Gmail already exist!");
                    frag = true;
                    break;
                }
            }
            if (!frag) {
                if (regexGmail(gmail, patternGmail)) {
                    check = true;
                } else {
                    System.out.println("Wrong gmail format!\nPlease re-enter!");
                }
            }
        }
        while (!check);
        check = false;
        do {
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
            if (regexPassword(password, patternPassword)) {
                check = true;
            } else {
                System.out.println("Wrong password format!\nPlease re-enter!");
            }
        } while (!check);
        user = new User(id++, name, age, address, gender, phoneNumber, gmail, password);
        return user;
    }

    public void loginUser(Scanner scanner) {
        int choice = -1;
        do {
            System.out.println("Press 0 to exit!");
            System.out.println("Press 1 to login:");
            System.out.println("Press 2 to register:");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    System.out.println("Bye T-T");
                    System.exit(0);
                    break;
                case 1:
                    checkLogin(scanner);
                    break;
                case 2:
                    add(path, scanner);
                    break;
            }if(choice>2){
                System.out.println(" -------- Can not determined your choice -------");
                System.out.println(" -------- Please choose again -------");
            }

        } while (choice != 0);
    }

    public void checkLogin(Scanner scanner) {
        String path = "/Users/HieuHip/IdeaProjects/CaseStudy/src/User.txt";
        int count = 3;
        int count1 = 3;
        int count2 = 3;
        boolean check = false;
        do {
            System.out.println("Your gmail:");
            String username = scanner.nextLine();
            System.out.println("Your password");
            String password = scanner.nextLine();


            if (username.equals("") && password.equals("")) {
                System.out.println("Wrong user name or password");
                break;
            }

            if (listUser.isEmpty()) {
                System.out.println("     ---- User is not exist -----");
                System.out.println("       -------- Re-enter ------");
                count2--;
                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~.~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("      -------- ATTENTION --------");
                System.out.println(" ~@~@~ You have "+count2+" time to enter ~@~@~");
                if(count2==0){
                    System.out.println("      ------- Out of entry --------");
                    System.out.println("      ---- Please create new user -----");
                    check = true;
                    break;
                }
            } else {
                for (User a : listUser) {
                    if (a.getGmail().equals(username)) {
                        if ((a.getPassword()).equals(password)) {
                            System.out.println("Welcome  " + a.getName() + "!" + " Glad to serve you!");
                            menuUser.userMenu();
                            check = true;
                            break;
                        } else {
                            while (!(a.getPassword()).equals(password)) {
                                System.out.println(       "*-*-*Wrong password*-*-*\n      *-*-*Please re-enter*-*-*");
                                count1--;
                                System.out.println("     ~~~~~~~~~~~~~~~~~~~~~.~~~~~~~~~~~~~~~~~~~~~");
                                System.out.println("      -------- ATTENTION --------");
                                System.out.println("You have " + count1 + " times to enter");
                                password = scanner.nextLine();
                                if (count1 == 1) {
                                    System.out.println("~~~~~~~~~~~Out entry~~~~~~~~");
                                    check = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!check) {
                    System.out.println("*-*-*User is not exist*-*-*");
                    System.out.println("*-*-* Please re-enter *-*-*");
                    count--;
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~.~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("      -------- ATTENTION --------");
                    System.out.println("~~~~You have " + count + " times to enter!~~~~~~");
                    if (count == 1) {
                        System.out.println("*-*-*Out of entry*-*-*");
                        check = true;
                    }
                }
            }
        }while (!check) ;
    }


    @Override
    public void add(String path, Scanner scanner) {
        User user = create(scanner);
        listUser.add(user);
        write(path,listUser);
        System.out.println("~@~@~@~@~@~ Create account successful ~@~@~@~@~@~");
        System.out.println("*-* Please login *-*");
        loginUser(scanner);

    }

    @Override
    public void edit(Scanner scanner) {
    }

    @Override
    public void delete(Scanner scanner) {
    }

    public ArrayList<User> read(String path) {
        ArrayList<User> listUser = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(path);
            if (fileInputStream.available() != 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                listUser = (ArrayList<User>) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return listUser;
    }

    public void write(String path, ArrayList<User> listUser) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listUser);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException a) {
            a.printStackTrace();
        }
    }

}

// map<String, String> a = new Hashmap<>();
// pubic void login (){
// String