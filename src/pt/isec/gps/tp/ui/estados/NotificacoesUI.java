package pt.isec.gps.tp.ui.estados;

import javafx.scene.layout.BorderPane;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.modelo.fsm.AppState;

public class NotificacoesUI extends BorderPane {
    AppManager appManager;

    public NotificacoesUI(AppManager appManager) {
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
        if(appManager.getState() != AppState.NOTIFICACOES_STATE){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
