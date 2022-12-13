package pt.isec.gps.tp.ui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import pt.isec.gps.tp.modelo.dados.Autocarro;
import pt.isec.gps.tp.utils.*;
import pt.isec.gps.tp.modelo.AppManager;
import pt.isec.gps.tp.modelo.fsm.AppState;

import java.awt.*;


public class AutocarrosDisponiveisUI extends BorderPane {

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

    private Label origemDestinoLabel;
    private HBox origemDestinoHBox;

    private Label categoria, horario;
    private ComboBox escolherCategoriaCBox;
    private TextField horaPartidaTField, horaChegadaTField;
    private Button aplicarFiltros;
    private VBox categoriaFiltroVBox, horaFiltroVBox, aplicarFiltrosVBox;
    private HBox conjuntoFiltrosHBox;

    Boolean passou = false;
    private TableView<Autocarro> tabela;
    private TableColumn autocarroTable, precoTable, partidaTable, chegadaTable, notificacaoTable;
    private VBox tabelaVBox;


    Line linhaBottom;
    private Button recolhaDados, notificacoes;
    private HBox conjunto;

    private VBox principalTopVBox, principalBottomVBox;


    public AutocarrosDisponiveisUI(AppManager appManager) {
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


            origemDestinoLabel = new Label();
            origemDestinoLabel.setText("Origem: " + appManager.getDestino() + " - Destino: " + appManager.getOrigem());
            origemDestinoLabel.setFont(new Font("Arial", 18));
            origemDestinoHBox = new HBox(origemDestinoLabel);
            origemDestinoHBox.setAlignment(Pos.CENTER_LEFT);
            origemDestinoHBox.setPadding(new Insets(16, 0, 0, 21));


            categoria = new Label("Categoria");
            escolherCategoriaCBox = new ComboBox<>();
            escolherCategoriaCBox.setPromptText("Tipo");
            escolherCategoriaCBox.getItems().add(utils.getCategoria(0));
            escolherCategoriaCBox.getItems().add(utils.getCategoria(1));
            escolherCategoriaCBox.getItems().add("TODOS");
            escolherCategoriaCBox.setMinWidth(UtilsUI.appWidthSize * (2.0/5));
            escolherCategoriaCBox.setMaxWidth(UtilsUI.appWidthSize * (2.0/5));
            categoriaFiltroVBox = new VBox(categoria, escolherCategoriaCBox);
            categoriaFiltroVBox.setAlignment(Pos.TOP_LEFT);
            categoriaFiltroVBox.setMinWidth(UtilsUI.appWidthSize * (2.0/5));
            categoriaFiltroVBox.setMaxWidth(UtilsUI.appWidthSize * (2.0/5));
            categoriaFiltroVBox.setSpacing(5);

            horario = new Label("Horário");
            horaPartidaTField = new TextField();
            horaPartidaTField.setPromptText("Partida (HH:mm):");
            horaChegadaTField = new TextField();
            horaChegadaTField.setPromptText("Chegada (HH:mm):");
            horaFiltroVBox = new VBox(horario, horaPartidaTField, horaChegadaTField);
            horaFiltroVBox.setAlignment(Pos.TOP_LEFT);
            horaFiltroVBox.setMinWidth(UtilsUI.appWidthSize * (2.0/5));
            horaFiltroVBox.setMaxWidth(UtilsUI.appWidthSize * (2.0/5));
            horaFiltroVBox.setSpacing(5);

            aplicarFiltros = new Button("Aplicar");
            aplicarFiltros.setMinWidth(50);
            aplicarFiltros.setMaxWidth(50);
            aplicarFiltros.setMinHeight(30);
            aplicarFiltros.setMaxHeight(30);
            aplicarFiltros.setPadding(new Insets(0));
            aplicarFiltros.setAlignment(Pos.CENTER);
            aplicarFiltrosVBox = new VBox(aplicarFiltros);
            aplicarFiltrosVBox.setAlignment(Pos.CENTER);
            /*aplicarFiltrosVBox.setMinWidth(UtilsUI.appWidthSize * (1.0/5));
            aplicarFiltrosVBox.setMaxWidth(UtilsUI.appWidthSize * (1.0/5));*/
            aplicarFiltrosVBox.setPadding(new Insets(20, 0, 0, 0));

            conjuntoFiltrosHBox = new HBox(categoriaFiltroVBox, horaFiltroVBox, aplicarFiltrosVBox);
            conjuntoFiltrosHBox.setAlignment(Pos.CENTER);
            conjuntoFiltrosHBox.setPadding(new Insets(20, 0, 0, 0));
            conjuntoFiltrosHBox.setSpacing(10);






            tabela = new TableView<Autocarro>();
            tabela.setEditable(false);
            tabela.setMinHeight(265);
            //tabela.setMaxHeight(280);

            autocarroTable = new TableColumn("Autocarro");
            autocarroTable.setMinWidth(UtilsUI.appWidthSize * 0.30);
            autocarroTable.setMaxWidth(UtilsUI.appWidthSize * 0.30);
            autocarroTable.setCellValueFactory(
                    new PropertyValueFactory<Autocarro, String>("nome_autocarro")
            );
            autocarroTable.setStyle("-fx-alignment: CENTER;");
            precoTable = new TableColumn("Preço");
            precoTable.setMinWidth(UtilsUI.appWidthSize * 0.20);
            precoTable.setMaxWidth(UtilsUI.appWidthSize * 0.20);
            precoTable.setCellValueFactory(
                    new PropertyValueFactory<Autocarro, String>("preco")
            );
            precoTable.setStyle("-fx-alignment: CENTER;");
            partidaTable = new TableColumn("Partida");
            partidaTable.setMinWidth(UtilsUI.appWidthSize * 0.205);
            partidaTable.setMaxWidth(UtilsUI.appWidthSize * 0.205);
            partidaTable.setCellValueFactory(
                    new PropertyValueFactory<Autocarro, String>("horaPartida")
            );
            partidaTable.setStyle("-fx-alignment: CENTER;");
            chegadaTable = new TableColumn("Chegada");
            chegadaTable.setMinWidth(UtilsUI.appWidthSize * 0.205);
            chegadaTable.setMaxWidth(UtilsUI.appWidthSize * 0.205);
            chegadaTable.setCellValueFactory(
                    new PropertyValueFactory<Autocarro, String>("horaChegada")
            );
            chegadaTable.setStyle("-fx-alignment: CENTER;");
            notificacaoTable = new TableColumn("Sino");
            //notificacaoTable.setGraphic(new ImageView(new Image("Music_Icon.jpg")));
            notificacaoTable.setMinWidth(UtilsUI.appWidthSize * 0.11);
            notificacaoTable.setMaxWidth(UtilsUI.appWidthSize * 0.11);
            notificacaoTable.setCellValueFactory(
                    new PropertyValueFactory<Autocarro, Button>("notificacao")
            );
            notificacaoTable.setStyle("-fx-alignment: CENTER;");

            tabela.setItems(utils.getTabelaDisponiveis(appManager.getAutocarrosFiltros()));
            tabela.getColumns().addAll(autocarroTable, precoTable, partidaTable, chegadaTable, notificacaoTable);

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
            conjunto = new HBox(recolhaDados, notificacoes);
            conjunto.setAlignment(Pos.CENTER);
            //conjunto.setPadding(new Insets(40, 0, 0, 0));
            conjunto.setSpacing(50);

            principalTopVBox = new VBox();
            principalTopVBox.getChildren().addAll(cimaVBox, origemDestinoHBox, conjuntoFiltrosHBox, tabelaVBox);
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
            tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                System.out.println(MouseInfo.getPointerInfo().getLocation().getX());
                double mousePositionX = MouseInfo.getPointerInfo().getLocation().getX();

                if (mousePositionX > 800 && mousePositionX < 1100) {
                    if (oldSelection == null) {
                        System.out.println("Autocarro1: " + newSelection.toString());

                    }
                    else System.out.println("Autocarro1: " + newSelection.toString() + "\nAutocarro2: "+oldSelection.toString());

                    appManager.detalhesAutocarro(newSelection);
                }
                else if (mousePositionX > 1112.0 && mousePositionX < 1150) {
                   update();
                }

            });

            aplicarFiltros.setOnAction(event -> {
                System.out.println("CATEGORIA: " + escolherCategoriaCBox.getValue());
                System.out.println("HORA_P: " + horaPartidaTField.getText());
                System.out.println("HORA_C: " + horaChegadaTField.getText());
                if (escolherCategoriaCBox.getValue() == null) {
                    tabela.setItems(utils.getTabelaDisponiveis(appManager.getAutocarrosComFiltro(
                            "TODOS",
                            horaPartidaTField.getText(),
                            horaChegadaTField.getText()
                    )));

                }else {
                    tabela.setItems(utils.getTabelaDisponiveis(appManager.getAutocarrosComFiltro(
                            escolherCategoriaCBox.getValue().toString(),
                            horaPartidaTField.getText(),
                            horaChegadaTField.getText()
                    )));
                }


            });

            voltar.setOnAction(event -> {
                appManager.voltar();
            });

            notificacoes.setOnAction(event -> {
                appManager.notificacoes();
            });

            recolhaDados.setOnAction(event -> {
                appManager.recolherDados();
            });
        }


    }

    private void update() {
        if(appManager.getState() != AppState.AUTOCARROS_DISPONIVEIS_STATE){
            this.setVisible(false);
            passou = false;
            return;
        }
        passou = true;
        createViews();
        registerHandlers();
        this.setVisible(true);
        if (horaSistema != null)
            horaSistema.setText(dataHora.getTime());
    }
}
