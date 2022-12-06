package pt.isec.gps.tp.modelo.dados;

public class Origem {

    private String nome_origem;
    private int id_origem;
    private String tempo_chegada_orig;
    private String tempo_partida_orig;


    public Origem(String nome_origem, int id_origem, String tempo_chegada_orig, String tempo_partida_orig) {
        this.nome_origem = nome_origem;
        this.id_origem = id_origem;
        this.tempo_chegada_orig = tempo_chegada_orig;
        this.tempo_partida_orig = tempo_partida_orig;
    }

    public String getNome_origem() {
        return nome_origem;
    }

    public void setNome_origem(String nome_origem) {
        this.nome_origem = nome_origem;
    }

    public int getId_origem() {
        return id_origem;
    }

    public void setId_origem(int id_origem) {
        this.id_origem = id_origem;
    }

    public String getTempo_chegada_orig() {
        return tempo_chegada_orig;
    }

    public void setTempo_chegada_orig(String tempo_chegada_orig) {
        this.tempo_chegada_orig = tempo_chegada_orig;
    }

    public String getTempo_partida_orig() {
        return tempo_partida_orig;
    }

    public void setTempo_partida_orig(String tempo_partida_orig) {
        this.tempo_partida_orig = tempo_partida_orig;
    }
}
