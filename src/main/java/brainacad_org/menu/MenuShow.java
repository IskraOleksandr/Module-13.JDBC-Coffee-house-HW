package brainacad_org.menu;


import brainacad_org.dao.cafeassortmentDAO.CafeAssortmentDao;
import brainacad_org.dao.cafeassortmentDAO.CafeAssortmentImpl;
import brainacad_org.dao.clientsDAO.ClientsDaoImpl;
import brainacad_org.dao.staffDAO.StaffDao;
import brainacad_org.dao.staffDAO.StaffDaoImpl;
import brainacad_org.model.CafeAssortment;
import brainacad_org.model.Clients;
import brainacad_org.model.Staff;

import java.util.List;
import java.util.Scanner;

import static brainacad_org.menu.MenuPublisher.*;

public class MenuShow {
    private static final String SHOW_DRINK_OR_DESSERT = "Показать асортементы меню по типу(напиток или десерт)";
    private static final String SHOW_ALL_CAFE_ASORTMENT = "Показать весь асортемент меню";
    private static final String SHOW_CLIENTS = "Показать всех клиентов";
    private static final String SHOW_PERSONAL_BY_POSITION = "Показать персонал по типу";
    private static final String SHOW_ALL_PERSONAL = "Показать весь персонал";
    private static final String INVATION_STRING = "Пожалуйста, введите цифру";
    private static final String SEPARATOR = "-";
    private static final String DOT_SPACE = ".  ";
    private static final String SPACE = "   ";
    private static final String END_LINE = "\n";
    private static final String LIST_OF_CAFE_ASORTMENTS = "Список десертов и напитков";
    private static final String LIST_OF_DRINKS = "Список напитков";
    private static final String LIST_OF_DESSERTS = "Список дессертов";
    private static final String LIST_OF_STAFFS = "Список персонала";
    private static final String LIST_OF_CLIENTS = "Список персонала";
    private static final String LIST_OF_BARISTA = "Список бариста";
    private static final String LIST_OF_WAITER = "Список официантов";
    private static final String LIST_OF_CONFECTIONER = "Список кондитеров";


    public static void showMenuShow() {
        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        resultString.append("Меню отображения")
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(SHOW_DRINK_OR_DESSERT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(SHOW_ALL_CAFE_ASORTMENT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(SHOW_CLIENTS)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(SHOW_PERSONAL_BY_POSITION)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(SHOW_ALL_PERSONAL)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(INVATION_STRING);

        System.out.println(resultString.toString());
    }


    public static void startShowMenu() {
        showMenuShow();

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
        if (choice == 4) {
            menuItem4Execute();//
        }
        if (choice == 5) {
            menuItem5Execute();//
        }
    }

    public static void menuItem1Execute() {//show
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        int num = 0;

        while (num < 1 || num > 2) {
            System.out.println("Пожалуйста, выберите тип асортемента, для отображения");
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
        String str = showCafeAssortmentListByType(flag);

        if (!str.isEmpty()) {
            System.out.println(str);
        } else System.out.println("Нет асортемента для отображения");
    }

    public static void menuItem2Execute() {
        String str = showCafeAssortmentList();
        if (str.length() > 0) {
            System.out.println(str);
        } else System.out.println("Нет асортемента для отображения");
    }

    public static void menuItem3Execute() {
        String str = showClientsList();
        if (!str.isEmpty()) {
            System.out.println(str);
        } else System.out.println("Нет клиентов для отображения");
    }

    public static void menuItem4Execute() {//show
        Scanner scanner = new Scanner(System.in);
        int num = 0;

        while (num < 1 || num > 3) {
            System.out.println("Пожалуйста, выберите позицию персонала, для отображения");
            System.out.println("1. Бариста\n2. Официант\n3. Кондитер");
            num = scanner.nextInt();

            if (num < 1 || num > 3) {
                System.out.println("Неправильное число. Повторите ввод.");
            }
        }
        String str = showStaffListByPosition(num);
        if (!str.isEmpty()) {
            System.out.println(str);
        } else System.out.println("Нет асортемента для отображения");
    }

    public static void menuItem5Execute() {
        String str = showStaffList();
        if (!str.isEmpty()) {
            System.out.println(str);
        } else System.out.println("Нет персонала для отображения");
    }
    public static String showCafeAssortmentList() {
        CafeAssortmentDao groupDao = new CafeAssortmentImpl();
        List<CafeAssortment> groups = groupDao.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_CAFE_ASORTMENTS)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append("№").append(SPACE)
                .append("Id").append(SPACE)
                .append("English Name").append(SPACE)
                .append("Rusian Name").append(SPACE).append(SPACE)
                .append("Type").append(SPACE).append(SPACE).append(SPACE)
                .append("Price").append(SPACE)
                .append(END_LINE);

        for (var currentGroup : groups) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append(currentGroup.getId())
                    .append(SPACE).append(SPACE)
                    .append(currentGroup.getTitle_eng())
                    .append(SPACE).append(SPACE)//.append(SPACE)
                    .append(currentGroup.getTitle_rus())
                    .append(SPACE).append(SPACE).append(SPACE)//.append(SPACE)
                    .append(currentGroup.getAssortment_type())
                    .append(SPACE).append(SPACE)
                    .append(currentGroup.getPrice())
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        return resultString.toString();
    }

    public static String showCafeAssortmentListByType(boolean flag) {
        CafeAssortmentDao cafeAssortmentDao = new CafeAssortmentImpl();
        List<CafeAssortment> cafeAssortments = cafeAssortmentDao.findByAssortmenType(flag);

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        String list = LIST_OF_DESSERTS;
        if (flag == true) {
            list = LIST_OF_DRINKS;
        }
        if (!cafeAssortments.isEmpty()) {
            resultString.append(list)
                    .append(END_LINE)
                    .append(SEPARATOR.repeat(60))
                    .append(END_LINE)
                    .append("№").append(SPACE)
                    .append("Id").append(SPACE)
                    .append("English Name").append(SPACE)
                    .append("Rusian Name").append(SPACE).append(SPACE)
                    .append("Type").append(SPACE).append(SPACE).append(SPACE)
                    .append("Price").append(SPACE)
                    .append(END_LINE);

            for (var currentGroup : cafeAssortments) {
                resultString.append(menuLine++)
                        .append(DOT_SPACE)
                        .append(currentGroup.getId())
                        .append(SPACE).append(SPACE)
                        .append(currentGroup.getTitle_eng())
                        .append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentGroup.getTitle_rus())
                        .append(SPACE).append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentGroup.getAssortment_type())
                        .append(SPACE).append(SPACE)
                        .append(currentGroup.getPrice())
                        .append(END_LINE);
            }
            resultString.append(SEPARATOR.repeat(60))
                    .append(END_LINE);
        }
        return resultString.toString();
    }

    public static String showClientsList() {
        ClientsDaoImpl clientsDao = new ClientsDaoImpl();
        List<Clients> clients = clientsDao.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        if (!clients.isEmpty()) {
            resultString.append(LIST_OF_CLIENTS)
                    .append(END_LINE)
                    .append(SEPARATOR.repeat(60))
                    .append(END_LINE)
                    .append("№").append(SPACE)
                    .append("Id").append(SPACE)
                    .append("Full Name").append(SPACE)
                    .append("Date of birth").append(SPACE).append(SPACE)
                    .append("Contact phone").append(SPACE).append(SPACE).append(SPACE)
                    .append("Contact addres").append(SPACE).append(SPACE).append(SPACE)
                    .append("Discount").append(SPACE)
                    .append(END_LINE);

            for (var currentClients : clients) {
                resultString.append(menuLine++)
                        .append(DOT_SPACE)
                        .append(currentClients.getId())
                        .append(SPACE).append(SPACE)
                        .append(currentClients.getFull_name())
                        .append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentClients.getDate_of_birth())
                        .append(SPACE).append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentClients.getContact_phone())
                        .append(SPACE).append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentClients.getContact_address())
                        .append(SPACE).append(SPACE)
                        .append(currentClients.getDiscount())
                        .append(END_LINE);
            }
            resultString.append(SEPARATOR.repeat(60))
                    .append(END_LINE);
        }
        return resultString.toString();
    }

    public static String showStaffList() {
        StaffDao staffDao = new StaffDaoImpl();
        List<Staff> staffs = staffDao.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        if (!staffs.isEmpty()) {
            resultString.append(LIST_OF_STAFFS)
                    .append(END_LINE)
                    .append(SEPARATOR.repeat(60))
                    .append(END_LINE)
                    .append("№").append(SPACE)
                    .append("Id").append(SPACE)
                    .append("Full Name").append(SPACE)
                    .append("Date of birth").append(SPACE).append(SPACE)
                    .append("Contact phone").append(SPACE).append(SPACE).append(SPACE)
                    .append("Contact addres").append(SPACE).append(SPACE).append(SPACE)
                    .append("Position").append(SPACE)
                    .append(END_LINE);

            for (var currentStaff : staffs) {
                resultString.append(menuLine++)
                        .append(DOT_SPACE)
                        .append(currentStaff.getId())
                        .append(SPACE).append(SPACE)
                        .append(currentStaff.getFull_name())
                        .append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentStaff.getDate_of_birth())
                        .append(SPACE).append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentStaff.getContact_phone())
                        .append(SPACE).append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentStaff.getContact_address())
                        .append(SPACE).append(SPACE)
                        .append(currentStaff.getPosition())
                        .append(END_LINE);
            }
            resultString.append(SEPARATOR.repeat(60))
                    .append(END_LINE);
        }

        return resultString.toString();
    }

    public static String showStaffListByPosition(int flag) {
        StaffDao staffDao = new StaffDaoImpl();
        List<Staff> staffs = staffDao.findByStaffPosition(flag);

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        String list = "";
        if (flag == 1) {
            list = LIST_OF_BARISTA;
        } else if (flag == 2) {
            list = LIST_OF_WAITER;
        } else if (flag == 3) {
            list = LIST_OF_CONFECTIONER;
        }
        if (!staffs.isEmpty()) {
            resultString.append(list)
                    .append(END_LINE)
                    .append(SEPARATOR.repeat(60))
                    .append(END_LINE)
                    .append("№").append(SPACE)
                    .append("Id").append(SPACE)
                    .append("Full Name").append(SPACE)
                    .append("Date of birth").append(SPACE).append(SPACE)
                    .append("Contact phone").append(SPACE).append(SPACE).append(SPACE)
                    .append("Contact addres").append(SPACE).append(SPACE).append(SPACE)
                    .append("Position").append(SPACE)
                    .append(END_LINE);

            for (var currentStaff : staffs) {
                resultString.append(menuLine++)
                        .append(DOT_SPACE)
                        .append(currentStaff.getId())
                        .append(SPACE).append(SPACE)
                        .append(currentStaff.getFull_name())
                        .append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentStaff.getDate_of_birth())
                        .append(SPACE).append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentStaff.getContact_phone())
                        .append(SPACE).append(SPACE).append(SPACE)//.append(SPACE)
                        .append(currentStaff.getContact_address())
                        .append(SPACE).append(SPACE)
                        .append(currentStaff.getPosition())
                        .append(END_LINE);
            }
            resultString.append(SEPARATOR.repeat(60))
                    .append(END_LINE);
        }

        return resultString.toString();
    }
    private MenuShow() {
    }

}
