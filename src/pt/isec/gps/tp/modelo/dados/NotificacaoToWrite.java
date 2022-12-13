package pt.isec.gps.tp.modelo.dados;

import javafx.scene.image.ImageView;
import pt.isec.gps.tp.ui.resources.ImageManager;
import pt.isec.gps.tp.utils.UtilsUI;

import java.io.Serializable;

public class NotificacaoToWrite implements Serializable {
    static final long serialVersionUID = 1L;

    String nome;
    String origem;
    String destino;
    String partida;

    String horaC;
    Boolean notificacao;
    Boolean ativo;
    int autocarroID;


    public NotificacaoToWrite(int autocarroID, String nome, String origem, String destino, String hora_p, String horaC, Boolean notificacao, Boolean ativo) {
        this.autocarroID = autocarroID;
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.partida = hora_p;
        this.horaC = horaC;
        this.notificacao = notificacao;
        this.ativo = ativo;

    }



    @Override
    public String toString() {
        return "Notificacao{" +
                "nome='" + nome + '\'' +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", partida='" + partida + '\'' +
                ", horaChegada='" + horaC + '\'' +
                ", notificacao=" + notificacao +
                ", ativo=" + ativo +
                '}';
    }

    public int getAutocarroID() {
        return autocarroID;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPartida() {
        return partida;
    }

    public void setHora_p(String hora_p) {
        this.partida = hora_p;
    }

    public String getHoraC() {
        return horaC;
    }

    public void setHoraC(String horaC) {
        this.horaC = horaC;
    }

    public Boolean getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(Boolean notificacao) {
        this.notificacao = notificacao;
    }


}
