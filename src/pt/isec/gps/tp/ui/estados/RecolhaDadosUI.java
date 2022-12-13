package pt.isec.gps.tp.ui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import pt.isec.gps.tp.modelo.dados.Paragem;
import pt.isec.gps.tp.utils.*;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.modelo.fsm.AppState;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class RecolhaDadosUI extends BorderPane {
    private AppManager appManager;
    private DataHora dataHora = new DataHora();
    private AutoCompletionBinding<String> autoCompletionBinding;

    private Label horaSistema;
    private HBox horaSistHBox;
    private Button voltar;
    private HBox voltarHBox;
    private Label titulo;
    Line linhaTop;
    private VBox cimaVBox;

    private Label origemLabel, destinoLabel;
    private TextField origemTField, destinoTField;
    private VBox origemVBox, destinoVBox;

    Line linhaMiddle;

    private ComboBox escolherDiasCBox;
    private VBox escolherdiasVBox;

    private Button confirmar;
    Line linhaBottom;
    private Button recolhaDados, notificacoes;
    private HBox conjunto;

    private VBox principalTopVBox, principalBottomVBox;
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

        horaSistema = new Label("" + dataHora.getTime());
        horaSistema.setFont( new Font("Sans Serif", 12) );
        horaSistHBox = new HBox(horaSistema);
        horaSistHBox.setPadding(new Insets(10, 0, 0, 10));
        horaSistHBox.setAlignment(Pos.CENTER_LEFT);

        voltar = new Button("< Voltar");
        voltar.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        voltarHBox = new HBox(voltar);
        voltarHBox.setPadding(new Insets(0, 0, 0, 20));
        voltarHBox.setAlignment(Pos.CENTER_LEFT);

        titulo = new Label();
        titulo.setText("ESTAÇÃO MÓVEL");
        titulo.setFont( new Font("Times New Roman", 28) );
        titulo.setAlignment(Pos.CENTER);

        linhaTop = new Line(0, 0, UtilsUI.appWidthSize, 0);

        cimaVBox = new VBox(horaSistHBox, voltarHBox, titulo, linhaTop);
        cimaVBox.setAlignment(Pos.CENTER);
        cimaVBox.setSpacing(18);


        origemLabel = new Label("Selecione a sua origem");
        origemLabel.setFont( new Font("Arial", 20) );
        origemLabel.setAlignment(Pos.CENTER);
        origemTField = new TextField();
        origemTField.setPromptText("Origem");
        //origemTField.setText("Serpins");
        origemTField.setMinWidth(150);
        origemTField.setMaxWidth(150);
        TextFields.bindAutoCompletion(origemTField, appManager.getRuas());
        origemVBox = new VBox(origemLabel, origemTField);
        origemVBox.setPadding(new Insets(40, 0, 0, 0));
        origemVBox.setSpacing(10);
        origemVBox.setAlignment(Pos.CENTER);

        destinoLabel = new Label("Selecione o seu destino");
        destinoLabel.setFont( new Font("Arial", 20) );
        destinoLabel.setAlignment(Pos.CENTER);
        destinoTField = new TextField();
        destinoTField.setPromptText("Destino");
        //destinoTField.setText("Coimbra");
        destinoTField.setMinWidth(150);
        destinoTField.setMaxWidth(150);
        TextFields.bindAutoCompletion(destinoTField, appManager.getRuas());
        destinoVBox = new VBox(destinoLabel, destinoTField);
        destinoVBox.setPadding(new Insets(30, 0, 0, 0));
        destinoVBox.setSpacing(10);
        destinoVBox.setAlignment(Pos.CENTER);

        linhaMiddle = new Line(0, 0, UtilsUI.appWidthSize, 0);

        escolherDiasCBox = new ComboBox();
        escolherDiasCBox.setPromptText("Escolha o(s) dia(s)");
        escolherDiasCBox.getItems().add(dataHora.getDate(0));
        escolherDiasCBox.getItems().add(dataHora.getDate(1));
        escolherDiasCBox.getItems().add(dataHora.getDate(1));

        escolherdiasVBox = new VBox(linhaMiddle, escolherDiasCBox);
        escolherdiasVBox.setAlignment(Pos.CENTER);
        escolherdiasVBox.setSpacing(30);
        escolherdiasVBox.setPadding(new Insets(30, 0, 90, 0));

        confirmar = new Button("Confirmar");
        confirmar.setAlignment(Pos.CENTER);
        confirmar.setPadding(new Insets(5, 20, 5, 20));


        linhaBottom = new Line(0, 0, UtilsUI.appWidthSize, 0);

        recolhaDados = new Button("Origem/Destino");
        recolhaDados.setPadding(new Insets(5, 20, 5, 20));
        recolhaDados.setDisable(true);
        notificacoes = new Button("Notificações");
        notificacoes.setPadding(new Insets(5, 20, 5, 20));
        conjunto = new HBox(recolhaDados, notificacoes);
        conjunto.setAlignment(Pos.CENTER);
        //conjunto.setPadding(new Insets(40, 0, 0, 0));
        conjunto.setSpacing(50);

        principalTopVBox = new VBox();
        principalTopVBox.getChildren().addAll(cimaVBox, origemVBox, destinoVBox, escolherdiasVBox, confirmar);
        principalTopVBox.setAlignment(Pos.TOP_CENTER);
        //principalVBox.setPadding( new Insets(50, 0, 50, 0) );
        /*principalTopVBox.setMinWidth(UtilsUI.appWidthSize);
        principalTopVBox.setMaxWidth(UtilsUI.appWidthSize);*/
        principalTopVBox.setMinHeight(UtilsUI.appHeightSize - 10*UtilsUI.appHeightSize);
        principalTopVBox.setMaxHeight(UtilsUI.appHeightSize - 10*UtilsUI.appHeightSize);

        principalBottomVBox = new VBox();
        principalBottomVBox.getChildren().addAll(linhaBottom, conjunto);
        principalBottomVBox.setAlignment(Pos.BOTTOM_CENTER);
        principalBottomVBox.setPadding( new Insets(60, 0, 30, 0) );
        principalBottomVBox.setSpacing(30);
        /*principalBottomVBox.setMinWidth(UtilsUI.appWidthSize);
        principalBottomVBox.setMaxWidth(UtilsUI.appWidthSize);*/
        principalBottomVBox.setMinHeight(UtilsUI.appHeightSize - 90*UtilsUI.appHeightSize);
        principalBottomVBox.setMaxHeight(UtilsUI.appHeightSize - 90*UtilsUI.appHeightSize);

        this.setTop(principalTopVBox);
        this.setBottom(principalBottomVBox);

    }

    private void registerHandlers() {

        appManager.addPropertyChangeListener(evt -> {
            update();
        });

        voltar.setOnAction(event -> {
            appManager.voltar();
        });

        confirmar.setOnAction(event -> {

            String origemAux = null, destinoAux = null, diaAux = null;
            boolean encontrou = false;

            try {
                origemAux = origemTField.getText();
                for (int i = 0; i < appManager.getRuas().size(); i++) {
                    if (origemAux.equals(appManager.getRuas().get(i)))
                        encontrou = true;
                }

                if (!encontrou) {
                    origemAux = null;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ALERTA");
                    alert.setHeaderText("Origem indicada não existe...");
                    alert.showAndWait();
                }
                else
                    encontrou = false;
            }
            catch (Exception e) {

            }

            try {
                destinoAux = destinoTField.getText();
                for (int i = 0; i < appManager.getRuas().size(); i++) {
                    if (destinoAux.equals(appManager.getRuas().get(i)))
                        encontrou = true;
                }

                if (!encontrou) {
                    destinoAux = null;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ALERTA");
                    alert.setHeaderText("Destino indicado não existe...");
                    alert.showAndWait();
                }
            }
            catch (Exception e) {

            }

            try {
                diaAux = escolherDiasCBox.getValue().toString();
            }
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ALERTA");
                alert.setHeaderText("Indique um dia...");
                alert.showAndWait();
            }


            /*origemTField.clear();
            destinoTField.clear();*/
            /*
            escolherDiasCBox.getSelectionModel().clearSelection();
            escolherDiasCBox.setPromptText("Escolha o(s) dia(s)");
            */
            if (origemAux != null && destinoAux != null && diaAux != null) {
                appManager.autocarrosDisponiveis(origemAux, destinoAux, diaAux);
            }

        });

        notificacoes.setOnAction(event -> {
            appManager.notificacoes();
        });

    }

    private void update() {
        if(appManager.getState() != AppState.RECOLHA_DADOS_STATE){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        horaSistema.setText(dataHora.getTime());
    }
}
