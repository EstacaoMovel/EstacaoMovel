package pt.isec.gps.tp.ui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pt.isec.gps.tp.utils.*;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.modelo.fsm.AppState;


public class RecolhaDadosUI extends BorderPane {
    private AppManager appManager;
    private DataHora dataHora;

    private Label horaSistema;
    private HBox horaSistHBox;

    private Button voltar;
    private HBox voltarHBox;

    private Label titulo;
    private Label origemLabel, destinoLabel;
    private TextField origemTField, destinoTField;
    private VBox origemVBox, destinoVBox;

    private ComboBox escolherDiasCBox;
    private VBox escolherdiasVBox;

    private Button confirmar;

    private Button recolhaDados, notificacoes;
    private HBox conjunto;

    private VBox principalVBox;

    public RecolhaDadosUI(AppManager appManager) {
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

        origemLabel = new Label("Selecione a sua origem");
        origemLabel.setFont( new Font("Arial", 20) );
        origemLabel.setAlignment(Pos.CENTER);
        origemTField = new TextField();
        origemTField.setPromptText("Origem");
        origemTField.setMinWidth(150);
        origemTField.setMaxWidth(150);
        origemVBox = new VBox(origemLabel, origemTField);
        origemVBox.setPadding(new Insets(40, 0, 10, 0));
        origemVBox.setSpacing(10);
        origemVBox.setAlignment(Pos.CENTER);

        destinoLabel = new Label("Selecione o seu destino");
        destinoLabel.setFont( new Font("Arial", 20) );
        destinoLabel.setAlignment(Pos.CENTER);
        destinoTField = new TextField();
        destinoTField.setPromptText("Destino");
        destinoTField.setMinWidth(150);
        destinoTField.setMaxWidth(150);
        destinoVBox = new VBox(destinoLabel, destinoTField);
        destinoVBox.setPadding(new Insets(40, 0, 50, 0));
        destinoVBox.setSpacing(10);
        destinoVBox.setAlignment(Pos.CENTER);


        dataHora = new DataHora();

        escolherDiasCBox = new ComboBox();
        escolherDiasCBox.setPromptText("Escolha o(s) dia(s)");
        escolherDiasCBox.getItems().add(dataHora.getDate(0));
        escolherDiasCBox.getItems().add(dataHora.getDate(1));
        escolherDiasCBox.getItems().add(dataHora.getDate(1));

        escolherdiasVBox = new VBox(escolherDiasCBox);
        escolherdiasVBox.setAlignment(Pos.CENTER);
        escolherdiasVBox.setPadding(new Insets(20, 0, 100, 0));

        confirmar = new Button("Confirmar");
        confirmar.setAlignment(Pos.CENTER);

        recolhaDados = new Button("Origem/Destino");
        notificacoes = new Button("Notificações");
        conjunto = new HBox(recolhaDados, notificacoes);
        conjunto.setAlignment(Pos.CENTER);
        conjunto.setPadding(new Insets(40, 0, 0, 0));
        conjunto.setSpacing(50);

        principalVBox = new VBox();
        principalVBox.getChildren().addAll(horaSistHBox, voltarHBox, titulo, origemVBox, destinoVBox, escolherdiasVBox, confirmar, conjunto);
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
        if(appManager.getState() != AppState.RECOLHA_DADOS_STATE){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
