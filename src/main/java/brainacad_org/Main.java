package brainacad_org;

import brainacad_org.service.CoffeeHouseInitializer;

import static brainacad_org.menu.MenuExecutor.startMenu;

public class Main {

    public static void main(String[] args) {
        System.setProperty("test", "false");
        //тут тільки перше завдання по Кофейні
        CoffeeHouseInitializer school = new CoffeeHouseInitializer();
        school.schoolInitialize();
        while (true) {
            startMenu();
        }
    }

}
