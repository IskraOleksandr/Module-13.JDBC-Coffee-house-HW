package brainacad_org.service;


import static java.lang.System.setProperty;

public class CoffeeHouseInitializer {

    public void schoolInitialize() {
        setProperty("test", "false");
        CoffeeHouseDbInitializer.createTables();
    }
}
