package product;

import Menu.MenuAdmin;
import crud.ICrud;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ManagerProductType implements ICrud<ProductType> {
    public MenuAdmin menuAdmin;
    String path = "product_type.txt";
    int id;
    public ManagerProductType(MenuAdmin menuAdmin) {
        this.menuAdmin = menuAdmin;
    }

    public ArrayList<ProductType> listProductType;
    private String numberForm = "^([0-9]+)";

    public ManagerProductType() {
        listProductType = new ArrayList<>();
        listProductType = read(path);
        if(!listProductType.isEmpty()) {
            id = listProductType.get(listProductType.size() - 1).getId();
        } else{id=0;}
    }

    public boolean regexNumberForm(String input, String pattern) {
        return Pattern.compile(pattern).matcher(input).matches();
    }

    public void displayProductType() {
        System.out.println("----- All of your product type ------");
        for (ProductType a : listProductType) {
            System.out.println(a);
        }
        if (listProductType.isEmpty()) {
            System.out.println("~@~@~@~@~@~ There is no product type in your shop ~@~@~@~@~@~");
        }
    }

    public ProductType choiceProductType(Scanner scanner) {
        boolean frag = false;
        ProductType choiceProductTypeName=null;
        if (!(listProductType.isEmpty())) {
            System.out.println("~@~@~ Enter name of product type ~@~@~");
            String name = scanner.nextLine() ;
            for (ProductType a : listProductType) {
                if (a.getName().equals(name)) {
                    choiceProductTypeName = a;
                    return choiceProductTypeName;
                }
            }
            if(!frag){
                System.out.println("~@~@~@~ There is no product type you want in your shop ~@~@~@~");
                displayProductType();
                System.out.println("    ------ Please create new one -----");
                choiceProductTypeName = create(scanner);
                listProductType.add(choiceProductTypeName);
                write(path, listProductType);
                return choiceProductTypeName;
            }
        }else {
            System.out.println("~@~@~@~@~ There is no product type ~@~@~@~@~");
            System.out.println("~@~@~@~@~ Please create new one ~@~@~@~@~");
            choiceProductTypeName = create(scanner);
            listProductType.add(choiceProductTypeName);
            write(path, listProductType);
            return choiceProductTypeName;
        }
        return choiceProductTypeName;
    }
    public void write(String path, ArrayList<ProductType> listProductType) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listProductType);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    public ArrayList<ProductType> read(String path) {
        ArrayList<ProductType> listProductType = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(path);
            if (fileInputStream.available() != 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                listProductType = (ArrayList<ProductType>) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return listProductType;
    }

    @Override
    public ProductType create(Scanner scanner) {
        System.out.println("        --------------");
        id++;
        System.out.println("Enter name of product type: ");
        String name = scanner.nextLine();
        return new ProductType(id, name);
    }



    @Override
    public void add(String path,Scanner scanner) {
        ProductType addProductType = create(scanner);
        listProductType.add(addProductType);
        write(path, listProductType);
        System.out.println("~@~@~@~ Create product type successful ~@~@~@~");
    }

    @Override
    public void edit(Scanner scanner) {
        displayProductType();
        System.out.println("         --------------");
        boolean check = false;
        String productTypeId;
        do{
            System.out.println("Enter id of product type you want to edit: ");
            productTypeId =scanner.nextLine();
            if(regexNumberForm(productTypeId,numberForm)){
                for (ProductType a:listProductType) {
                    if(a.getId()==Integer.parseInt(productTypeId)){
                        System.out.println("Enter new name of product type:");
                        String name = scanner.nextLine();
                        a.setName(name);
                        System.out.println("    -_-_-_-_- Edit successful -_-_-_-_-");
                        check = true;
                    }
                }
                if(!check){
                    System.out.println("~@~@~@~@~ Do not have any product you want to delete ~@~@~@~@~");
                }
                write(path,listProductType);
            }else{
                System.out.println("      *-*SOS*-*Wrong id format*-*SOS*-*\n   -_-_-_-_- Please re-enter! -_-_-_-_-_-");
            }
        }while (!check);


        }


    @Override
    public void delete(Scanner scanner) {
        boolean check = false;
        displayProductType();
        System.out.println("        -------------------");
        ProductType productType=null;
        System.out.println("Enter id of product type you want to delete:");
        try{
            int productTypeId=Integer.parseInt(scanner.nextLine());
            for (ProductType a:listProductType) {
                if(productTypeId==a.getId()){
                    productType=a;
                    listProductType.remove(productType);
                    check = true;
                    System.out.println("------------- Deleted----------");
                    break;
                }

            }
            if(!check){
                System.out.println("~@~@~@~@~ ID not found ~@~@~@~@~");
            }
            write(path,listProductType);
        }catch (NumberFormatException a){
            a.printStackTrace();
        }
    }
    public void productTypeMenu(){
        Scanner scanner=new Scanner(System.in);
        ManagerProductType managerProductType=new ManagerProductType();
        managerProductType.listProductType=managerProductType.read(path);
        int choice=-1;
        do{
            System.out.println("What do you want to do with your product type?");
            System.out.println("Press 1 to create new product type:");
            System.out.println("Press 2 to delete product type:");
            System.out.println("Press 3 to edit product type:");
            System.out.println("Press 4 to show all product type:");
            System.out.println("--------------------------------");
            System.out.println("Press 0 to back to admin menu");
            choice=Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Enter information of product type");
                    add(path,scanner);
                    System.out.println("*-*-* Create successful *-*-*");
                    break;
                case 2:
                    delete(scanner);
                    break;
                case 3:
                    edit(scanner);
                    break;
                case 4:
                    System.out.println("ALL PRODUCT TYPE:");
                    displayProductType();
                    break;
            }
            if(choice>4){
                System.out.println(" -------- Can not determined your choice -------");
                System.out.println(" -------- Please choose again -------");
            }
        }while (choice!=0);
    }
}
