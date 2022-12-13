package pt.isec.gps.tp.modelo.db;

import org.testng.annotations.Test;
import pt.isec.gps.tp.modelo.dados.Autocarro;
import pt.isec.gps.tp.modelo.dados.Paragem;


import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement stmt = null;

    @Test
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public static void closeStatement(Statement statement) {
        try{
            if (statement == null) return;
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void connect() {
        try {
            String url = "jdbc:sqlite:C:\\sqlite\\db\\DataBase_GPS.db";
            connection = DriverManager.getConnection(url);
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Test
    public static Connection connectTest() {

        Connection connection;
        try {
            String url = "jdbc:sqlite:C:\\sqlite\\db\\DataBase_GPS.db";
            connection = DriverManager.getConnection(url);
            return connection;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void disconnect(Connection connection) throws SQLException {
        connection.close();
    }
    public static void clsoeStatement(Statement statement) throws SQLException {
        statement.close();
    }


    public ArrayList<Autocarro> getAutocarros() throws SQLException {
        Integer id;
        String query = "SELECT * FROM Autocarro";
        ArrayList<Autocarro> Autocarros = new ArrayList<>();
        int bd;
        Boolean be;
        rs = stmt.executeQuery(query);
        Autocarro autocarro = null;
        while (rs.next()) {
            id = rs.getInt("id");
            bd = rs.getInt("Estado");
            be = bd == 1;
            autocarro = new Autocarro(
                    id,
                    rs.getString("Nome"),
                    rs.getInt("Lotacao"),
                    be,
                    rs.getBoolean("Notificacao"),
                    rs.getString("Empresa"),
                    rs.getString("Latitude"),
                    rs.getString("Longitude")
            );
            System.out.println("AUTOCARRO " + id + autocarro);
            Autocarros.add(autocarro);
        }
        System.out.println("AUTOCARROS " + Autocarros);
        for(Autocarro a : Autocarros)
            getParagens(a);
        System.out.println("AUTOCARROS COM TRAJETOS" + Autocarros);
        return Autocarros;
    }

    private int getIdTrajeto(Autocarro autocarro){
        try {
            String query = "SELECT id,Preco_Bilhete FROM Trajeto WHERE id_autocarro= " + autocarro.getId_autocarro();
            rs = stmt.executeQuery(query);
            int id_trajeto = rs.getInt("id");
            float preco = rs.getFloat("Preco_Bilhete");
            autocarro.setPreco(preco);

            return id_trajeto;
        }catch (SQLException e){
            System.err.println("Error updating Database");
        }
        return 0;
    }
    public ArrayList<String> getParagensToArrayList() {
        ArrayList<String> paragens = new ArrayList<>();
        try {
            String query = "SELECT * FROM Paragem";
            rs = stmt.executeQuery(query);
            while(rs.next()) {

                paragens.add(rs.getString("Nome"));
            }
        return paragens;
        }catch (SQLException e){
            System.err.println(e);
        }
        return paragens;
    }

    private void getParagens(Autocarro autocarro) {
        ArrayList<Paragem> paragens = new ArrayList<>();
        try {
            int id_trajeto = getIdTrajeto(autocarro);
            String query = "SELECT * FROM Paragem_Trajeto WHERE id_trajeto = " + id_trajeto;
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                Paragem paragem = new Paragem(rs.getString("nome_paragem"),
                        rs.getString("Hora_Partida"),
                        rs.getString("Hora_Chegada"));
                System.out.println(paragem);
                paragens.add(paragem);
            }
            autocarro.setTrajeto(paragens);
        }catch (SQLException e){
            System.err.println(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
