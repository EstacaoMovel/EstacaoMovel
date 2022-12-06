package pt.isec.gps.tp.modelo.db;

import pt.isec.gps.tp.modelo.dados.Autocarro;
import pt.isec.gps.tp.modelo.dados.Destino;
import pt.isec.gps.tp.modelo.dados.Origem;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement stmt = null;

    public void connect() {
        try {
            String url = "jdbc:sqlite:EstacaoMovel-master\\src\\pt\\isec\\gps\\tp\\modelo\\DB\\DataBase_GPS.db";
            connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException ex) {
                System.out.println("Erro aceder a base de dados");
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
            }
        }
    }

    public ArrayList<Autocarro> getAutocarros( String query)throws SQLException {

        ArrayList<Autocarro> Autocarros = new ArrayList<>();
        rs = stmt.executeQuery(query);

        while(rs.next())
            Autocarros.add(new Autocarro(
                    rs.getInt("id"),
                    rs.getString("Nome"),
                    rs.getFloat("Preco_bilhete"),
                    rs.getInt("Lotacao"),
                    rs.getBoolean("Estado"),
                    rs.getString("Horario_chegada"),
                    rs.getString("Horario_partida"),
                    rs.getInt("id_localizacao"),
                    rs.getInt("id_origem"),
                    rs.getInt("id_destino"),
                    rs.getInt("id_rota"),
                    rs.getBoolean("Notificacao"),
                    rs.getString("Empresa")
                    ));

        return Autocarros;
    }

    public ArrayList<Autocarro> consultarAutocarros(String origem, String destino, String data){
        try {
            ArrayList<Autocarro> Autocarros = new ArrayList<>();
            String query = "SELECT * FROM Origem WHERE Origem LIKE '"+ origem + "'" + "AND Destino LIKE '"+ destino + "'" + "AND Data LIKE '"+ data + "'";
            Autocarros= getAutocarros(query);
            return Autocarros;
        }catch (SQLException e){
            System.err.println("Error updating Database");
            return null;
        }

    }

    public ArrayList<Autocarro> consultarAutocarrosNot(){
        try {
            ArrayList<Autocarro> AutocarrosNot = new ArrayList<>();
            String query = "SELECT * FROM Autocarro WHERE Notificacao LIKE 'true'";
            AutocarrosNot = getAutocarros(query);
            return AutocarrosNot;
        }catch (SQLException e){
            System.err.println("Error updating Database");
            return null;
        }
    }

    public ArrayList<Origem> consultarOrigem(String origem){
        try {
            ArrayList<Origem> Origens = new ArrayList<>();
            String query = "SELECT * FROM Origem WHERE Nome LIKE '"+ origem + "'";
            rs = stmt.executeQuery(query);

            while(rs.next())
                Origens.add(new Origem(
                        rs.getString("Nome"),
                        rs.getInt("id"),
                        rs.getString("Tempo_chegada"),
                        rs.getString("Tempo_partida")
                ));
            return Origens;
        }catch (SQLException e){
            System.err.println("Error updating Database");
            return null;
        }
    }

    public ArrayList<Destino> consultarDestino(String destino){
        try {
            ArrayList<Destino> Destinos = new ArrayList<>();
            String query = "SELECT * FROM Destino WHERE Nome LIKE '"+ destino + "'";
            rs = stmt.executeQuery(query);

            while(rs.next())
                Destinos.add(new Destino(
                        rs.getString("Nome"),
                        rs.getInt("id"),
                        rs.getString("Tempo_chegada"),
                        rs.getString("Tempo_partida")
                        ));

            return Destinos;
        }catch (SQLException e){
            System.err.println("Error updating Database");
            return null;
        }
    }

    public ArrayList<Autocarro> consultarAutocarrosFiltros(String origem, String destino, String data,String categoria,String Partida,String Chegada){
        try {
            ArrayList<Autocarro> AutocarrosFiltros = new ArrayList<>();

            String query = "SELECT * " +
                    "FROM Origem " +
                    "WHERE Origem LIKE '"+ origem + "'" +
                    "AND Destino LIKE '"+ destino + "'" +
                    "AND Data LIKE '"+ data + "'" +
                    "AND Partida LIKE '"+ Partida + "'" +
                    "AND Chegada LIKE '"+ Chegada + "'";

                    AutocarrosFiltros = getAutocarros(query);
            return AutocarrosFiltros;
        }catch (SQLException e){
            System.err.println("Error updating Database");
            return null;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
