package pt.isec.gps.tp.modelo.dados;

public class Paragem_rota {

    private int id_rota;
    private String nome_paragem;

    public Paragem_rota(int id_rota, String nome_paragem) {
        this.id_rota = id_rota;
        this.nome_paragem = nome_paragem;
    }

    public int getId_rota() {
        return id_rota;
    }

    public void setId_rota(int id_rota) {
        this.id_rota = id_rota;
    }

    public String getNome_paragem() {
        return nome_paragem;
    }

    public void setNome_paragem(String nome_paragem) {
        this.nome_paragem = nome_paragem;
    }
}
