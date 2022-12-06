package pt.isec.gps.tp.ui.estados;

import javafx.scene.layout.BorderPane;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.modelo.fsm.AppState;

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

        appManager.addPropertyChangeListener(evt -> {
            update();
        });
        
    }

    private void update() {
        if(appManager.getState() != AppState.DETALHES_AUTOCARRO_STATE){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
