package pt.isec.gps.tp.modelo.dados;

import pt.isec.gps.tp.modelo.db.DataBase;

import java.util.ArrayList;

public class AppData {
    private DataBase db;
    ArrayList<Autocarro> autocarros;
    ArrayList<Destino> destinos;
    ArrayList<Origem> origens;

    public void getAutocarros(String origem, String destino, String data ){
        autocarros.addAll(db.consultarAutocarros(origem, destino, data));
    }

    public void getOrigens(String origem){
        origens.addAll(db.consultarOrigem(origem));
    }

    public void getDestinos(String destino){
        destinos.addAll(db.consultarDestino(destino));
    }
}

