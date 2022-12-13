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
import pt.isec.gps.tp.utils.DataHora;
import pt.isec.gps.tp.utils.UtilsUI;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.modelo.fsm.AppState;

public class InicioUI extends BorderPane {

    private AppManager appManager;
    private DataHora dataHora = new DataHora();

    private Label horaSistema;
    private HBox horaSistHBox;
    Line linhaTop;
    private VBox cimaVBox;

    private Label titulo;
    private VBox tituloHBox;

    private Button inicio;
    private HBox inicioHBox;

    private Line linhaBottom;
    private Label nomeEmpresaLabel;

    private VBox principalTopVBox, principalBottomVBox;


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

        horaSistema = new Label("" + dataHora.getTime());
        horaSistema.setFont( new Font("Sans Serif", 12) );
        horaSistHBox = new HBox(horaSistema);
        horaSistHBox.setPadding(new Insets(10, 0, 0, 10));
        horaSistHBox.setAlignment(Pos.CENTER_LEFT);

        linhaTop = new Line(0, 0, UtilsUI.appWidthSize, 0);

        cimaVBox = new VBox(horaSistHBox, linhaTop);
        cimaVBox.setAlignment(Pos.CENTER);
        cimaVBox.setSpacing(50);

        titulo = new Label();
        titulo.setText("ESTAÇÃO MÓVEL");
        titulo.setFont( new Font("Times New Roman", 28) );
        titulo.setAlignment(Pos.CENTER);

        tituloHBox = new VBox(titulo);
        tituloHBox.setAlignment(Pos.CENTER);


        inicio = new Button("Início");
        inicio.setPadding(new Insets(0, 5, 0, 5));
        inicio.setFont( new Font("Sans Serif", 24) );
        inicioHBox = new HBox(inicio);
        inicioHBox.setPadding(new Insets(30, 0, 50, 0));
        inicioHBox.setAlignment(Pos.CENTER);

        linhaBottom = new Line(0, 0, UtilsUI.appWidthSize, 0);

        nomeEmpresaLabel = new Label("EstaçãoMóvelLDA");


        principalTopVBox = new VBox();
        principalTopVBox.getChildren().addAll(cimaVBox);
        principalTopVBox.setAlignment(Pos.TOP_CENTER);
        //principalVBox.setPadding( new Insets(50, 0, 50, 0) );
        /*principalTopVBox.setMinWidth(UtilsUI.appWidthSize);
        principalTopVBox.setMaxWidth(UtilsUI.appWidthSize);*/
        principalTopVBox.setMinHeight(UtilsUI.appHeightSize - 10*UtilsUI.appHeightSize);
        principalTopVBox.setMaxHeight(UtilsUI.appHeightSize - 10*UtilsUI.appHeightSize);

        principalBottomVBox = new VBox();
        principalBottomVBox.getChildren().addAll(inicioHBox, linhaBottom, nomeEmpresaLabel);
        principalBottomVBox.setAlignment(Pos.BOTTOM_CENTER);
        principalBottomVBox.setPadding( new Insets(60, 0, 15, 0) );
        principalBottomVBox.setSpacing(50);
        /*principalBottomVBox.setMinWidth(UtilsUI.appWidthSize);
        principalBottomVBox.setMaxWidth(UtilsUI.appWidthSize);*/
        principalBottomVBox.setMinHeight(UtilsUI.appHeightSize - 90*UtilsUI.appHeightSize);
        principalBottomVBox.setMaxHeight(UtilsUI.appHeightSize - 90*UtilsUI.appHeightSize);

        this.setTop(principalTopVBox);
        this.setCenter(tituloHBox);
        this.setBottom(principalBottomVBox);

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

        horaSistema.setText(dataHora.getTime());
    }

}
