package pt.isec.gps.tp.ui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.modelo.dados.Autocarro;
import pt.isec.gps.tp.modelo.fsm.AppState;
import pt.isec.gps.tp.utils.DataHora;
import pt.isec.gps.tp.utils.DetalhesContent;
import pt.isec.gps.tp.utils.DisponiveisContent;
import pt.isec.gps.tp.utils.UtilsUI;

public class DetalhesAutocarroUI extends BorderPane {
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

    private Label subtitulo;
    private HBox subtituloHBox;

    private TableView<DetalhesContent> tabela;
    private TableColumn topicoTable, detalheTable;
    private VBox tabelaVBox;

    private Label notificaoAlerta;
    private Button ativarNotificacao;
    private HBox conjuntoNotificacao;


    Line linhaBottom;
    private Button recolhaDados, notificacoes;
    private HBox conjunto;
    Autocarro bus = null;
    Boolean passou = false;

    private VBox principalTopVBox, principalBottomVBox;

    public DetalhesAutocarroUI(AppManager appManager) {
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


            subtitulo = new Label("Detalhes do autocarro");
            subtitulo.setAlignment(Pos.CENTER);
            subtitulo.setFont( new Font("Sans Serif", 20) );
            subtituloHBox = new HBox(subtitulo);
            subtituloHBox.setAlignment(Pos.CENTER);
            subtituloHBox.setPadding(new Insets(30, 0, 25, 0));



            tabela = new TableView<DetalhesContent>();
            tabela.setEditable(false);
            tabela.setMinHeight(265);
            //tabela.setMaxHeight(280);

            topicoTable = new TableColumn("Autocarro");
            topicoTable.setMinWidth(UtilsUI.appWidthSize * 0.70);
            topicoTable.setMaxWidth(UtilsUI.appWidthSize * 0.70);
            topicoTable.setCellValueFactory(
                    new PropertyValueFactory<DisponiveisContent, String>("topico")
            );
            topicoTable.setStyle("-fx-alignment: CENTER-LEFT;");
            detalheTable = new TableColumn(appManager.getAutocarro().getNome_autocarro());
            detalheTable.setMinWidth(UtilsUI.appWidthSize * 0.30);
            detalheTable.setMaxWidth(UtilsUI.appWidthSize * 0.30);
            detalheTable.setCellValueFactory(
                    new PropertyValueFactory<DisponiveisContent, String>("detalheTopico")
            );
            detalheTable.setStyle("-fx-alignment: CENTER;");

            tabela.setItems(utils.getTabelaDetalhes(bus));
            tabela.getColumns().addAll(topicoTable, detalheTable);

            tabelaVBox = new VBox(tabela);
            tabelaVBox.setAlignment(Pos.CENTER);
            tabelaVBox.setMinWidth(UtilsUI.appWidthSize +10);
            tabelaVBox.setMaxWidth(UtilsUI.appWidthSize +10);
            tabelaVBox.setPadding(new Insets(10, 0, 0, 0));


            notificaoAlerta = new Label("Notificação de alerta");
            notificaoAlerta.setAlignment(Pos.CENTER_RIGHT);
            ativarNotificacao = new Button(appManager.isInNotifications(bus));
            //ativarNotificacao = new Button("x");
            //ativarNotificacao.setFont(new Font("Arial", 10));
            ativarNotificacao.setMinWidth(20);
            ativarNotificacao.setMaxWidth(20);
            ativarNotificacao.setMinHeight(20);
            ativarNotificacao.setMaxHeight(20);
            ativarNotificacao.setPadding(new Insets(0));
            ativarNotificacao.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK");
            conjuntoNotificacao = new HBox(notificaoAlerta, ativarNotificacao);
            conjuntoNotificacao.setAlignment(Pos.CENTER_RIGHT);
            conjuntoNotificacao.setSpacing(5);
            conjuntoNotificacao.setPadding(new Insets(10, 20, 0, 0));



            linhaBottom = new Line(0, 0, UtilsUI.appWidthSize, 0);

            recolhaDados = new Button("Origem/Destino");
            recolhaDados.setPadding(new Insets(5, 20, 5, 20));
            notificacoes = new Button("Notificações");
            notificacoes.setPadding(new Insets(5, 20, 5, 20));
            conjunto = new HBox(recolhaDados, notificacoes);
            conjunto.setAlignment(Pos.CENTER);
            //conjunto.setPadding(new Insets(40, 0, 0, 0));
            conjunto.setSpacing(50);

            principalTopVBox = new VBox();
            principalTopVBox.getChildren().addAll(cimaVBox, subtituloHBox, tabelaVBox, conjuntoNotificacao);
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
            voltar.setOnAction(event -> {
                appManager.voltar();
            });

            notificacoes.setOnAction(event -> {
                appManager.notificacoes();
            });

            recolhaDados.setOnAction(event -> {
                appManager.recolherDados();
            });

            ativarNotificacao.setOnAction(event -> {

                System.out.println("Cliquei no botão «Ativar NOTIFICAÇÃO»");

                appManager.handleNotification(bus, ativarNotificacao.getText());

                ativarNotificacao.setText(appManager.isInNotifications(bus));
            });
        }

        
    }

    private void update() {
        if(appManager.getState() != AppState.DETALHES_AUTOCARRO_STATE){
            this.setVisible(false);
            passou = false;
            return;
        }
        bus = appManager.getAutocarro();
        passou = true;
        System.out.println("Hello1");
        createViews();
        registerHandlers();
        this.setVisible(true);
        horaSistema.setText(dataHora.getTime());

    }
}
