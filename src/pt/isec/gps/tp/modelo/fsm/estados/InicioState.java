package pt.isec.gps.tp.modelo.fsm.estados;

import pt.isec.gps.tp.modelo.dados.AppData;
import pt.isec.gps.tp.modelo.fsm.AppContext;
import pt.isec.gps.tp.modelo.fsm.AppState;
import pt.isec.gps.tp.modelo.fsm.AppStateAdapter;

public class InicioState extends AppStateAdapter {
    public InicioState(AppContext context, AppData data) {
        super(context, data);
    }

    @Override
    public AppState getState() {
        return AppState.INICIO_STATE;
    }

    @Override
    public void recolherDados() {
        changeState(AppState.RECOLHA_DADOS_STATE);
    }
}
