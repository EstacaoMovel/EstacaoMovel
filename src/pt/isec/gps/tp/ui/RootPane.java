package pt.isec.gps.tp.ui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.ui.estados.*;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RootPane extends BorderPane {

    AppManager appManager;
    private int counter;
    private TextArea textArea;
    public RootPane(AppManager appManager) {
        this.appManager = appManager;
        this.appManager.setRp(this);
        this.textArea = new TextArea();
        createViews();
        registerHandlers();
        update();

        Thread thread = new Thread(() -> {
            try {
                for (;;) {
                    Thread.sleep(1000);

                    if(appManager.getNewNotification()) {

                        FutureTask<Void> interrupt = new FutureTask<>(() -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("ALERTA_Notificação");
                            alert.setHeaderText("O AutocarroX teve uma avaria.");
                            alert.showAndWait();
                            return null ;
                        });
                        Platform.runLater(interrupt);
                        interrupt.get();
                    }

                    Platform.runLater(() ->
                            textArea.appendText(Thread.currentThread().getName() + ": " + counter+ "\n"));

                    appManager.setNewNotification(false);

                }

            } catch (InterruptedException exc) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException exc) {
                exc.printStackTrace();
            }
        });

        thread.start();



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
    public void triggerAlert(){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.showAndWait().ifPresent(event->{

        });
    }


    private void update() {




    }
}
