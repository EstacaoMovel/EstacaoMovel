package pt.isec.gps.tp.modelo.fsm.estados;

import pt.isec.gps.tp.modelo.dados.AppData;
import pt.isec.gps.tp.modelo.dados.Autocarro;
import pt.isec.gps.tp.modelo.fsm.AppContext;
import pt.isec.gps.tp.modelo.fsm.AppState;
import pt.isec.gps.tp.modelo.fsm.AppStateAdapter;
import pt.isec.gps.tp.utils.CategoriaFiltro;

import java.util.ArrayList;

public class AutocarrosDisponiveisState extends AppStateAdapter {
    AppData data;
    public AutocarrosDisponiveisState(AppContext context, AppData data) {
        super(context, data);
        this.data = data;
    }

    @Override
    public AppState getState() {
        return AppState.AUTOCARROS_DISPONIVEIS_STATE;
    }

    @Override
    public void recolherDados() {
        changeState(AppState.RECOLHA_DADOS_STATE);
    }

    @Override
    public void detalhesAutocarro() {
        changeState(AppState.DETALHES_AUTOCARRO_STATE);
    }

    @Override
    public void notificacoes() {
        changeState(AppState.NOTIFICACOES_STATE);
    }

    @Override
    public void autocarrosDisponiveis() {
        changeState(AppState.AUTOCARROS_DISPONIVEIS_STATE);
    }

    @Override
    public ArrayList<Autocarro> getAutocarrosFiltros(CategoriaFiltro filtro, String hora_chegada, String hora_destino, String origem, String destino) {
        return data.getAutocarrosFiltros(filtro.label, hora_chegada, hora_destino, destino, origem);
    }

    @Override
    /*public void voltar() {
        changeState(getLastState());
    }*/


    public void voltar() {
        changeState(AppState.RECOLHA_DADOS_STATE);
    }
}
