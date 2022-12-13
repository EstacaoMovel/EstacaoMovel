package pt.isec.gps.tp.testes;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import org.junit.BeforeClass;
import org.testng.annotations.Test;
import pt.isec.gps.tp.modelo.db.DataBase;

import static org.testng.AssertJUnit.assertTrue;

public class SybaseStatementTest {

    Connection connection;

    @Test
    public void before() {
        connection = DataBase.connectTest();
    }

    @Test
    public void after() {
        DataBase.closeConnection(connection);
    }
    @Test
    public void closeStatementShouldCloseStatement() throws SQLException {
        Statement statement = connection.createStatement();
        DataBase.closeStatement(statement);
        assertTrue(statement.isClosed());
    }

    @Test
    public void closeStatementWithNullShouldNotThrow() {
        DataBase.closeStatement(null);
    }
}
