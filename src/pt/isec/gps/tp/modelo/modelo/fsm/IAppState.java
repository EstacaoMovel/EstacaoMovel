package pt.isec.gps.tp.modelo.fsm;

public interface IAppState {
    AppState getState();
    void recolherDados();
    void voltar();
    void notificacoes();
    void autocarrosDisponiveis();
    void detalhesAutocarro();
}
