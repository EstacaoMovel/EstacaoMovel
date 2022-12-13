package pt.isec.gps.tp.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.gps.tp.utils.UtilsUI;
import pt.isec.gps.tp.modelo.AppManager;

public class MainJFX extends Application {
    AppManager appManager;

    @Override
    public void init() throws Exception {
        super.init();
        appManager = new AppManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            RootPane root = new RootPane(appManager);
            Scene scene = new Scene(root, UtilsUI.appWidthSize, UtilsUI.appHeightSize);
            stage.setScene(scene);
            stage.setTitle("@EstaçãoMóvel");
            stage.setMinWidth(393.75);
            stage.setMinHeight(700);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        appManager.escreveNotificacoes();
        appManager.stopThread();
        super.stop();
    }
}
