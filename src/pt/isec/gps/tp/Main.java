package pt.isec.gps.tp;

import javafx.application.Application;
import pt.isec.gps.tp.ui.MainJFX;

public class Main {
    public static void main(String[] args) {
        System.out.println("A executar a GUI...");

        Application.launch(MainJFX.class, args);
    }
}