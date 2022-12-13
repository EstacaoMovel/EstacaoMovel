package pt.isec.gps.tp.modelo.fsm.estados;

import pt.isec.gps.tp.modelo.dados.AppData;
import pt.isec.gps.tp.modelo.dados.Notificacao;
import pt.isec.gps.tp.modelo.fsm.AppContext;
import pt.isec.gps.tp.modelo.fsm.AppState;
import pt.isec.gps.tp.modelo.fsm.AppStateAdapter;

import java.util.ArrayList;

public class NotificacoesState extends AppStateAdapter {
    AppData data;
    public NotificacoesState(AppContext context, AppData data) {
        super(context, data);
        this.data = data;
    }

    @Override
    public AppState getState() {
        return AppState.NOTIFICACOES_STATE;
    }

    @Override
    public void recolherDados() {
        changeState(getLastState());
    }

    @Override
    public ArrayList<Notificacao> getNotificacoes() {
        return data.getNotificacoes();
    }
}
