package pt.isec.gps.tp.modelo.fsm;

import pt.isec.gps.tp.modelo.dados.Autocarro;
import pt.isec.gps.tp.modelo.dados.Notificacao;
import pt.isec.gps.tp.utils.CategoriaFiltro;

import java.util.ArrayList;

public interface IAppState {
    AppState getState();
    void recolherDados();
    void voltar();
    void notificacoes();
    void autocarrosDisponiveis();
    void detalhesAutocarro();
    ArrayList<Notificacao> getNotificacoes();
    ArrayList<Autocarro> getAutocarrosFiltros(CategoriaFiltro filtro, String hora_chegada, String hora_destino, String origem, String destino);
}
