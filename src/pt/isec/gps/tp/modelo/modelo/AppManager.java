package pt.isec.gps.tp.modelo;

import pt.isec.gps.tp.modelo.fsm.AppContext;
import pt.isec.gps.tp.modelo.fsm.AppState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AppManager {
    private AppContext fsm;

    PropertyChangeSupport pcs;

    public AppManager() {
        this.fsm = new AppContext();
        this.pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public AppState getState() {
        return fsm.getState();
    }

    public void recolherDados() {
        fsm.recolherDados();
        this.pcs.firePropertyChange(null, null, null);
    }

    public void voltar() {
        fsm.voltar();
        this.pcs.firePropertyChange(null, null, null);
    }

    public void notificacoes() {
        fsm.notificacoes();
        this.pcs.firePropertyChange(null, null, null);
    }

    public void autocarrosDisponiveis() {
        fsm.autocarrosDisponiveis();
        this.pcs.firePropertyChange(null, null, null);
    }

    public void detalhesAutocarro() {
        fsm.detalhesAutocarro();
        this.pcs.firePropertyChange(null, null, null);
    }
}
