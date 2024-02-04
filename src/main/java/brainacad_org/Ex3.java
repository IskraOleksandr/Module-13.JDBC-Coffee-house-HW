package brainacad_org;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 */
public class Ex3 {
    public static final String QUERY1 = """
        UPDATE CafeAssortment
        SET price = 4.2 WHERE title_eng = 'Cappuccino' """;

    public static final String QUERY2 = """
            UPDATE staff
             SET contact_phone = '555555555', contact_address = 'ул. Толстого, 20'
             WHERE position = 'Кондитер' """;

    public static final String QUERY3 = """
            UPDATE staff
             SET contact_phone = '999999999' WHERE position = 'Бариста' """;

    public static final String QUERY4 = """
            UPDATE clients
            SET discount = 0.15 WHERE full_name = 'Смирнова Екатерина' """;

    public static void main(String[] args) {

        try (Connection connection1 = getConnection();) {
            Statement statement = connection1.createStatement();

            System.out.println("\nИзменить цену на определенный вид кофе");
            statement.execute(QUERY1);

            System.out.println("\nИзменить контактный, почтовый адрес кондитеру");
            statement.execute(QUERY2);

            System.out.println("\nИзменить контактный телефон бариста");
            statement.execute(QUERY3);

            System.out.println("\nИзменить процент скидки конкретного клиента");
            statement.execute(QUERY4);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5436/Coffee_house_db",
                "IskraOl", "admin");
    }


}
