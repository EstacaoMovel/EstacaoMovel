package pt.isec.gps.tp.modelo.fsm;

import pt.isec.gps.tp.modelo.dados.AppData;
import pt.isec.gps.tp.modelo.fsm.estados.InicioState;

public class AppContext {
    IAppState state;
    AppData data;
    IAppState lastState;

    public void changeState(IAppState newState) {
        lastState = this.state;
        this.state = newState;
    }

    public AppContext() {
        data = new AppData();
        state = new InicioState(this, data);
        lastState = null;
    }

    public AppState getState() {
        if (this.state == null) {
            return null;
        }
        return state.getState();
    }

    public IAppState getLastState() {
        return lastState;
    }

    public void recolherDados() {
        state.recolherDados();
    }

    public void notificacoes() {
        state.notificacoes();
    }

    public void autocarrosDisponiveis() {
        state.autocarrosDisponiveis();
    }

    public void detalhesAutocarro() {
        state.detalhesAutocarro();
    }

    public void voltar() {
        state.voltar();
    }
}
