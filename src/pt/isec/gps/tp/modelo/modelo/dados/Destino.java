package pt.isec.gps.tp.modelo.dados;

public class Destino {

    private String nome_destino;
    private int id_destino;
    private String tempo_chegada_dest;
    private String tempo_partida_dest;

    public Destino(String nome_destino, int id_destino, String tempo_chegada_dest, String tempo_partida_dest) {
        this.nome_destino = nome_destino;
        this.id_destino = id_destino;
        this.tempo_chegada_dest = tempo_chegada_dest;
        this.tempo_partida_dest = tempo_partida_dest;
    }

    public String getNome_destino() {
        return nome_destino;
    }

    public void setNome_destino(String nome_destino) {
        this.nome_destino = nome_destino;
    }

    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public String getTempo_chegada_dest() {
        return tempo_chegada_dest;
    }

    public void setTempo_chegada_dest(String tempo_chegada_dest) {
        this.tempo_chegada_dest = tempo_chegada_dest;
    }

    public String getTempo_partida_dest() {
        return tempo_partida_dest;
    }

    public void setTempo_partida_dest(String tempo_partida_dest) {
        this.tempo_partida_dest = tempo_partida_dest;
    }
}
