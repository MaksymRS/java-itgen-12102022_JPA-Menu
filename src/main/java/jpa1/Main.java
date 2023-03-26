package jpa1;

import javax.persistence.*;
import java.util.Scanner;

import static jpa1.FlatOperations.*;


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
                    System.out.println("1: add flat");
                    System.out.println("2: delete flat");
                    System.out.println("3: view all flats");
                    System.out.println("4: view flats by parameters");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1" -> addFlat(sc);
                        case "2" -> deleteFlat(sc);
                        case "3" -> viewFlats();
                        case "4" -> viewSelectedFlats();
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