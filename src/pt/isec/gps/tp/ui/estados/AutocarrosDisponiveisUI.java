package pt.isec.gps.tp.ui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pt.isec.gps.tp.utils.*;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.modelo.fsm.AppState;


public class AutocarrosDisponiveisUI extends BorderPane {
    private AppManager appManager;
    private DataHora dataHora;

    private Label horaSistema;
    private HBox horaSistHBox;

    private Button voltar;
    private HBox voltarHBox;

    private Label titulo;

    private ComboBox escolherEmpresaCBox;
    private TextField horaP, horaC;
    private VBox escolherEmpresaVBox, horasFiltroVBox;
    private HBox filtrosConjunto;

    private Button confirmar;

    private Button recolhaDados, notificacoes;
    private HBox conjunto;

    private VBox principalVBox;

    public AutocarrosDisponiveisUI(AppManager appManager) {
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

        horaSistema = new Label("xxx");
        horaSistHBox = new HBox(horaSistema);
        horaSistHBox.setPadding(new Insets(-50, 0, 0, 10));
        horaSistHBox.setAlignment(Pos.CENTER_LEFT);

        voltar = new Button("< Voltar");
        voltar.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        voltarHBox = new HBox(voltar);
        voltarHBox.setPadding(new Insets(10, 0, 30, 30));
        voltarHBox.setAlignment(Pos.CENTER_LEFT);

        titulo = new Label();
        titulo.setText("ESTAÇÃO MÓVEL");
        titulo.setFont( new Font("Times New Roman", 28) );
        titulo.setAlignment(Pos.CENTER);



        escolherEmpresaCBox = new ComboBox();
        escolherEmpresaCBox.setPromptText("Escolha a empresa");
        escolherEmpresaCBox.getItems().add("SMTUC");
        escolherEmpresaCBox.getItems().add("Transdev");

        horaP = new TextField();
        horaC = new TextField();

        horasFiltroVBox = new VBox(horaP, horaC);

        escolherEmpresaVBox = new VBox(escolherEmpresaCBox);
        escolherEmpresaVBox.setAlignment(Pos.CENTER);
        escolherEmpresaVBox.setPadding(new Insets(20, 0, 100, 0));

        filtrosConjunto = new HBox(escolherEmpresaVBox, horasFiltroVBox);

        confirmar = new Button("Confirmar");
        confirmar.setAlignment(Pos.CENTER);

        recolhaDados = new Button("Origem/Destino");
        notificacoes = new Button("Notificações");
        conjunto = new HBox(recolhaDados, notificacoes);
        conjunto.setAlignment(Pos.CENTER);
        conjunto.setPadding(new Insets(40, 0, 0, 0));
        conjunto.setSpacing(50);

        principalVBox = new VBox();
        principalVBox.getChildren().addAll(horaSistHBox, voltarHBox, titulo, filtrosConjunto, confirmar, conjunto);
        principalVBox.setAlignment(Pos.TOP_CENTER);
        //principalVBox.setPadding( new Insets(50, 0, 50, 0) );
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

        voltar.setOnAction(event -> {
            appManager.voltar();
        });

    }

    private void update() {
        if(appManager.getState() != AppState.AUTOCARROS_DISPONIVEIS_STATE){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
