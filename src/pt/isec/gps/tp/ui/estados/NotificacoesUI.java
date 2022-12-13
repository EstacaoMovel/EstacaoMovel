package pt.isec.gps.tp.ui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.modelo.dados.Notificacao;
import pt.isec.gps.tp.modelo.fsm.AppState;
import pt.isec.gps.tp.utils.DataHora;
import pt.isec.gps.tp.utils.DisponiveisContent;
import pt.isec.gps.tp.utils.NotificacoesContent;
import pt.isec.gps.tp.utils.UtilsUI;

import java.awt.*;

public class NotificacoesUI extends BorderPane {
    private AppManager appManager;
    private DataHora dataHora = new DataHora();
    private UtilsUI utils = new UtilsUI();

    private Label horaSistema;
    private HBox horaSistHBox;
    private Button voltar;
    private HBox voltarHBox;
    private Label titulo;
    Line linhaTop;
    private VBox cimaVBox;

    private Label subtitulo, indicacao;
    private HBox subtituloHBox, indicacaoHBox;
    Boolean passou=false;

    private TableView<Notificacao> tabela;
    private TableColumn nomeTable, origemTable, destinoTable, horaPartidaTable, horaChegadaTable, notificacaoTable;
    private VBox tabelaVBox;


    Line linhaBottom;
    private Button recolhaDados, notificacoes;
    private HBox conjunto;

    private VBox principalTopVBox, principalBottomVBox;

    public NotificacoesUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        if (passou) {
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
            voltar.setStyle("-fx-background-color: transparent; -fx-cursor: hand; -fx-text-fill: white");
            voltar.setDisable(true);
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


            subtitulo = new Label("Notificações");
            subtitulo.setFont( new Font("Sans Serif", 20) );
            subtituloHBox = new HBox(subtitulo);
            subtituloHBox.setAlignment(Pos.CENTER);
            subtituloHBox.setPadding(new Insets(10, 0, 0, 0));
            indicacao = new Label("Autocarros com notificações ativas:");
            indicacaoHBox = new HBox(indicacao);
            indicacaoHBox.setAlignment(Pos.CENTER_LEFT);
            indicacaoHBox.setPadding(new Insets(25, 0, 20, 20));



            tabela = new TableView<>();
            tabela.setEditable(false);
            tabela.setMinHeight(265);
            //tabela.setMaxHeight(280);

            nomeTable = new TableColumn("Nome");
            nomeTable.setMaxWidth(UtilsUI.appWidthSize * 0.235);
            nomeTable.setCellValueFactory(
                    new PropertyValueFactory<Notificacao, String>("nome")
            );
            nomeTable.setStyle("-fx-alignment: CENTER;");
            origemTable = new TableColumn("Origem");
            origemTable.setMaxWidth(UtilsUI.appWidthSize * 0.19);
            origemTable.setCellValueFactory(
                    new PropertyValueFactory<Notificacao, String>("origem")
            );

            origemTable.setStyle("-fx-alignment: CENTER;");
            destinoTable = new TableColumn("Destino");
            destinoTable.setMaxWidth(UtilsUI.appWidthSize * 0.19);
            destinoTable.setCellValueFactory(
                    new PropertyValueFactory<Notificacao, String>("destino")
            );
            destinoTable.setStyle("-fx-alignment: CENTER;");
            horaPartidaTable = new TableColumn("Hora_P");
            horaPartidaTable.setMaxWidth(UtilsUI.appWidthSize * 0.15);
            horaPartidaTable.setCellValueFactory(
                    new PropertyValueFactory<Notificacao, String>("partida")
            );
            horaPartidaTable.setStyle("-fx-alignment: CENTER;");
            horaChegadaTable = new TableColumn("Hora_C");
            horaChegadaTable.setMaxWidth(UtilsUI.appWidthSize * 0.15);
            horaChegadaTable.setCellValueFactory(
                    new PropertyValueFactory<Notificacao, String>("horaC")
            );
            horaChegadaTable.setStyle("-fx-alignment: CENTER;");
            notificacaoTable = new TableColumn("Not.");
            notificacaoTable.setMaxWidth(UtilsUI.appWidthSize * 0.1);
            notificacaoTable.setCellValueFactory(
                    new PropertyValueFactory<Notificacao, ImageView>("notifImage")
            );
            notificacaoTable.setStyle("-fx-alignment: CENTER;");

            tabela.setItems(utils.getTabelaNotificacoes(appManager.getNotificacoes()));
            tabela.getColumns().addAll(nomeTable, origemTable, destinoTable, horaPartidaTable, horaChegadaTable, notificacaoTable);

            tabelaVBox = new VBox(tabela);
            tabelaVBox.setAlignment(Pos.CENTER);
            tabelaVBox.setMinWidth(UtilsUI.appWidthSize +10);
            tabelaVBox.setMaxWidth(UtilsUI.appWidthSize +10);
            tabelaVBox.setPadding(new Insets(10, 0, 0, 0));





            linhaBottom = new Line(0, 0, UtilsUI.appWidthSize, 0);

            recolhaDados = new Button("Origem/Destino");
            recolhaDados.setPadding(new Insets(5, 20, 5, 20));
            notificacoes = new Button("Notificações");
            notificacoes.setPadding(new Insets(5, 20, 5, 20));
            notificacoes.setDisable(true);
            conjunto = new HBox(recolhaDados, notificacoes);
            conjunto.setAlignment(Pos.CENTER);
            //conjunto.setPadding(new Insets(40, 0, 0, 0));
            conjunto.setSpacing(50);

            principalTopVBox = new VBox();
            principalTopVBox.getChildren().addAll(cimaVBox, subtituloHBox, indicacaoHBox, tabelaVBox);
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

    }

    private void registerHandlers() {

        appManager.addPropertyChangeListener(evt -> {
            update();
        });
        if (passou) {
            recolhaDados.setOnAction(event -> {
                appManager.recolherDados();
            });
            tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

                System.out.println(MouseInfo.getPointerInfo().getLocation().getX());
                double mousePositionX = MouseInfo.getPointerInfo().getLocation().getX();

                if (mousePositionX > 1100 && mousePositionX < 1300) {
                    if (oldSelection == null) {
                        System.out.println("Autocarro1: " + newSelection.toString());

                    }
                    else System.out.println("Autocarro1: " + newSelection.toString() + "\nAutocarro2: "+oldSelection.toString());

                    appManager.changeNotificationStatus(newSelection);
                    update();

                }

            });


        }


        
    }

    private void update() {
        if(appManager.getState() != AppState.NOTIFICACOES_STATE){
            this.setVisible(false);
            passou = false;
            return;
        }
        passou = true;
        createViews();
        registerHandlers();

        this.setVisible(true);

        horaSistema.setText(dataHora.getTime());
    }
}
