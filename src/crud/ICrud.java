package crud;

import java.util.Scanner;

public interface ICrud <E> {
    E create(Scanner scanner);
    void add(String path,Scanner scanner);
    void edit(Scanner scanner);
    void delete(Scanner scanner);
}
