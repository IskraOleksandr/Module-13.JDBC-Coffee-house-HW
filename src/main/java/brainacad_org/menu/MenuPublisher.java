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

public class MenuPublisher {

    private static final String ACTION_STRING = "Кофейня";
    private static final String ADDING_MENU = "Меню добавления";
    private static final String EDITING_MENU = "Меню измения";
    private static final String DELETING_MENU = "Меню удаления";
    private static final String SHOW_MENU = "Меню отображения";
    private static final String INVATION_STRING = "Пожалуйста, введите цифру";
    private static final String SEPARATOR = "-";
    private static final String DOT_SPACE = ".  ";

    private static final String END_LINE = "\n";



    public static void showMenu() {

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        resultString.append(ACTION_STRING)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADDING_MENU)//
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(EDITING_MENU)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETING_MENU)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(SHOW_MENU)
                .append(END_LINE)
                .append(INVATION_STRING);

        System.out.println(resultString.toString());
    }



    private MenuPublisher() {
    }

}
