package brainacad_org;

import java.io.IOException;
import java.sql.*;

/**
 * Hello world!
 */
public class Ex5 {
    public static final String QUERY1 = """ 
            SELECT * FROM CafeAssortment
            WHERE assortment_type = 'Напиток'""";

    public static final String QUERY2 = """
            SELECT * FROM CafeAssortment
            WHERE assortment_type = 'Дессерт'""";

    public static final String QUERY3 = """
            SELECT * FROM staff
            WHERE position = 'Бариста'""";

    public static final String QUERY4 = """ 
            SELECT * FROM staff
           WHERE position = 'Официант'""";

    public static void main(String[] args) {

        try (Connection connection1 = getConnection();) {
            Statement statement = connection1.createStatement();

            ResultSet resultSet1 = statement.executeQuery(QUERY1);
            System.out.println("\nВсе напитки");
            printAllCafeAssortment(resultSet1);

            ResultSet resultSet2 = statement.executeQuery(QUERY2);
            System.out.println("\nВсе десерты");
            printAllCafeAssortment(resultSet2);

            ResultSet resultSet3 = statement.executeQuery(QUERY3);
            System.out.println("\nИнформацию обо всех бариста");
            printAllStaff(resultSet3);

            ResultSet resultSet4 = statement.executeQuery(QUERY4);
            System.out.println("\nИнформацию обо всех официантах");
            printAllStaff(resultSet4);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }//

    public static void printAllCafeAssortment(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String title_eng = resultSet.getString("title_eng");
            String title_rus = resultSet.getString("title_rus");
            String assortment_type = resultSet.getString("assortment_type");
            float price = resultSet.getFloat("price");
            System.out.println(title_eng + " " + title_rus + " " + assortment_type + " " + price);
        }
    }
    public static void printAllStaff(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String full_name = resultSet.getString("full_name");
            String date_of_birth = resultSet.getString("date_of_birth");
            String contact_phone = resultSet.getString("contact_phone");
            String contact_address = resultSet.getString("contact_address");
            String position = resultSet.getString("position");
            System.out.println(full_name + " " + date_of_birth + " " + contact_phone
                    + " " + contact_address + " " + position);
        }
    }
    public static Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5436/Coffee_house_db",
                "IskraOl", "admin");
    }

}
