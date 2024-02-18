package brainacad_org.service;

import brainacad_org.dao.ConnectionFactory;
//import org.example.dao.courseDAO.CourseDao;
//import org.example.dao.courseDAO.CourseDaoImpl;
//import org.example.dao.groupDAO.GroupDao;
//import org.example.dao.groupDAO.GroupDaoImpl;
//import org.example.dao.studentDAO.StudentDao;
//import org.example.dao.studentDAO.StudentDaoImpl;
//import org.example.dao.studentandcourseDAO.StudentsToCoursesDao;
//import org.example.dao.studentandcourseDAO.StudentsToCoursesDaoImpl;
//import org.example.model.Course;
//import org.example.model.Group;
//import org.example.model.Student;
//import org.example.model.StudentsToCourse;
import brainacad_org.dao.cafeassortmentDAO.CafeAssortmentDao;
import brainacad_org.dao.cafeassortmentDAO.CafeAssortmentImpl;
import brainacad_org.dao.clientsDAO.ClientsDao;
import brainacad_org.dao.clientsDAO.ClientsDaoImpl;
import brainacad_org.dao.staffDAO.StaffDao;
import brainacad_org.dao.staffDAO.StaffDaoImpl;
import brainacad_org.model.Clients;
import brainacad_org.service.exception.ConnectionDBException;
import brainacad_org.service.exception.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoffeeHouseDbInitializer {

    private static final Random RANDOM_GENERATOR = new Random();
    private static final List<String> TABLES_NAME_ARRAY;
    private static final String SQL_SCRIPT_CREATE_TABLES;

    static {
        SQL_SCRIPT_CREATE_TABLES = PropertyFactory.getInstance().getProperty().getProperty("db.sqlScriptCreateTables");

        String tablesNames = PropertyFactory.getInstance().getProperty().getProperty("db.tablesNames");
        TABLES_NAME_ARRAY = Arrays.stream(tablesNames.split(",")).collect(Collectors.toList());
    }

    public static void createTables() {
        try {
            Connection conn = ConnectionFactory.getInstance().makeConnection();

            for (var tableName : TABLES_NAME_ARRAY) {
                if (!tableExists(tableName)) {

                    try (Stream<String> lineStream = Files.lines(Paths.get(SQL_SCRIPT_CREATE_TABLES))) {
                        StringBuilder createTablesQuery = new StringBuilder();

                        for (var currentString : lineStream.collect(Collectors.toList())) {
                            createTablesQuery.append(currentString).append(" ");
                        }

                        try (PreparedStatement ps = conn.prepareStatement(createTablesQuery.toString())) {
                            ps.execute();
                        }

                    } catch (IOException exception) {
                        throw new FileException("Error with createTables.sql");
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }

        } catch (ConnectionDBException | FileException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void createTablesForTests() {
        try {
            try (Connection conn = ConnectionFactory.getInstance().makeConnection()) {

                try (Stream<String> lineStream = Files.lines(Paths.get(SQL_SCRIPT_CREATE_TABLES))) {
                    StringBuilder createTablesQuery = new StringBuilder();

                    for (var currentString : lineStream.collect(Collectors.toList())) {
                        createTablesQuery.append(currentString).append("\n");
                    }

                    try (PreparedStatement ps = conn.prepareStatement(createTablesQuery.toString())) {
                        ps.execute();
                    }

                } catch (IOException exception) {
                    throw new FileException("Error with createTables.sql");
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ConnectionDBException | FileException e) {
            System.err.println(e.getMessage());
        }
    }


    private static boolean tableExists(String tableName) throws ConnectionDBException {
        try (Connection connection = ConnectionFactory.getInstance().makeConnection()){
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[]{"TABLE"});
            return resultSet.next();
        } catch (SQLException exception) {
            throw new ConnectionDBException("error connection to DB");
        }
    }

    private CoffeeHouseDbInitializer() {
    }

}
