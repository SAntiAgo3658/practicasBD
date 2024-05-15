package apuntes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.Resultset;

public class CreateTableBank {
   // Attributes
   String driver = "com.mysql.jdbc.Driver";
   String url = "jdbc:mysql://localhost:3306/bank";
   String login = "root";
   String password = "";
   String createTableBank = "CREATE TABLE banco (" +
         "client VARCHAR(100) NOT NULL, " +
         "password VARCHAR(20) NOT NULL, " +
         "balance Integer NOT NULL, " +
         "PRIMARY KEY(client))";
   Connection connection;
   Statement statement;
   String createInsertInto = "INSERT INTO banco " +
         "(client, password, balance) " +
         "VALUES " +
         "('José Antonio', 'c', 5)";

   String createSelect = "SELECT * FROM banco";

   public void CreateTableBank() {
   }

   public void initConnection() {
      try {
         Class.forName(driver);
      } catch (ClassNotFoundException e) {
         System.out.println(e.getMessage());
      }
      try {
         connection = DriverManager.getConnection(url, login, password);

      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
   }

   public void createStatement() {
      try {
         statement = connection.createStatement();
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
   }

   public void useStatement() {
      try {
         statement.executeUpdate(createTableBank);
      } catch (SQLException e) {
         System.out.println("al crear la tabla");
         System.out.println(e.getMessage());
      }
   }

   public void useStatementInsert() {
      try {
         statement.executeUpdate(createInsertInto);
      } catch (SQLException e) {
         System.out.println("al crear la tabla");
         System.out.println(e.getMessage());
      }
   }

   public void useSelect() {
      try {
         ResultSet vista = statement.executeQuery(createSelect);
         java.sql.ResultSetMetaData datosVista = vista.getMetaData();
         System.out.println("El nombre de la tabla es: " + datosVista.getTableName(1));
         while (vista.next()) {
            for (int i = 1; i <= datosVista.getColumnCount(); i++) {
               System.out.print("[" + datosVista.getColumnName(i) + " : " + vista.getString(i) + "] ");
            }
            System.out.println();
         }
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
   }

   public void closeConnection() {
      try {
         connection.close();
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
   }

} // end class