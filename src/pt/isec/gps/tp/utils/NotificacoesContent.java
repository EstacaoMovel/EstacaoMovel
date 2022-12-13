package pt.isec.gps.tp.utils;

import javafx.beans.property.SimpleStringProperty;

public class NotificacoesContent {
    private SimpleStringProperty nome;
    private SimpleStringProperty origem;
    private SimpleStringProperty destino;
    private SimpleStringProperty horaPartida;
    private SimpleStringProperty horaChegada;
    private SimpleStringProperty notificacao;

    public NotificacoesContent(String nome, String origem, String destino, String horaPartida, String horaChegada, String notificacao) {
        this.nome = new SimpleStringProperty(nome);
        this.origem = new SimpleStringProperty(origem);
        this.destino = new SimpleStringProperty(destino);
        this.horaPartida = new SimpleStringProperty(horaPartida);
        this.horaChegada = new SimpleStringProperty(horaChegada);
        this.notificacao = new SimpleStringProperty(notificacao);
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getOrigem() {
        return origem.get();
    }

    public SimpleStringProperty origemProperty() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem.set(origem);
    }

    public String getDestino() {
        return destino.get();
    }

    public SimpleStringProperty destinoProperty() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino.set(destino);
    }

    public String getHoraPartida() {
        return horaPartida.get();
    }

    public SimpleStringProperty horaPartidaProperty() {
        return horaPartida;
    }

    public void setHoraPartida(String horaPartida) {
        this.horaPartida.set(horaPartida);
    }

    public String getHoraChegada() {
        return horaChegada.get();
    }

    public SimpleStringProperty horaChegadaProperty() {
        return horaChegada;
    }

    public void setHoraChegada(String horaChegada) {
        this.horaChegada.set(horaChegada);
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
