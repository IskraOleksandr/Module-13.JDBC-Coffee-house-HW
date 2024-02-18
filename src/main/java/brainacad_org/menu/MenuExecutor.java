package brainacad_org.menu;


import brainacad_org.dao.clientsDAO.ClientsDao;
import brainacad_org.dao.clientsDAO.ClientsDaoImpl;
import brainacad_org.dao.cafeassortmentDAO.CafeAssortmentDao;
import brainacad_org.dao.cafeassortmentDAO.CafeAssortmentImpl;
import brainacad_org.dao.staffDAO.StaffDao;
import brainacad_org.dao.staffDAO.StaffDaoImpl;

import brainacad_org.model.CafeAssortment;
import brainacad_org.model.Clients;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static brainacad_org.menu.MenuAdding.startAddingMenu;
import static brainacad_org.menu.MenuDeleting.startDeletingMenu;
import static brainacad_org.menu.MenuEditing.startEditingMenu;
import static brainacad_org.menu.MenuPublisher.*;
import static brainacad_org.menu.MenuShow.startShowMenu;


public class MenuExecutor {

    public static void startMenu() {
        showMenu();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            startAddingMenu();
        }
        if (choice == 2) {
            startEditingMenu();
        }
        if (choice == 3) {
            startDeletingMenu();
        }
        if (choice == 4) {
            startShowMenu();
        }

    }

    private MenuExecutor() {
    }

}
