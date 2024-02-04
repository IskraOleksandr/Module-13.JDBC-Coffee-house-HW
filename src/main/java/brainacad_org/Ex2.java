package brainacad_org;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

/**
 * Hello world!
 */
public class Ex2 {
    public static final String QUERY1 = """
            insert into CafeAssortment(title_eng, title_rus, assortment_type, price)
            values ('Croissant', 'Круассан', 'Дессерт', 25);
            insert into CafeAssortment(title_eng, title_rus, assortment_type, price)
            values ('Cappuccino', 'Капучино', 'Напиток', 10)""";

    public static final String QUERY2 = """
            INSERT INTO staff (full_name, date_of_birth, contact_phone, contact_address, position)
            VALUES ('Иванов Иван', '1990-01-01', '123456789', 'ул. Центральная, 1', 'Бариста') """;

    public static final String QUERY3 = """
            INSERT INTO staff (full_name, date_of_birth, contact_phone, contact_address, position)
            VALUES ('Петрова Анна', '1995-05-15', '987654321', 'ул. Гоголя, 10', 'Кондитер')""";

    public static final String QUERY4 = """
            INSERT INTO clients (full_name, date_of_birth, contact_phone, contact_address, discount)
             VALUES ('Смирнова Екатерина', '1988-09-23', '456789123', 'ул. Лермонтова, 5', 0.1)""";

    public static void main(String[] args) {



        try (Connection connection1 = getConnection();) {
            Statement statement = connection1.createStatement();

            System.out.println("\nДобавление новой позиции в ассортимент кафе");
            statement.execute(QUERY1);

            System.out.println("\nДобавление информации о новом бариста");
            statement.execute(QUERY2);

            System.out.println("\nДобавление информации о новом кондитере");
            statement.execute(QUERY3);

            System.out.println("\nДобавление информации о новом клиенте");
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
