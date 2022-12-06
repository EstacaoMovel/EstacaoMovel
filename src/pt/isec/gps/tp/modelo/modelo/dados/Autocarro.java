package pt.isec.gps.tp.modelo.dados;

public class Autocarro {

    private int id_autocarro;
    private String nome_autocarro;
    private float preco_bilhete;
    private int lotacao;
    private boolean estado;
    private String horario_chegada;
    private String horario_partida;
    private int id_localizacao_autocarro;
    private int id_origem_autocarro;
    private int id_destino_autocarro;
    private int id_rota_autocarro;
    private boolean notificacao;
    private String empresa;

    public Autocarro(int id_autocarro, String nome_autocarro, float preco_bilhete, int lotacao, boolean estado, String horario_chegada, String horario_partida, int id_localizacao_autocarro, int id_origem_autocarro, int id_destino_autocarro, int id_rota_autocarro,boolean notificao,String empresa) {
        this.id_autocarro = id_autocarro;
        this.nome_autocarro = nome_autocarro;
        this.preco_bilhete = preco_bilhete;
        this.lotacao = lotacao;
        this.estado = estado;
        this.horario_chegada = horario_chegada;
        this.horario_partida = horario_partida;
        this.id_localizacao_autocarro = id_localizacao_autocarro;
        this.id_origem_autocarro = id_origem_autocarro;
        this.id_destino_autocarro = id_destino_autocarro;
        this.id_rota_autocarro = id_rota_autocarro;
        this.notificacao = notificao;
        this.empresa=empresa;
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

    public float getPreco_bilhete() {
        return preco_bilhete;
    }

    public void setPreco_bilhete(float preco_bilhete) {
        this.preco_bilhete = preco_bilhete;
    }

    public int getLotacao() {
        return lotacao;
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

    public String getHorario_chegada() {
        return horario_chegada;
    }

    public void setHorario_chegada(String horario_chegada) {
        this.horario_chegada = horario_chegada;
    }

    public String getHorario_partida() {
        return horario_partida;
    }

    public void setHorario_partida(String horario_partida) {
        this.horario_partida = horario_partida;
    }

    public int getId_localizacao_autocarro() {
        return id_localizacao_autocarro;
    }

    public void setId_localizacao_autocarro(int id_localizacao_autocarro) {
        this.id_localizacao_autocarro = id_localizacao_autocarro;
    }

    public int getId_origem_autocarro() {
        return id_origem_autocarro;
    }

    public void setId_origem_autocarro(int id_origem_autocarro) {
        this.id_origem_autocarro = id_origem_autocarro;
    }

    public int getId_destino_autocarro() {
        return id_destino_autocarro;
    }

    public void setId_destino_autocarro(int id_destino_autocarro) {
        this.id_destino_autocarro = id_destino_autocarro;
    }

    public int getId_rota_autocarro() {
        return id_rota_autocarro;
    }

    public void setId_rota_autocarro(int id_rota_autocarro) {
        this.id_rota_autocarro = id_rota_autocarro;
    }
}
