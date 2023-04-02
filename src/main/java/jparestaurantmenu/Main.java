package jparestaurantmenu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

import static jparestaurantmenu.MenuOperations.*;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("\n --Make your choice--");
                    System.out.println("1: add dish");
                    System.out.println("2: view all menu");
                    System.out.println("3: select dishes by price");
                    System.out.println("4: view all discounted dishes");
                    System.out.println("5: choose set of dishes up to 1 kg");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1" -> addMenuDish(sc);
                        case "2" -> viewMenu();
                        case "3" -> dishesByPrice(sc);
                        case "4" -> discountedDishes(sc);
                        case "5" -> setUptoOneKG(sc);
                        default -> {
                            return;
                        }
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
