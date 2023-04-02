package jparestaurantmenu;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

public class MenuOperations {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void startEM() {
        emf = Persistence.createEntityManagerFactory("JPATest");
        em = emf.createEntityManager();
    }

    public static void addMenuDish(Scanner sc) {
        System.out.print("Enter the new dish name in the menu: ");
        String name = sc.nextLine();
        System.out.print("Enter the new dish price in the menu: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Enter the new dish weight in the menu:  ");
        double weight = Double.parseDouble(sc.nextLine());
        System.out.print("Enter 'yes' or 'no', if there is a discount on a new dish: ");
        String isDiscount = sc.nextLine().toLowerCase();
        String discStr;
        if (isDiscount.equals("yes")) discStr = "true";
        else discStr = "false";
        boolean discount = Boolean.parseBoolean(discStr);

        startEM();
        em.getTransaction().begin();
        try {
            MenuDish c = new MenuDish(name, price, weight, discount);
            em.persist(c);
            em.getTransaction().commit();
            System.out.println("The new dish with id " + c.getId() + " was added");
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
//        finally {
//            em.close();
//            emf.close();
//        }
    }

    public static void viewMenu() {
        startEM();
        Query query = em.createQuery("SELECT md FROM MenuDish md", MenuDish.class);
        List<MenuDish> list = query.getResultList();

        for (MenuDish c : list)
            System.out.println(c);

//        em.close();
//        emf.close();
    }


    public static List<MenuDish> dishesByPrice(Scanner sc) {
        System.out.println("Enter min price:");
        final double minPrice = Double.parseDouble(sc.nextLine());
        System.out.println("Enter max price:");
        final double maxPrice = Double.parseDouble(sc.nextLine());

        startEM();
        Query query = em.createQuery("from MenuDish md where md.price>=:minPrice AND md.price<=:maxPrice")
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice);
        List<MenuDish> list = query.getResultList();
        if (list.isEmpty()) {
            System.out.println("No dishes found!\n");
        } else {
            System.out.printf("Founded dishes with price between " + minPrice + " and " + maxPrice + ":\n");
            for (MenuDish dishes : list) System.out.println(dishes.getName() + " - " + dishes.getPrice());
        }
        return list;
    }

    public static void discountedDishes(Scanner sc) {
        startEM();
        Query query = em.createQuery("SELECT md FROM MenuDish md WHERE md.discount = true", MenuDish.class);
        List<MenuDish> list = query.getResultList();

        for (MenuDish c : list)
            System.out.println(c);
    }

    public static void setUptoOneKG(Scanner sc) {
        final double maxWeight = 1000;

        startEM();
        Query query = em.createQuery("SELECT md FROM MenuDish md", MenuDish.class);
        List<MenuDish> list = query.getResultList();
        Random rnd = new Random();
        List<MenuDish> maxWeightList = new LinkedList<>();

        double weight;
        double totalWeight = 0;

        System.out.println("Your proposal dishes set:");
        while (totalWeight <= maxWeight) {
            MenuDish randomDish = list.get(rnd.nextInt(list.size()));
            weight = randomDish.getWeight();
            totalWeight += randomDish.getWeight();
            if (totalWeight >= maxWeight) {
                totalWeight -= randomDish.getWeight();
                break;
            }
            maxWeightList.add(randomDish);
            System.out.println(randomDish.getName() + " - " + weight + " g");
        }
        System.out.println("The total weight of your set of dishes is " + totalWeight + " g");
    }
}

