package pt.isec.gps.tp.ui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.ui.estados.*;

public class RootPane extends BorderPane {

    AppManager appManager;

    public RootPane(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        StackPane stackPane = new StackPane(
                new InicioUI(appManager),
                new RecolhaDadosUI(appManager),
                new AutocarrosDisponiveisUI(appManager),
                new DetalhesAutocarroUI(appManager),
                new NotificacoesUI(appManager)
        );

        this.setCenter(stackPane);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> {
            update();
        });

    }

    private void update() {

    }
}
