package pt.isec.gps.tp.modelo.fsm;

import pt.isec.gps.tp.modelo.dados.AppData;
import pt.isec.gps.tp.modelo.fsm.estados.*;

public enum AppState {

    INICIO_STATE, DETALHES_AUTOCARRO_STATE, AUTOCARROS_DISPONIVEIS_STATE, NOTIFICACOES_STATE, RECOLHA_DADOS_STATE;

    public IAppState createState(AppContext context, AppData data) {
        return switch (this) {
            case INICIO_STATE -> new InicioState(context, data);
            case DETALHES_AUTOCARRO_STATE -> new DetalhesAutocarroState(context, data);
            case AUTOCARROS_DISPONIVEIS_STATE -> new AutocarrosDisponiveisState(context, data);
            case NOTIFICACOES_STATE -> new NotificacoesState(context, data);
            case RECOLHA_DADOS_STATE -> new RecolhaDadosState(context, data);
        };
    }
}
