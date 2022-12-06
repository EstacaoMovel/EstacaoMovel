package pt.isec.gps.tp.modelo.fsm;

import pt.isec.gps.tp.modelo.dados.AppData;

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
}
