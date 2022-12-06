package pt.isec.gps.tp.ui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import pt.isec.gps.tp.utils.UtilsUI;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.modelo.fsm.AppState;

public class InicioUI extends BorderPane {

    AppManager appManager;

    private Line linha;
    private Label titulo;
    private Button inicio;

    private HBox linhaHBox;
    private VBox principalVBox;

    public InicioUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        this.setBackground(
                new Background(
                        new BackgroundFill(
                                Paint.valueOf(Color.WHITE.toString()),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        linha = new Line(200, 20, 300, 200);


        linhaHBox = new HBox();
        linhaHBox.setPadding(new Insets(50, 0, 20, 0));
        linhaHBox.getChildren().add(linha);


        titulo = new Label();
        titulo.setText("ESTAÇÃO MÓVEL");
        titulo.setFont( new Font("Arial", 24) );
        titulo.setAlignment(Pos.CENTER);


        inicio = new Button("Inicio");

        principalVBox = new VBox();
//        principalVBox.getChildren().add(linha);
//        principalVBox.getChildren().addAll(titulo, inicio);
        principalVBox.getChildren().add(linhaHBox);
        principalVBox.getChildren().addAll(titulo, inicio);
        principalVBox.setAlignment(Pos.TOP_CENTER);
        //principalVBox.setPadding( new Insets(50, 0, 50, 0) );
        //principalVBox.setSpacing(100);
        principalVBox.setMinWidth(UtilsUI.appWidthSize);
        principalVBox.setMaxWidth(UtilsUI.appWidthSize);
        principalVBox.setMinHeight(UtilsUI.appHeightSize);
        principalVBox.setMaxHeight(UtilsUI.appHeightSize);

        this.setCenter(principalVBox);

    }

    private void registerHandlers() {

        appManager.addPropertyChangeListener(evt -> {
            update();
        });

        inicio.setOnAction(event -> {
            appManager.recolherDados();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.INICIO_STATE){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }

}
