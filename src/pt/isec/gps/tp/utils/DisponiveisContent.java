package pt.isec.gps.tp.utils;

import javafx.beans.property.SimpleStringProperty;

public class DisponiveisContent {
    private SimpleStringProperty autocarro;
    private SimpleStringProperty preco;
    private SimpleStringProperty partida;
    private SimpleStringProperty chegada;
    private SimpleStringProperty notificacao;

    public DisponiveisContent(String autocarro, String preco, String partida, String chegada, String notificacao) {
        this.autocarro = new SimpleStringProperty(autocarro);
        this.preco = new SimpleStringProperty(preco);
        this.partida = new SimpleStringProperty(partida);
        this.chegada = new SimpleStringProperty(chegada);
        this.notificacao = new SimpleStringProperty(notificacao);
    }

    @Override
    public String toString() {
        return "DisponiveisContent{" +
                "autocarro=" + autocarro +
                ", preco=" + preco +
                ", partida=" + partida +
                ", chegada=" + chegada +
                ", notificacao=" + notificacao +
                '}';
    }

    public String getAutocarro() {
        return autocarro.get();
    }

    public SimpleStringProperty autocarroProperty() {
        return autocarro;
    }

    public void setAutocarro(String autocarro) {
        this.autocarro.set(autocarro);
    }

    public String getPreco() {
        return preco.get();
    }

    public SimpleStringProperty precoProperty() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco.set(preco);
    }

    public String getPartida() {
        return partida.get();
    }

    public SimpleStringProperty partidaProperty() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida.set(partida);
    }

    public String getChegada() {
        return chegada.get();
    }

    public SimpleStringProperty chegadaProperty() {
        return chegada;
    }

    public void setChegada(String chegada) {
        this.chegada.set(chegada);
    }

    public String getNotificacao() {
        return notificacao.get();
    }

    public SimpleStringProperty notificacaoProperty() {
        return notificacao;
    }

    public void setNotificacao(String notificacao) {
        this.notificacao.set(notificacao);
    }
}
