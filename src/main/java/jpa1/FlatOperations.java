package jpa1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class FlatOperations {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void addFlat(Scanner sc) {
        System.out.print("Enter flat district: ");
        String district = sc.nextLine();
        System.out.print("Enter flat address: ");
        String address = sc.nextLine();
        System.out.print("Enter flat area: ");
        int square = Integer.parseInt(sc.nextLine());
        System.out.print("Enter number of rooms: ");
        int roomsQty = Integer.parseInt(sc.nextLine());
        System.out.print("Enter flat price: ");
        long price = Long.parseLong(sc.nextLine());

        em.getTransaction().begin();
        try {
            Flat c = new Flat(district, address, square, roomsQty, price);
            em.persist(c);
            em.getTransaction().commit();

            System.out.println(c.getId());
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public static void deleteFlat(Scanner sc) {
        System.out.print("Enter flat id: ");
        String sId = sc.nextLine();
        long id = Long.parseLong(sId);

        Flat c = em.getReference(Flat.class, id);
        if (c == null) {
            System.out.println("Flat not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public static void viewFlats() {
        emf = Persistence.createEntityManagerFactory("JPATest");
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT f FROM Flat f", Flat.class);
        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat c : list)
            System.out.println(c);
    }

    public static void viewSelectedFlats() {
        System.out.println("Select one flat parameter:");
        System.out.println("1: by price");
        System.out.println("2: by square");
        System.out.println("3: by rooms quantity");
        System.out.print("-> ");

        Scanner sc = new Scanner(System.in);
        final String parameter = sc.nextLine();
        switch (parameter) {
            case "1":
                byPrice(sc);
                break;
            case "2":
                bySquare(sc);
                break;
            case "3":
                byRoomQty(sc);
                break;
        }
    }

    private static void byPrice(Scanner sc) {
        System.out.println("Enter min price:");
        final long minPrice = sc.nextLong();
        System.out.println("Enter max price:");
        final long maxPrice = sc.nextLong();

        Query query = em.createQuery("from Flat f where f.price>=:minPrice AND f.price<=:maxPrice")
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice);
        List<Flat> list = (List<Flat>) query.getResultList();
        if (list.isEmpty()) {
            System.out.println("No flats found!\n");
        } else {
            System.out.printf("Founded flats with price between " + minPrice + " and " + maxPrice + ":\n");
            for (Flat flats : list)
                System.out.println(flats);
        }
    }

    private static void bySquare(Scanner sc) {
        System.out.println("Enter min square:");
        int minSquare = Integer.parseInt(sc.nextLine());
        System.out.println("Enter max square:");
        int maxSquare = Integer.parseInt(sc.nextLine());

        Query query = em.createQuery("from Flat f where f.square>=:minSquare AND f.square<=:maxSquare")
                .setParameter("minSquare", minSquare)
                .setParameter("maxSquare", maxSquare);
        List<Flat> list = (List<Flat>) query.getResultList();
        if (list.isEmpty()) {
            System.out.println("No flats found!\n");
        } else {
            System.out.printf("Founded flats with square between " + minSquare + " and " + maxSquare + ":\n");
            for (Flat flats : list)
                System.out.println(flats);
        }
    }

    private static void byRoomQty(Scanner sc) {
        System.out.println("Enter min room quantity:");
        int minRoomQty = Integer.parseInt(sc.nextLine());
        System.out.println("Enter max room quantity:");
        int maxRoomQty = Integer.parseInt(sc.nextLine());

        Query query = em.createQuery("from Flat f where f.room_qty>=:minRoomQty AND f.room_qty<=:maxRoomQty")
                .setParameter("minRoomQty", minRoomQty)
                .setParameter("maxRoomQty", maxRoomQty);
        List<Flat> list = (List<Flat>) query.getResultList();
        if (list.isEmpty()) {
            System.out.println("No flats found!\n");
        } else {
            System.out.printf("Founded flats with rooms quantity from " + minRoomQty + " and " + maxRoomQty + ":\n");
            for (Flat flats : list)
                System.out.println(flats);
        }
    }
}
