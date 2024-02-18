package brainacad_org.service;

import brainacad_org.service.exception.PropertyFileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;

public class PropertyReader {

    public Properties readProperties() throws PropertyFileException {
        Properties property = new Properties();

        String result = getProperty("test");

        if (result.equals("false")) {
            try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
                property.load(fileInputStream);
                return property;
            } catch (IOException e) {
                throw new PropertyFileException("Error open file property");
            }
        }
//        else {
//            try (FileInputStream fileInputStream1 = new FileInputStream("src/test/resources/application-test.properties")) {
//                property.load(fileInputStream1);
//                return property;
//            } catch (IOException e) {
//                throw new PropertyFileException("Error open file property");
//            }
//        }

        return property;
    }

}
