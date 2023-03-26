//package jpa1;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.Scanner;
//
//public class FlatOperations {
//    static EntityManagerFactory emf;
//    static EntityManager em;
//
//    //    static final String[] DISTRICTS = {"Osnovyanskyi", "Saltivskyi", "Kyivskyi", "Kholodnogirskyi", "Novobavarskyi"};
//    public static void addFlat() {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter flat district: ");
//        String district = sc.nextLine();
//        System.out.print("Enter flat address: ");
//        String address = sc.nextLine();
//        System.out.print("Enter flat area: ");
//        int square = Integer.parseInt(sc.nextLine());
//        System.out.print("Enter number of rooms: ");
//        int roomsQty = Integer.parseInt(sc.nextLine());
//        System.out.print("Enter flat price: ");
//        long price = Long.parseLong(sc.nextLine());
//
//        emf = Persistence.createEntityManagerFactory("JPATest");
//        em = emf.createEntityManager();
//
//        em.getTransaction().begin();
//        try {
//            Flat c = new Flat(district, address, square, roomsQty, price);
//            em.persist(c);
//            em.getTransaction().commit();
//
//            System.out.println(c.getId());
//        } catch (Exception ex) {
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//            emf.close();
//        }
//    }
//
//    public static void deleteFlat() {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter flat id: ");
//        String flatId = sc.nextLine();
//        int id = Integer.parseInt(flatId);
//
//        emf = Persistence.createEntityManagerFactory("JPATest");
//        em = emf.createEntityManager();
//
//        Flat c = em.getReference(Flat.class, id);
//        if (c == null) {
//            System.out.println("Flat not found!");
//            return;
//        }
//
//        em.getTransaction().begin();
//        try {
//            em.remove(c);
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            em.getTransaction().rollback();
//        }
//    }
//}
