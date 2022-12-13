package pt.isec.gps.tp.modelo.dados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import pt.isec.gps.tp.utils.Api;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Autocarro {
    private AppData app;
    private int id_autocarro;
    private String nome_autocarro;
    private int lotacao;
    private boolean estado;
    private boolean notific;
    private String empresa;
    private String latitude;
    private String longitude;
    private ArrayList<Paragem> trajeto;
    private Float preco;
    private String origem;
    private String destino;
    private  Button notificacao;
    private String horaPartida;
    private String horaChegada;
    private int timer;
    public String getEstado() {
        return !estado ?"Não Ativo":"Ativo";
    }

    public Boolean getBestado() {
        return estado;
    }
    public Autocarro(int id_autocarro, String nome_autocarro, int lotacao, boolean estado, boolean notific, String empresa, String latitude, String longitude) {
        this.id_autocarro = id_autocarro;
        this.nome_autocarro = nome_autocarro;
        this.lotacao = lotacao;
        this.estado = estado;
        this.notific = notific;
        this.empresa = empresa;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trajeto = new ArrayList<>();
        this.destino = "";
        this.origem = "";
        this.preco = 0.0f;
        changeRedCustom();
        app = null;
        this.horaChegada = "";
        this.horaPartida = "";
        
    }

    public String getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }

    public void setHoraChegada(String horaChegada) {
        this.horaChegada = horaChegada;
    }

    public void setApp(AppData app, int contador) {
        this.app = app;
        //System.out.println("CONTADOR: " + contador);
        if (contador %2 == 0) {
            notificacao.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: BLACK");
            //notificacao.setStyle("-fx-border-color: BLACK");
        }
        else {
            notificacao.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK");
            //notificacao.setStyle("-fx-border-color: BLACK");
        }
    }

    public Button getNotificacao() {
        return notificacao;
    }

    public String getHoraPrevistaPartida() {
        int horaPartidaAux, minutoPartidaAux;
        String[] tokensPartida;
        tokensPartida = horaPartida.split(":");
        horaPartidaAux = Integer.parseInt(tokensPartida[0]);
        minutoPartidaAux = Integer.parseInt(tokensPartida[1]);

        if (minutoPartidaAux + timer <=59) {
            minutoPartidaAux+=timer;
        }
        else {
            horaPartidaAux++;
            int auxMinuto = 60 - minutoPartidaAux;
            int auxTimer = timer - auxMinuto;
            minutoPartidaAux = auxTimer;

        }

        System.out.println("hora_partida: " + horaPartidaAux);
        System.out.println("minuto_partida: " + minutoPartidaAux);

        String horaPartidaAuxS, minutoPartidaAuxS;
        horaPartidaAuxS = Integer.toString(horaPartidaAux);
        minutoPartidaAuxS = Integer.toString(minutoPartidaAux);

        if (horaPartidaAux < 10)
            horaPartidaAuxS = "0" + horaPartidaAuxS;

        String horaPartidaFinal = horaPartidaAuxS.concat(":").concat(minutoPartidaAuxS);

        return horaPartidaFinal;
    }
    public String getHoraPrevistaChegada() {
        int horaChegadaAux, minutoChegadaAux;
        String[] tokensChegada;
        tokensChegada = horaChegada.split(":");
        horaChegadaAux = Integer.parseInt(tokensChegada[0]);
        minutoChegadaAux = Integer.parseInt(tokensChegada[1]);

        if (minutoChegadaAux + timer <=59) {
            minutoChegadaAux+=timer;
        }
        else {
            horaChegadaAux++;
            int auxMinuto = 60 - minutoChegadaAux;
            int auxTimer = timer - auxMinuto;
            minutoChegadaAux = auxTimer;

        }

        System.out.println("hora_chegada: " + horaChegadaAux);
        System.out.println("minuto_chegada: " + minutoChegadaAux);

        String horaChegadaAuxS, minutoChegadaAuxS;
        horaChegadaAuxS = Integer.toString(horaChegadaAux);
        minutoChegadaAuxS = Integer.toString(minutoChegadaAux);

        if (horaChegadaAux < 10)
            horaChegadaAuxS = "0" + horaChegadaAuxS;

        String horaChegadaFinal = horaChegadaAuxS.concat(":").concat(minutoChegadaAuxS);

        return horaChegadaFinal;
    }
    public String getHoraChegada() {
        for(Paragem p: trajeto) {
            if (p.getNome().equalsIgnoreCase(destino)) {
                return p.getHora_chegada();
            }
        }
        return "00:00";
    }
    public String getEstimatedTime(String destino) {
        System.out.println("ESTIMADOOO: O=" + this.origem + "; D=" + this.destino);
        Api helper = new Api();
        String e = ""+latitude+","+longitude+"";
        //e = "Lisboa";
        System.out.println("First DEBUG: " + e);
        String aux2 = destino;
        String aux= "Rua+de+Coimbra,Coimbra";
        aux2="Rua da Sofia,Coimbra";
        aux2 = aux2.replaceAll(" ","+");
        System.out.println(aux2);

        String origemAux, destinoAux;
        origemAux = this.origem+",Coimbra";
        origemAux = origemAux.replaceAll(" ","+");
        destinoAux = this.destino+",Coimbra";
        destinoAux = destinoAux.replaceAll(" ","+");
        System.out.println("Já depois de ajeitado: " + origemAux + destinoAux);

        float timerAux = 0;
        try {
             //timerAux = helper.execute(e, aux2);
            timerAux = helper.execute(origemAux, destinoAux);
             this.timer = Math.round(timerAux / 60);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return ""+this.timer+"min";
    }
    //< >
    public String getHoraPartida(String  destino) {
        if (trajeto == null) {
            return "00:00";
        }
        for(Paragem p: trajeto) {
            if (p.getNome().equalsIgnoreCase(destino)) {
                return p.getHora_partida();
            }
        }
        return null;
    }
    public String getHoraChegada(String  destino) {
        if (trajeto == null) {
            return "00:00";
        }
        for(Paragem p: trajeto) {
            if (p.getNome().equalsIgnoreCase(destino)) {
                return p.getHora_chegada();
            }
        }
        return null;
    }

    public String getOrigem() {
        if (origem == null) {
            return "";
        }
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        if (destino == null) return "";
        return destino;
    }
    public void changeRedCustom() {
        notificacao = new Button();
        notificacao.setMinWidth(17);
        notificacao.setMaxWidth(17);
        notificacao.setMinHeight(17);
        notificacao.setMaxHeight(17);
        notificacao.setPadding(new Insets(0));

        notificacao.setOnAction(event-> {
            System.out.println("XXXXXX");
            handle();
        });

    }
    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPreco() {
        if (preco == null) return "0.00€";
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(preco)+"€";
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public int getId_autocarro() {
        return id_autocarro;
    }

    public void setId_autocarro(int id_autocarro) {
        this.id_autocarro = id_autocarro;
    }

    public String getNome_autocarro() {
        return nome_autocarro;
    }

    public void setNome_autocarro(String nome_autocarro) {
        this.nome_autocarro = nome_autocarro;
    }

    public String getLotacao() {
        return String.valueOf(lotacao);
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isNotific() {
        return notific;
    }

    public void setNotific(boolean notific) {
        this.notific = notific;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void handle() {
        if (isNotific()) {


            app.removeNotification(this.id_autocarro);
            setNotific(false);
            getNotificacao().setText(" ");

        }

        else {

             app.addNotificacao(new Notificacao(id_autocarro, nome_autocarro, origem,destino,getHoraPartida(destino),getHoraChegada(destino), true, estado));
             setNotific(true);
             getNotificacao().setText("x");
        }
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public ArrayList<Paragem> getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(ArrayList<Paragem> trajeto) {
        this.trajeto = trajeto;
    }
    @Override
    public String toString() {
        return "Autocarro{" +
                "id_autocarro=" + id_autocarro +
                ", nome_autocarro='" + nome_autocarro + '\'' +
                ", lotacao=" + lotacao +
                ", estado=" + estado +
                ", notificacao=" + notific +
                ", empresa='" + empresa + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", trajeto=" + trajeto +
                '}';
    }

}
