package pt.isec.gps.tp.ui.estados;

import javafx.scene.layout.BorderPane;
import pt.isec.gps.tp.modelo.AppManager;

public class DetalhesAutocarroUI extends BorderPane {
    AppManager appManager;

    public DetalhesAutocarroUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
    }

    private void registerHandlers() {
        
    }

    private void update() {
        
    }
}
