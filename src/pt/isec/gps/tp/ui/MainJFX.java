package pt.isec.gps.tp.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.gps.tp.Utils;
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
            //Scene scene = new Scene(root, 393.75, 700);
            Scene scene = new Scene(root, Utils.appWidthSize, Utils.appHeightSize);
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
}
