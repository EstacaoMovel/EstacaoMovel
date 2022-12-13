package pt.isec.gps.tp.modelo.fsm;

import pt.isec.gps.tp.modelo.dados.AppData;
import pt.isec.gps.tp.modelo.dados.Autocarro;
import pt.isec.gps.tp.modelo.dados.Notificacao;
import pt.isec.gps.tp.modelo.dados.NotificacaoToWrite;
import pt.isec.gps.tp.modelo.fsm.estados.AutocarrosDisponiveisState;
import pt.isec.gps.tp.modelo.fsm.estados.InicioState;
import pt.isec.gps.tp.modelo.fsm.estados.RecolhaDadosState;
import pt.isec.gps.tp.utils.CategoriaFiltro;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppContext {
    IAppState state;
    AppData data;
    IAppState lastState;

    public void changeState(IAppState newState) {
        lastState = this.state;
        this.state = newState;
    }
    public ArrayList<Autocarro> getAutocarrosFiltros(CategoriaFiltro filtro, String hora_partida, String hora_chegada, String origem, String destino) {
        return state.getAutocarrosFiltros(filtro,hora_partida,hora_chegada,origem,destino);
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
    public void removeNotificacao(int id) {
        data.removeNotification(id);
    }
    public void getAutocarroDB() {
        data.setUpThread();

    }
    public Boolean updateNotifcVariable() {
        return data.newStateOnBus();
    }
    public ArrayList<String> ruas() {
        return data.getNomeParagens();
    }
    public void addNotificacao(Notificacao n) {
        data.addNotificacao(n);
    }
    public ArrayList<Notificacao> getNotificacoes() {
        return data.getNotificacoes();
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

    public void refreshNotifications() {
        data.refreshNotifications();
    }
    public void changeNotificationStatus(Notificacao c){
        data.changeNotificationStatus(c);
    }

    public ArrayList<NotificacaoToWrite> getNotificacoesToWrite() {
        return data.getNotificacoesToWrite();
    }
}
