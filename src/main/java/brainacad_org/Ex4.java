package brainacad_org;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 */
public class Ex4 {
    public static final String QUERY1 = """ 
            DELETE FROM CafeAssortment
            WHERE title_eng = 'Croissant' """;

    public static final String QUERY2 = """
            DELETE FROM staff
            WHERE full_name = 'Иванов Иван'""";

    public static final String QUERY3 = """
            DELETE FROM staff
            WHERE full_name = 'Петрова Анна' """;

    public static final String QUERY4 = """
            DELETE FROM clients
            WHERE full_name = 'Смирнова Екатерина'""";

    public static void main(String[] args) {


        try (Connection connection1 = getConnection();) {
            Statement statement = connection1.createStatement();

            System.out.println("\nУдаление информации о конкретном десерте");
            statement.execute(QUERY1);

            System.out.println("\nУдаление информации об определенном официанте по причине увольнения");
            statement.execute(QUERY2);

            System.out.println("\nУдаление информации об определенном бариста по причине увольнения");
            statement.execute(QUERY3);

            System.out.println("\nУдаление информации о конкретном клиенте");
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
