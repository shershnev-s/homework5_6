package by.tut.shershnev_s;

import by.tut.shershnev_s.controller.HomeWorkController;
import by.tut.shershnev_s.controller.impl.*;

public class App {
    public static void main(String[] args) {
        HomeWorkController homeWorkController = new HomeWorkControllerImpl();
        homeWorkController.runHomeWork();
    }
}
