package pt.isec.gps.tp.modelo.fsm;

import pt.isec.gps.tp.modelo.dados.AppData;
import pt.isec.gps.tp.modelo.dados.Autocarro;
import pt.isec.gps.tp.modelo.dados.Notificacao;
import pt.isec.gps.tp.utils.CategoriaFiltro;

import java.util.ArrayList;

public class AppStateAdapter implements IAppState {
    AppContext context;
    AppData data;

    public AppStateAdapter(AppContext context, AppData data) {
        this.context = context;
        this.data = data;
    }

    protected void changeState(AppState newState) {
        context.changeState(newState.createState(context, data));
    }

    protected void changeState(IAppState state) {
        context.changeState(state);
    }

    protected IAppState getLastState() {
        return context.getLastState();
    }

    @Override
    public AppState getState() {
        return null;
    }

    @Override
    public void recolherDados() {

    }

    @Override
    public void voltar() {

    }

    @Override
    public void notificacoes() {

    }

    @Override
    public void autocarrosDisponiveis() {

    }

    @Override
    public void detalhesAutocarro() {

    }

    @Override
    public ArrayList<Notificacao> getNotificacoes() {
        return null;
    }

    @Override
    public ArrayList<Autocarro> getAutocarrosFiltros(CategoriaFiltro filtro, String hora_chegada, String hora_destino, String origem, String destino) {
        return null;
    }
}
