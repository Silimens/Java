import by.gsu.pms.Connector;
import by.gsu.pms.Logic;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateTables {
    public static void main(String[] args) {
        Connector.init("products", "root", "root");
        try (Connection connection = Connector.getConnection()) {
            Logic.createTables(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
