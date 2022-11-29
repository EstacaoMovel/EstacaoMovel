package pt.isec.gps.tp.ui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pt.isec.gps.tp.Utils;
import pt.isec.gps.tp.modelo.AppManager;

public class InicioUI extends BorderPane {

    AppManager appManager;

    private Label titulo;
    private Button inicio;

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

        titulo = new Label();
        titulo.setText("ESTAÇÃO MÓVEL");
        titulo.setFont( new Font("Arial", 24) );
        titulo.setAlignment(Pos.CENTER);


        principalVBox = new VBox();
        principalVBox.getChildren().addAll(titulo);
        principalVBox.setAlignment(Pos.CENTER);
        //principalVBox.setPadding( new Insets(50, 0, 50, 0) );
        principalVBox.setSpacing(100);
        principalVBox.setStyle("-fx-border-color: black;");
        principalVBox.setMinHeight(Utils.appHeightSize-300);
        principalVBox.setMaxHeight(Utils.appHeightSize-300);

        this.setCenter(principalVBox);


    }

    private void registerHandlers() {

    }

    private void update() {

    }

}
