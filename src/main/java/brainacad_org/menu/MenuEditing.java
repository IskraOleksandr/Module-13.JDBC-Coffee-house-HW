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
import java.util.Scanner;

import static brainacad_org.menu.MenuShow.*;

public class MenuEditing {
    private static final String EDIT_DRINK_OR_DESSERT = "Изменить асортемент меню(напиток или десерт)";
    private static final String EDIT_CLIENT = "Изменить даные клиента";
    private static final String EDIT_PERSONAL = "Изменение персонал по типу и Id";
    private static final String INVATION_STRING = "Пожалуйста, введите цифру";
    private static final String SEPARATOR = "-";
    private static final String DOT_SPACE = ".  ";
    private static final String END_LINE = "\n";

    public static void showMenuEditing() {
        Scanner scanner = new Scanner(System.in);
        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        resultString.append("Меню изменения")
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(EDIT_DRINK_OR_DESSERT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(EDIT_CLIENT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(EDIT_PERSONAL)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(INVATION_STRING);

        System.out.println(resultString.toString());
    }


    public static void startEditingMenu() {
        showMenuEditing();

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

    public static void menuItem1Execute() {//Add
        Scanner scanner = new Scanner(System.in);
        String type = "";
        int num = 0;

        String str = showCafeAssortmentList();
        if (str.isEmpty()) {
            System.out.println("Нет асортемента для отображения");
        } else {
            System.out.println("Пожалуйста, выберите клиента, чтобы изменить");
            System.out.println(str);

            System.out.println("Пожалуйста, введите Id для изменения");
            long cafeAssortmentId = scanner.nextLong();
            scanner.nextLine();

            while (num < 1 || num > 2) {
                System.out.println("Пожалуйста, выберите тип асортемента, ");
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
            System.out.println("Введите новое название на английском");
            String engName = scanner.nextLine();
            System.out.println("Введите новое название на руском");
            String rusName = scanner.nextLine();

            System.out.println("Введите новую цену");
            float price = scanner.nextFloat();

            try {
                CafeAssortmentDao cafeAssortmentDao = new CafeAssortmentImpl();
                CafeAssortment addCafeAssortment = new CafeAssortment();
                addCafeAssortment.setId(cafeAssortmentId);
                addCafeAssortment.setTitle_eng(engName);
                addCafeAssortment.setTitle_rus(rusName);
                addCafeAssortment.setAssortment_type(type);

                addCafeAssortment.setPrice(price);
                cafeAssortmentDao.update(addCafeAssortment);

            } catch (IndexOutOfBoundsException e) {
                System.err.println("Invalid name of group");
            }
        }
    }

    public static void menuItem2Execute() {//Add
        String str = showClientsList();
        if (str.isEmpty()) {
            System.out.println("Нет клиентов для отображения");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Пожалуйста, выберите клиента, чтобы изменить");
            System.out.println(str);

            System.out.println("Пожалуйста, введите Id клиента для изменения");
            long clientId = scanner.nextLong();
            scanner.nextLine();

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

            try {
                ClientsDaoImpl clientsDao = new ClientsDaoImpl();
                Clients addClients = new Clients();
                addClients.setId(clientId);
                addClients.setDiscount(discount);
                addClients.setFull_name(fullName);
                addClients.setDate_of_birth(stringToDate(strDate));
                addClients.setContact_phone(contactPhone);
                addClients.setContact_address(contactAddress);
                clientsDao.update(addClients);

            } catch (IndexOutOfBoundsException e) {
                System.err.println("Invalid name of group");
            }
        }
    }

    public static void menuItem3Execute() {//Add
        String str = showStaffList(), position="";
        if (str.isEmpty()) {
            System.out.println("Нет персонала для изменения");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Пожалуйста, выберите тип асортемента, чтобы изменить");
            System.out.println(str);

            System.out.println("Пожалуйста, введите Id персонала для изменения");
            long clientId = scanner.nextLong();

            int num = 0;
            while (num < 1 || num > 3) {
                System.out.println("Пожалуйста, выберите позицию персонала,");
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
            System.out.println("Введите ФИО персонала");
            String fullName = scanner.nextLine();
            System.out.println("Введите дату рождения в формате yyyy/MM/dd");
            String strDate = scanner.nextLine();
            System.out.println("Введите контактный телефон");
            String contactPhone = scanner.nextLine();
            System.out.println("Введите контактный почтовый адрес");
            String contactAddress = scanner.nextLine();

            try {
                StaffDaoImpl staffsDao = new StaffDaoImpl();
                Staff addStafs = new Staff();
                addStafs.setId(clientId);
                addStafs.setPosition(position);
                addStafs.setFull_name(fullName);
                addStafs.setDate_of_birth(stringToDate(strDate));
                addStafs.setContact_phone(contactPhone);
                addStafs.setContact_address(contactAddress);
                staffsDao.update(addStafs);

            } catch (IndexOutOfBoundsException e) {
                System.err.println("Invalid name of group");
            }
        }
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

    private MenuEditing() {
    }

}
