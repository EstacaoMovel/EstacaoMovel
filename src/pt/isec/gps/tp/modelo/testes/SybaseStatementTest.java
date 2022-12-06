package pt.isec.gps.tp.testes;

/*
import java.sql.Connection;
import java.sql.Statement;
import org.junit.jupiter.api.test;
import org.junit.jupiter.api;
import pt.isec.gps.tp.modelo.db.DataBase;

public class SybaseStatementTest {

    Connection connection;
    DataBase db;
    SybaseStatementTest(DataBase db){
        this.db = db;
    }
    @Before
    public void before() {
        connection = db.getConnection();
    }

    @After
    public void after() {
        db.closeConnection(connection);
    }

    @Test
    public void closeStatementShouldCloseStatement() {
        Statement statement = connection.createStatement();
        SybaseDBConnection.closeStatement(statement);
        assertTrue(statement.isClosed());
    }

    @Test
    public void closeStatementWithNullShouldNotThrow() {
        SybaseDBConnection.closeStatement(null);
    }

}
*/