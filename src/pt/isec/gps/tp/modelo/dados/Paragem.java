package pt.isec.gps.tp.modelo.dados;

public class Paragem {
        private String nome;
        private String hora_partida;
        private String hora_chegada;

    public Paragem(String nome, String hora_partida, String hora_chegada) {
        this.nome = nome;
        this.hora_partida = hora_partida;
        this.hora_chegada = hora_chegada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHora_partida() {
        return hora_partida;
    }

    public void setHora_partida(String hora_partida) {
        this.hora_partida = hora_partida;
    }

    public String getHora_chegada() {
        return hora_chegada;
    }

    public void setHora_chegada(String hora_chegada) {
        this.hora_chegada = hora_chegada;
    }

    @Override
    public String toString() {
        return "Paragem{" +
                "nome='" + nome + '\'' +
                ", hora_partida='" + hora_partida + '\'' +
                ", hora_chegada='" + hora_chegada + '\'' +
                '}';
    }
}
