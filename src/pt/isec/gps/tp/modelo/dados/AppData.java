package pt.isec.gps.tp.modelo.dados;

import org.testng.annotations.Test;
import pt.isec.gps.tp.modelo.db.DataBase;
import pt.isec.gps.tp.testes.SybaseStatementTest;
import pt.isec.gps.tp.utils.WriteArrObject;

import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AppData {
    private DataBase db;
    ArrayList<Autocarro> autocarros;
    ArrayList<Autocarro> autocarrosUt;
    ArrayList<Autocarro> autocarrosFiltros;
    ArrayList<Notificacao> notificacoes;
    ArrayList<String> nomeParagens;
    ArrayList<NotificacaoToWrite> notificacaoToWrites;
    Timer timer;
    private int contador;

    public AppData()  {
        db = new DataBase();
        db.connect();
        autocarrosFiltros = new ArrayList<>();
        autocarrosUt = new ArrayList<>();
        autocarros = new ArrayList<>();
        notificacoes = new ArrayList<>();
        nomeParagens = new ArrayList<>();
        notificacaoToWrites = new ArrayList<>();
        try {
            getAutocarros();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        timer = new Timer();
        //setNotificacoes(WriteArrObject.readData());
       // notificacoes.add(new Notificacao(1,"bus1","doca","cyu", "22:30","23:00",true, true));
       // notificacoes.add(new Notificacao(2,"bus2","doca","cyu", "22:30","23:00",true, true));
       // notificacoes.add(new Notificacao(3,"bus3","doca","cyu", "22:30","23:00",true, true));

       notificacaoToWrites = WriteArrObject.readData();
       if (notificacaoToWrites == null) {
           notificacaoToWrites = new ArrayList<>();
       }
       updateNotificacoes();
        if (notificacoes == null) {
            notificacoes = new ArrayList<>();
        }
        for (Notificacao n: notificacoes)
            System.out.println(n);
        confirmaNotificatoBUS();

        contador = 0;
        nomeParagens = db.getParagensToArrayList();



    }

    public void updateNotificacoes() {
        for (NotificacaoToWrite n: notificacaoToWrites) {
            notificacoes.add(new Notificacao(
                    n.getAutocarroID(),
                    n.getNome(),
                    n.getOrigem(),
                    n.destino,
                    n.getPartida(),
                    n.getHoraC(),
                    n.notificacao,
                    n.ativo
            ));
        }
    }

    public void updateNotificacoesToWrite() {
        for(Notificacao n: notificacoes) {
            for(NotificacaoToWrite n2: notificacaoToWrites) {
                if (n.getAutocarroID() == n2.getAutocarroID()) {
                    n2.setAtivo(n.getAtivo());
                    n2.setDestino(n.getDestino());
                    n2.setNome(n.getNome());
                    n2.setOrigem(n.getOrigem());
                    n2.setHora_p(n.getPartida());
                    n2.setHoraC(n.getHoraC());
                    n2.setNotificacao(n.getNotificacao());
                }
            }
        }
    }
    public void confirmaNotificatoBUS() {
        for (Autocarro a: autocarros) {
            a.setApp(this, ++contador);
            for (Notificacao n: notificacoes) {
                if (a.getId_autocarro() == n.getAutocarroID()) {
                    a.setNotific(true);
                    a.getNotificacao().setText("x");
                }
            }
        }
    }
    public void refreshNotifications() {
        for (Notificacao n: notificacoes) {
            n.refreshNotificacao();

        }
        updateNotificacoesToWrite();
        WriteArrObject.writeAnObject(notificacaoToWrites);

    }

    public void changeNotificationStatus(Notificacao c){
        c.setNotificacao(!c.getNotificacao());
        refreshNotifications();
    }
    public ArrayList<String> getNomeParagens() {
        return nomeParagens;
    }

    public void getAutocarros() throws SQLException {
        autocarros.addAll(db.getAutocarros());
    }
    public ArrayList<Notificacao> getNotificacoes() {
        return notificacoes;
    }
    public void removeNotification(int idAut) {
        for (Notificacao n: notificacoes) {
            if (n.getAutocarroID() == idAut) {
                notificacoes.remove(n);
                setFalseNotif(idAut);
                removeNotificacaoToWrite(n);

                updateNotificacoesToWrite();
                WriteArrObject.writeAnObject(notificacaoToWrites);
                return;
            }
        }
        //notificacoes.removeIf(n -> idAut == n.autocarroID);
        
    }
    public void setFalseNotif(int idAut) {
        for (Autocarro a: autocarros) {
            if (a.getId_autocarro() == idAut) {
                a.setNotific(false);
                a.getNotificacao().setText(" ");
            }
        }
    }
    public void setTrueNotif(int idAut) {
        for (Autocarro a: autocarros) {
            if (a.getId_autocarro() == idAut) {
                a.setNotific(true);
                a.getNotificacao().setText("X");
            }
        }
    }
    public void addNotificacoToWrite(Notificacao n) {
        notificacaoToWrites.add(new NotificacaoToWrite(n.getAutocarroID(), n.getNome(),n.getOrigem(), n.getDestino(), n.getHoraC(),n.getHoraC(),n.getNotificacao(),n.getAtivo()));
    }
    public void removeNotificacaoToWrite(Notificacao n) {
        for (NotificacaoToWrite n2: notificacaoToWrites) {
            if (n2.getAutocarroID() == n.getAutocarroID()) {
                notificacaoToWrites.remove(n2);
                return;
            }
        }
    }
    public void addNotificacao(Notificacao n) {
        for (Notificacao a : notificacoes) {
            if (a.getAutocarroID() == n.getAutocarroID())
                return;
        }
        notificacoes.add(n);
        addNotificacoToWrite(n);
        setTrueNotif(n.getAutocarroID());
        updateNotificacoesToWrite();
        System.out.println(notificacaoToWrites);
        WriteArrObject.writeAnObject(notificacaoToWrites);



    }
    public void setUpThread() {
        try {
             autocarros.clear();
             getAutocarros();
             confirmaNotificatoBUS();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setNotificacoes(ArrayList<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }
    public ArrayList<Autocarro> getAutocarrosUt(String origem, String destino, String data){
        String horaAuxC = "";
        String horaAuxP = "";
        int indexC= 0;
        int indexD =0;
        int index = 0;
        for(Autocarro a : autocarros){
            System.out.println(autocarros);
            boolean origemb = false;
            boolean destinob = false;
            for(Paragem p : a.getTrajeto()){
                if(p.getNome().equalsIgnoreCase(origem)) {
                    origemb = true;
                    horaAuxP = p.getHora_partida();
                    indexC = index;

                }
                if(p.getNome().equalsIgnoreCase(destino)){
                    destinob = true;
                    horaAuxC = p.getHora_chegada();
                    indexD = index;

                }
                index++;

            }

            if(origemb && destinob && indexC < indexD) {
                Autocarro aux = a;
                a.setOrigem(origem);
                a.setDestino(destino);
                a.setHoraChegada(horaAuxC);
                a.setHoraPartida(horaAuxP);
                autocarrosUt.add(a);
            }
        }
        return autocarrosUt;
    }

    public ArrayList<Autocarro> getAutocarrosNot(){
        ArrayList<Autocarro> autocarrosNot = new ArrayList<>();
        for(Autocarro a : autocarros){
            if(a.isNotific())
                autocarrosNot.add(a);
        }
        return autocarrosNot;
    }
    public Boolean newStateOnBus() {
        for (Notificacao n: notificacoes) {
            for(Autocarro a: autocarros) {
                if (!n.ativo.equals(a.getBestado()) && n.getAutocarroID() == a.getId_autocarro()) {
                    System.out.println(n.ativo);
                    System.out.println(a.getBestado());
                    n.setAtivo(a.getBestado());
                    System.out.println(n);
                    n.setNotificacao(true);
                    n.refreshNotificacao();
                    updateNotificacoesToWrite();
                    WriteArrObject.writeAnObject(notificacaoToWrites);

                    return true;
                }
            }
        }
        return false;
    }
    public ArrayList<Autocarro> getAutocarrosFiltros(String categoria, String hora_partida, String hora_chegada, String destino, String partida){
        autocarrosFiltros.clear();

        autocarrosUt.clear();
        ArrayList<Autocarro> aux2 = new ArrayList<>();
        aux2 = getAutocarrosUt(destino, partida, "");


        //categoria
        if ((hora_partida.isEmpty() || hora_partida.trim().isBlank()) && categoria.equals("TODOS") && (hora_chegada.isEmpty() || hora_partida.trim().isBlank())) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            autocarrosFiltros.addAll(autocarrosUt);

            return autocarrosFiltros;

        }
        if ((hora_partida.isEmpty() || hora_partida.trim().isBlank()) && categoria.equals("SMTUC") && (hora_chegada.isEmpty() || hora_partida.trim().isBlank())) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt){
                if(a.getEmpresa().equalsIgnoreCase(categoria))
                    autocarrosFiltros.add(a);
            }
            return autocarrosFiltros;
        }
        if ((hora_partida.isEmpty() || hora_partida.trim().isBlank()) && categoria.equals("Transdev") && (hora_chegada.isEmpty() || hora_partida.trim().isBlank())) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt){
                if(a.getEmpresa().equalsIgnoreCase(categoria))
                    autocarrosFiltros.add(a);
            }
            return autocarrosFiltros;
        }

        //hora de partida
        if (!hora_partida.isBlank() && (categoria.isBlank() || categoria.isBlank()) && (hora_chegada.isEmpty() || hora_partida.trim().isBlank())) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt){
                for(Paragem p : a.getTrajeto()){
                    if(p.getNome().equalsIgnoreCase(a.getOrigem())){
                        if(p.getHora_partida().equalsIgnoreCase(hora_partida))
                            autocarrosFiltros.add(a);
                    }
                }
            }
            return autocarrosFiltros;
        }

        //hora de chegada
        if ((hora_partida.isEmpty() || hora_partida.trim().isBlank()) && (categoria.isBlank() || categoria.isBlank()) && !hora_chegada.isBlank()) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt){
                for(Paragem p : a.getTrajeto()){
                    if(p.getNome().equalsIgnoreCase(a.getDestino())){
                        if(p.getHora_chegada().equalsIgnoreCase(hora_chegada))
                            autocarrosFiltros.add(a);
                    }
                }

            }
            return autocarrosFiltros;
        }

        //categoria e hora de partida
        if (!hora_partida.isBlank() && categoria.equals("Todos") && (hora_chegada.isEmpty() || hora_partida.trim().isBlank())) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt) {
                if (a.getEmpresa().equalsIgnoreCase(categoria)) {
                    for (Paragem p : a.getTrajeto()) {
                        if (p.getNome().equalsIgnoreCase(a.getOrigem())) {
                            if (p.getHora_partida().equalsIgnoreCase(hora_partida))
                                autocarrosFiltros.add(a);
                        }
                    }

                }
            }
            return autocarrosFiltros;
        }
        if (!hora_partida.isBlank() && categoria.equals("SMTUC") && (hora_chegada.isEmpty() || hora_partida.trim().isBlank())) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt) {
                if (a.getEmpresa().equalsIgnoreCase(categoria)) {
                    for (Paragem p : a.getTrajeto()) {
                        if (p.getNome().equalsIgnoreCase(a.getOrigem())) {
                            if (p.getHora_partida().equalsIgnoreCase(hora_partida))
                                autocarrosFiltros.add(a);
                        }
                    }

                }
            }
            return autocarrosFiltros;
        }
        if (!hora_partida.isBlank() && categoria.equals("Transdev") && (hora_chegada.isEmpty() || hora_partida.trim().isBlank())) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt) {
                if (a.getEmpresa().equalsIgnoreCase(categoria)) {
                    for (Paragem p : a.getTrajeto()) {
                        if (p.getNome().equalsIgnoreCase(a.getOrigem())) {
                            if (p.getHora_partida().equalsIgnoreCase(hora_partida))
                                autocarrosFiltros.add(a);
                        }
                    }

                }
            }
            return autocarrosFiltros;
        }

        //categoria e hora de chegada
        if ((hora_partida.isEmpty() || hora_partida.trim().isBlank()) && categoria.equals("Todos") && !hora_chegada.isBlank()) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt) {
                if (a.getEmpresa().equalsIgnoreCase(categoria)) {
                    for (Paragem p : a.getTrajeto()) {
                        if (p.getNome().equalsIgnoreCase(a.getDestino())) {
                            if (p.getHora_chegada().equalsIgnoreCase(hora_chegada))
                                autocarrosFiltros.add(a);
                        }
                    }

                }
            }
            return autocarrosFiltros;
        }
        if ((hora_partida.isEmpty() || hora_partida.trim().isBlank()) && categoria.equals("SMTUC") && !hora_chegada.isBlank()) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt) {
                if (a.getEmpresa().equalsIgnoreCase(categoria)) {
                    for (Paragem p : a.getTrajeto()) {
                        if (p.getNome().equalsIgnoreCase(a.getDestino())) {
                            if (p.getHora_chegada().equalsIgnoreCase(hora_chegada))
                                autocarrosFiltros.add(a);
                        }
                    }

                }
            }
            return autocarrosFiltros;
        }
        if ((hora_partida.isEmpty() || hora_partida.trim().isBlank()) && categoria.equals("Transdev") && !hora_chegada.isBlank()) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt) {
                if (a.getEmpresa().equalsIgnoreCase(categoria)) {
                    for (Paragem p : a.getTrajeto()) {
                        if (p.getNome().equalsIgnoreCase(a.getDestino())) {
                            if (p.getHora_chegada().equalsIgnoreCase(hora_chegada))
                                autocarrosFiltros.add(a);
                        }
                    }

                }
            }
            return autocarrosFiltros;
        }

        //hora de partida e hora de chegada
        if (!hora_partida.isBlank() && (categoria.isBlank() || categoria.isBlank()) && !hora_chegada.isBlank()) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt){
                boolean cheg = false;
                boolean part = false;
                for(Paragem p : a.getTrajeto()){
                    if(p.getNome().equalsIgnoreCase(a.getOrigem())){
                        if(p.getHora_partida().equalsIgnoreCase(hora_partida))
                            part = true;
                    }
                    if(p.getNome().equalsIgnoreCase(a.getDestino())){
                        if(p.getHora_chegada().equalsIgnoreCase(hora_chegada))
                            cheg = true;
                    }
                }
                if(cheg && part) {
                    autocarrosFiltros.add(a);
                }
            }
            return autocarrosFiltros;
        }

        //todos os filtros
        if (!hora_partida.isBlank() && categoria.equals("Todos") && !hora_chegada.isBlank()) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt){
                if(a.getEmpresa().equalsIgnoreCase(categoria))
                    continue;
                boolean cheg = false;
                boolean part = false;
                for(Paragem p : a.getTrajeto()){
                    if(p.getNome().equalsIgnoreCase(a.getOrigem())){
                        if(p.getHora_partida().equalsIgnoreCase(hora_partida))
                            part = true;
                    }
                    if(p.getNome().equalsIgnoreCase(a.getDestino())){
                        if(p.getHora_chegada().equalsIgnoreCase(hora_chegada))
                            cheg = true;
                    }

                }
                if(cheg && part) {
                    autocarrosFiltros.add(a);
                }
            }
            return autocarrosFiltros;

        }
        if (!hora_partida.isBlank() && categoria.equals("SMTUC") && !hora_chegada.isBlank()) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();

            for(Autocarro a : autocarrosUt){
                if(a.getEmpresa().equalsIgnoreCase(categoria))
                    continue;
                boolean cheg = false;
                boolean part = false;
                for(Paragem p : a.getTrajeto()){
                    if(p.getNome().equalsIgnoreCase(a.getOrigem())){
                        if(p.getHora_partida().equalsIgnoreCase(hora_partida))
                            part = true;
                    }
                    if(p.getNome().equalsIgnoreCase(a.getDestino())){
                        if(p.getHora_chegada().equalsIgnoreCase(hora_chegada))
                            cheg = true;
                    }

                }
                if(cheg && part) {
                    autocarrosFiltros.add(a);
                }
            }
            return autocarrosFiltros;

        }
        if (!hora_partida.isBlank() && categoria.equals("Transdev") && !hora_chegada.isBlank()) {
            ArrayList<Autocarro> autocarrosFiltros = new ArrayList<>();
            System.out.println("Hellooooo");
            for(Autocarro a : autocarrosUt){
                System.out.println("Hellooooo3");

                if(!a.getEmpresa().equalsIgnoreCase(categoria)){
                    System.out.println("Hellooooo4 : " + a.getEmpresa());
                    System.out.println("Hellooooo5 : " + categoria);

                    continue;
                }


                boolean cheg = false;
                boolean part = false;
                for(Paragem p : a.getTrajeto()){
                    if(p.getNome().equalsIgnoreCase(a.getOrigem())){
                        System.out.println("Hellooooo2");

                        if(p.getHora_partida().trim().equalsIgnoreCase(hora_partida.trim())){
                            part = true;
                            System.out.println("Hellooooo52");

                        }

                    }
                    if(p.getNome().equalsIgnoreCase(a.getDestino())){
                        System.out.println("pChega: " + p.getHora_chegada() + "\nchega: "+hora_chegada);
                        if(p.getHora_chegada().trim().equalsIgnoreCase(hora_chegada.trim())){
                            cheg= true;
                            System.out.println("Hellooooo52");

                        }

                    }

                }
                if(cheg && part) {
                    autocarrosFiltros.add(a);
                }
            }
            return autocarrosFiltros;

        }



        return null;
    }

    public ArrayList<NotificacaoToWrite> getNotificacoesToWrite() {
        return notificacaoToWrites;
    }
}

