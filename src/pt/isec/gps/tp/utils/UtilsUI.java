package pt.isec.gps.tp.utils;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.isec.gps.tp.modelo.dados.Autocarro;
import pt.isec.gps.tp.modelo.dados.Notificacao;

import java.util.ArrayList;
import java.util.Arrays;

public class UtilsUI {
    public static final double appWidthSize = 337.50;
    public static final double appHeightSize = 600;

    public static final String filenameOpen="aberto.png";
    public static final String filenameClosed="fechado.png";

    private String origem = "ISEC", destino = "Baixa";
    private ArrayList<String> categorias = new ArrayList<>(Arrays.asList("SMTUC", "Transdev"));

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getCategoria(int pos) {
        return categorias.get(pos);
    }

    public ObservableList<Autocarro> getTabelaDisponiveis(ArrayList<Autocarro> autocarros) {

        return FXCollections.observableList(autocarros);
    }

    public ObservableList<DetalhesContent> getTabelaDetalhes(Autocarro c) {
        return FXCollections.observableArrayList(
                new DetalhesContent("Origem", c.getOrigem()),
                new DetalhesContent("Destino", c.getDestino()),
                new DetalhesContent("Hora de partida fixada", c.getHoraPartida(c.getOrigem())),
                new DetalhesContent("Hora de chegada fixada", c.getHoraChegada(c.getDestino())),
                new DetalhesContent("Tempo do autocarro à origem", c.getEstimatedTime(c.getOrigem())),
                new DetalhesContent("Hora prevista (origem)", c.getHoraPrevistaPartida()),
                new DetalhesContent("Hora prevista (chegada)", c.getHoraPrevistaChegada()),
                new DetalhesContent("Preço", c.getPreco()),
                new DetalhesContent("Lotação", c.getLotacao()+"/20"),
                new DetalhesContent("Estado", c.getEstado())
        );
    }

    public ObservableList<Notificacao> getTabelaNotificacoes(ArrayList<Notificacao> notificacaos) {
        return FXCollections.observableList(notificacaos);
    }
}
