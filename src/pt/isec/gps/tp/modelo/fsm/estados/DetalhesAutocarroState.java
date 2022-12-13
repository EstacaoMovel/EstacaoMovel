package pt.isec.gps.tp.modelo.fsm.estados;

import pt.isec.gps.tp.modelo.dados.AppData;
import pt.isec.gps.tp.modelo.fsm.AppContext;
import pt.isec.gps.tp.modelo.fsm.AppState;

public class DetalhesAutocarroState extends InicioState {
    public DetalhesAutocarroState(AppContext context, AppData data) {
        super(context, data);
    }

    @Override
    public AppState getState() {
        return AppState.DETALHES_AUTOCARRO_STATE;
    }

    @Override
    public void recolherDados() {
        changeState(AppState.RECOLHA_DADOS_STATE);
    }

    @Override
    public void voltar() {
        //changeState(getLastState());
        changeState(AppState.AUTOCARROS_DISPONIVEIS_STATE);
    }

    @Override
    public void notificacoes() {
        changeState(AppState.NOTIFICACOES_STATE);
    }
}
