package brainacad_org.menu;


import brainacad_org.dao.cafeassortmentDAO.CafeAssortmentDao;
import brainacad_org.dao.cafeassortmentDAO.CafeAssortmentImpl;
import brainacad_org.dao.clientsDAO.ClientsDaoImpl;
import brainacad_org.dao.staffDAO.StaffDaoImpl;
import brainacad_org.model.CafeAssortment;

import java.util.Scanner;

import static brainacad_org.menu.MenuShow.*;

public class MenuDeleting {
    private static final String DELETE_DRINK_OR_DESSERT = "Удалить асортемент меню по ID";
    private static final String DELETE_CLIENT = "Удалить даные клиента";
    private static final String DELETE_PERSONAL = "Удалить персонал по типу";
     private static final String INVATION_STRING = "Пожалуйста, введите цифру";
    private static final String SEPARATOR = "-";
    private static final String DOT_SPACE = ".  ";
    private static final String END_LINE = "\n";

    public static void showMenuDeleting() {
        Scanner scanner = new Scanner(System.in);
        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        resultString//.append(ACTION_STRING)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETE_DRINK_OR_DESSERT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETE_CLIENT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETE_PERSONAL)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(INVATION_STRING);

        System.out.println(resultString.toString());
    }


    public static void startDeletingMenu() {
        showMenuDeleting();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            menuItem1Execute();
        }
        if (choice == 2) {
            menuItem2Execute();
        }
        if (choice == 3) {
            menuItem3Execute();
        }

    }
    public static void menuItem1Execute() {//delete
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        int num = 0;

        while (num < 1 || num > 2) {
            System.out.println("Пожалуйста, выберите тип асортемента, для удаления");
            System.out.println("1. Напиток\n2. Дессерт");
            num = scanner.nextInt();
            if (num == 1) {//Напиток
                flag = true;
            } else if (num == 2) {//Дессерт
                flag = false;
            }
            if (num < 1 || num > 2) {
                System.out.println("Неправильное число. Повторите ввод.");
            }
        }
        String str=showCafeAssortmentListByType(flag);
        if (!str.isEmpty()){
            System.out.println(str);
            System.out.println("Пожалуйста, введите идентификатор асортемента для удаления");
            long cafeAssortmentId = scanner.nextLong();

            CafeAssortmentDao cafeAssortmentDao = new CafeAssortmentImpl();
            cafeAssortmentDao.delete(cafeAssortmentId);
        }
        else System.out.println("Нет асортемента для удаления");
    }
    public static void menuItem2Execute() {//delete
        Scanner scanner = new Scanner(System.in);
        String str=showClientsList();
        if (!str.isEmpty()){
            System.out.println(str);
            System.out.println("Пожалуйста, введите идентификатор клиента для удаления");
            long clientId = scanner.nextLong();

            ClientsDaoImpl clientDao = new ClientsDaoImpl();
            clientDao.delete(clientId);
        }
        else System.out.println("Нет клиентов для удаления");

    }
    public static void menuItem3Execute() {//delete
        Scanner scanner = new Scanner(System.in);
        int num = 0;

        while (num < 1 || num > 3) {
            System.out.println("Пожалуйста, выберите позицию персонала, для удаления");
            System.out.println("1. Бариста\n2. Официант\n3. Кондитер");
            num = scanner.nextInt();
            if (num < 1 || num > 3) {
                System.out.println("Неправильное число. Повторите ввод.");
            }
        }

        String str=showStaffListByPosition(num);
        if (!str.isEmpty()){
            System.out.println(str);
            System.out.println("Пожалуйста, введите идентификатор персонала для удаления");
            long staffId = scanner.nextLong();
            StaffDaoImpl staffDao = new StaffDaoImpl();
            staffDao.delete(staffId);
        }
        else System.out.println("Нет персонала для удаления");
    }
    private MenuDeleting() {
    }

}
