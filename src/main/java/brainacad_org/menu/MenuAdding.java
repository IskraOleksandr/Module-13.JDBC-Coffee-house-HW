package brainacad_org.menu;


import brainacad_org.dao.cafeassortmentDAO.CafeAssortmentDao;
import brainacad_org.dao.cafeassortmentDAO.CafeAssortmentImpl;
import brainacad_org.dao.clientsDAO.ClientsDaoImpl;
import brainacad_org.dao.staffDAO.StaffDaoImpl;
import brainacad_org.model.CafeAssortment;
import brainacad_org.model.Clients;
import brainacad_org.model.Staff;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static brainacad_org.menu.MenuPublisher.showMenu;

public class MenuAdding {
    private static final String ADD_DRINK_OR_DESSERT = "Добавить асортемент меню(напиток или десерт)";
    private static final String ADD_CLIENT = "Добавить клиента";
    private static final String ADD_PERSONAL = "Добавить персонал";
    private static final String INVATION_STRING = "Пожалуйста, введите цифру";
    private static final String SEPARATOR = "-";
    private static final String DOT_SPACE = ".  ";
    private static final String END_LINE = "\n";

    public static void showMenuAdding() {
        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        resultString.append("Меню добавления")
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_DRINK_OR_DESSERT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_CLIENT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_PERSONAL)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(INVATION_STRING);

        System.out.println(resultString.toString());
    }


    public static void startAddingMenu() {
        showMenuAdding();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            menuItem1Execute();
        }
        if (choice == 2) {
            menuItem2Execute();
        }
        if (choice == 3) {
            menuItem3Execute();//
        }

    }

    public static void menuItem1Execute() {//Add
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        String type = "";

        while (num < 1 || num > 2) {
            System.out.println("Пожалуйста, выберите тип асортемента, чтобы добавить его");
            System.out.println("1. Напиток\n2. Дессерт");
            num = scanner.nextInt();
            if (num == 1) {
                type = "Напиток";
            } else if (num == 2) {
                type = "Дессерт";
            }
            if (num < 1 || num > 2) {
                System.out.println("Неправильное число. Повторите ввод.");
            }
        }

        scanner.nextLine();
        System.out.println("Введите название на английском");
        String engName = scanner.nextLine();
        System.out.println("Введите название на руском");
        String rusName = scanner.nextLine();
        System.out.println("Введите цену");
        float price = scanner.nextFloat();

        CafeAssortmentDao cafeAssortmentDao = new CafeAssortmentImpl();
        CafeAssortment addCafeAssortment = new CafeAssortment();
        addCafeAssortment.setAssortment_type(type);
        addCafeAssortment.setTitle_eng(engName);
        addCafeAssortment.setTitle_rus(rusName);
        addCafeAssortment.setPrice(price);
        cafeAssortmentDao.save(addCafeAssortment);

    }

    public static void menuItem2Execute() {//Add
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО клиента");
        String fullName = scanner.nextLine();
        System.out.println("Введите дату рождения в формате yyyy/MM/dd");
        String strDate = scanner.nextLine();
        System.out.println("Введите контактный телефон");
        String contactPhone = scanner.nextLine();
        System.out.println("Введите контактный почтовый адрес");
        String contactAddress = scanner.nextLine();
        System.out.println("Введите скидку");
        float discount = scanner.nextFloat();

        ClientsDaoImpl clientsDao = new ClientsDaoImpl();
        Clients addClients = new Clients();
        addClients.setDiscount(discount);
        addClients.setFull_name(fullName);
        addClients.setDate_of_birth(stringToDate(strDate));
        addClients.setContact_phone(contactPhone);
        addClients.setContact_address(contactAddress);
        clientsDao.save(addClients);

    }

    public static void menuItem3Execute() {//Add
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        String position = "";

        while (num < 1 || num > 3) {
            System.out.println("Пожалуйста, выберите позицию персонала, чтобы добавить его");
            System.out.println("1. Бариста\n2. Официант\n3. Кондитер");
            num = scanner.nextInt();
            if (num == 1) {
                position = "Бариста";
            } else if (num == 2) {
                position = "Официант";
            } else if (num == 3) {
                position = "Кондитер";
            }
            if (num < 1 || num > 3) {
                System.out.println("Неправильное число. Повторите ввод.");
            }
        }

        scanner.nextLine();
        System.out.println("Введите ФИО");
        String fullName = scanner.nextLine();
        System.out.println("Введите дату рождения в формате yyyy/MM/dd");
        String strDate = scanner.nextLine();
        System.out.println("Введите контактный телефон");
        String contactPhone = scanner.nextLine();
        System.out.println("Введите контактный почтовый адрес");
        String contactAddress = scanner.nextLine();

        StaffDaoImpl staffDao = new StaffDaoImpl();
        Staff addStaff = new Staff();
        addStaff.setPosition(position);
        addStaff.setFull_name(fullName);
        addStaff.setDate_of_birth(stringToDate(strDate));
        addStaff.setContact_phone(contactPhone);
        addStaff.setContact_address(contactAddress);
        staffDao.save(addStaff);
    }

    public static Date stringToDate(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private MenuAdding() {
    }

}
