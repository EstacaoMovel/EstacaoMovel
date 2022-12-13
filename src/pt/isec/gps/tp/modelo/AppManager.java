package pt.isec.gps.tp.modelo;

import javafx.animation.Timeline;
import org.testng.annotations.Test;
import pt.isec.gps.tp.modelo.dados.Autocarro;
import pt.isec.gps.tp.modelo.dados.Notificacao;
import pt.isec.gps.tp.modelo.dados.NotificacaoToWrite;
import pt.isec.gps.tp.modelo.fsm.AppContext;
import pt.isec.gps.tp.modelo.fsm.AppState;
import pt.isec.gps.tp.ui.RootPane;
import pt.isec.gps.tp.utils.CategoriaFiltro;
import pt.isec.gps.tp.utils.DisponiveisContent;
import pt.isec.gps.tp.utils.WriteArrObject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static org.testng.AssertJUnit.assertEquals;

public class AppManager {
    private AppContext fsm;
    Autocarro aux;
    PropertyChangeSupport pcs;
    DisponiveisContent aux2;
    ArrayList<Autocarro> autocarrosFiltros;
    String origem, destino;
    String dia;
    Notificacao b;
    Timer timer;
    RootPane rp;
    Timeline time;
    TimerTask task;
    Boolean newNotification= false;
    public AppManager() {
        this.fsm = new AppContext();
        this.pcs = new PropertyChangeSupport(this);
        autocarrosFiltros = new ArrayList<>();
        timer = new Timer();

        setUpThread();
    }
    public ArrayList<Notificacao> getNotificacoes() {
        return fsm.getNotificacoes();

    }
    public void escreveNotificacoes() {
        WriteArrObject.writeAnObject(getNotificacoesToWrite());

    }

    private ArrayList<NotificacaoToWrite> getNotificacoesToWrite() {
        return fsm.getNotificacoesToWrite();
    }

    public void setRp(RootPane rp) {
        this.rp = rp;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public void removeNotificacao(Autocarro a) {
        fsm.removeNotificacao(a.getId_autocarro());
    }
    public void addNotificacao(Autocarro a) {
        b = new Notificacao(a.getId_autocarro(),
                a.getNome_autocarro(), a.getOrigem(),
                a.getDestino(), a.getHoraPartida(),
                a.getHoraChegada(),true,a.getBestado());
        fsm.addNotificacao(b);
    }
    public ArrayList<Autocarro> getAutocarrosFiltros() {
        autocarrosFiltros = fsm.getAutocarrosFiltros(CategoriaFiltro.TODOS, "","", origem, destino);
        System.out.println("AUTOCARROS FILTRO" + autocarrosFiltros);
        if (autocarrosFiltros == null) {
            autocarrosFiltros = new ArrayList<>();
            Autocarro po = new Autocarro(0,"Não há autocarros disponiveis", 0, true,true,"","","");
            autocarrosFiltros.add(po);
        }
        return autocarrosFiltros;
    }
    public ArrayList<Autocarro> getAutocarrosComFiltro(String categoria, String horaPartida, String horaChegada) {
        CategoriaFiltro categoriaAux;
        if (!categoria.equalsIgnoreCase(CategoriaFiltro.SMTUC.label) && !categoria.equalsIgnoreCase(CategoriaFiltro.TODOS.label)) {
            categoriaAux = CategoriaFiltro.TRANSDEV;
        }
        else
            categoriaAux = CategoriaFiltro.valueOf(categoria);

        autocarrosFiltros = fsm.getAutocarrosFiltros(categoriaAux, horaPartida, horaChegada, origem, destino);
        System.out.println("AUTOCARROS FILTRO" + autocarrosFiltros);
        if (autocarrosFiltros == null) {
            autocarrosFiltros = new ArrayList<>();
            //Autocarro po = new Autocarro(0,"Não há autocarros disponiveis", 0, true,true,"","","");
            //autocarrosFiltros.add(po);
        }
        return autocarrosFiltros;
    }
    public ArrayList<String> getRuas() {
        return fsm.ruas();
    }
    public void wait_timer(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void getRuasTeste() { // Validado
        assert(fsm.ruas() != null);
        System.out.println("Teste com sucesso ");

    }

    /**
     * @test Autocarro Filtros
     */
    @Test
    public void getAutocarros1() { // Validado
        assert(fsm.getAutocarrosFiltros(CategoriaFiltro.TODOS,"","","Coimbra","Serpins") != null);
        System.out.println("Teste com sucesso ");

    }
    @Test
    public void getAutocarros2() { // Validado
        assert(fsm.getAutocarrosFiltros(CategoriaFiltro.TODOS,"","","Serpins","Coimbra") != null);
        System.out.println("Teste com sucesso ");

    }
    @Test
    public void getAutocarros3() { // Validado
        assert(fsm.getAutocarrosFiltros(CategoriaFiltro.TODOS,"","","Arzila","Lisboa") != null);
        System.out.println("Teste com sucesso ");

    }
    @Test
    public void getAutocarros4() { // Validado
        assert(fsm.getAutocarrosFiltros(CategoriaFiltro.TODOS,"","","Arzila","Coimbra") != null);
        System.out.println("Teste com sucesso ");

    }

    @Test
    public void getStateTest() { // Validado
        assertEquals(getState(), AppState.INICIO_STATE);
        System.out.println("Teste com sucesso ");

    }
    public Boolean getNewNotification() {
        return newNotification;
    }

    public void setNewNotification(Boolean newNotification) {
        this.newNotification = newNotification;
    }

    public void setUpThread() {

        task = new TimerTask() {
            @Override
            public void run() {
                fsm.getAutocarroDB();
                newNotification = fsm.updateNotifcVariable();
            }

        };


        timer.scheduleAtFixedRate(task, 10000,  10000); // TODO improve with constant
    }

    public String isInNotifications(Autocarro c) {
        if (getNotificacoes()==null || getNotificacoes().isEmpty()) return " ";
        for (Notificacao n: getNotificacoes()) {
            if (c.getId_autocarro() == n.getAutocarroID()) {
                return "X";
            }
        }
        return " ";
    }
    public String checkNotification(Autocarro c) {
        if (getNotificacoes() == null) return "false";
        for (Notificacao n: getNotificacoes()) {
            if (n.getAutocarroID() == c.getId_autocarro()) {
                return "true";
            }

        }
        return "false";
    }
    public void setAutocarrosFiltros(ArrayList<Autocarro> autocarrosFiltros) {
        this.autocarrosFiltros = autocarrosFiltros;
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

    public void autocarrosDisponiveis(String destino, String chegada, String dia) {
        this.origem = chegada;
        this.destino = destino;
        this.dia = dia;
        fsm.autocarrosDisponiveis();
        this.pcs.firePropertyChange(null, null, null);
    }

    public void detalhesAutocarro(Autocarro aux) {
        this.aux= aux;

        fsm.detalhesAutocarro();
        this.pcs.firePropertyChange(null, null, null);
    }
    public Autocarro getAutocarro() {

        System.out.println("Hello2: " + aux);

        return aux;
    }

    public void stopThread() {
        task.cancel();
        timer.cancel();
        System.exit(1);
    }
    public void handleNotification(Autocarro a, String text) {
        if (text.equals("X")) {
            System.out.println("CHEGUEI AQUI 2");
            removeNotificacao(a);
        }
        else{
            System.out.println("CHEGUEI AQUI 3");

            addNotificacao(a);}
            System.out.println(getNotificacoes());
    }

    public void refreshNotifications() {
        fsm.refreshNotifications();
    }
    public void changeNotificationStatus(Notificacao c){
        fsm.changeNotificationStatus(c);
    }
    //public ArrayList<Autocarro> filtro_autocarro()
}
