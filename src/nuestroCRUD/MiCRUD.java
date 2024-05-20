package nuestroCRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MiCRUD {
   // atributtes
   private final String DRIVER = "com.mysql.cj.jdbc.Driver";
   private String url = "jdbc:mysql://localhost:3306/";
   private String user = "root";
   private String password = "";
   private Connection connection;
   private Statement statement;

   // constructor
   public MiCRUD(String baseDeDatos) {
      this.url = this.url.concat(baseDeDatos);
   };

   public boolean initDriver() {
      try {
         Class.forName(DRIVER);
         return true;
      } catch (ClassNotFoundException e) {
         return false;
      }
   }

   public boolean initConnection() {
      this.connection = null;
      try {
         this.connection = DriverManager.getConnection(url, user, password);
         return true;
      } catch (SQLException e) {
         return false;
      }
   }

   public boolean closeConnection() {
      try {
         this.connection.close();
         return true;
      } catch (SQLException e) {
         return false;
      } catch (NullPointerException e) {
         return false;
      }
   }

   public boolean createStatement() {
      try {
         this.statement = connection.createStatement();
         return true;
      } catch (SQLException e) {
         return false;
      } catch (NullPointerException e) {
         return false;
      }
   }

   public boolean useStatemente(String query) {
      try {
         return (0 == this.statement.executeUpdate(query));
      } catch (SQLException e) {
         return false;
      }
   }

   // create
   public String createTable(String name, MyColumn[] columns, MyConstraint[] constraints) {
      if (columns == null || constraints == null) {
         return null;
      } else {
         for (MyColumn colActual : columns) {
            if (colActual == null) {
               return null;

            }

            for (MyConstraint constraintActual : constraints) {
               if (constraintActual == null) {
                  return null;

               }

            }

         }

      }

      String myQuery = "CREATE TABLE ".concat(name) + " (";
      for (int i = 0; i < columns.length; i++) {
         myQuery = myQuery + columns[i].getColName() + " " + columns[i].getColType();
         if (columns[i].isNulleable()) {
            myQuery = myQuery + ", ";
         } else {
            myQuery = myQuery + " NOT NULL" + ", ";
         }
         // There´s always at least one constraint.
      }

      for (int i = 0; i < constraints.length - 1; i++) {
         myQuery = myQuery + "CONSTRAINT " + constraints[i].getParams()[0];

         if (constraints[i].getParams().length == 2) { // Primary
            myQuery = myQuery + " PRIMARY KEY (";
            myQuery = myQuery + constraints[i].getParams()[1] + ")";

         } else { // Foreign
            myQuery = myQuery + " FOREIGN KEY (";
            myQuery = myQuery + constraints[i].getParams()[1] + ") ";
            myQuery = myQuery + " REFERENCES ";
            myQuery = myQuery + constraints[i].getParams()[2] + "(";
            myQuery = myQuery + constraints[i].getParams()[3] + ")";

         }

         myQuery = myQuery + ", ";

      }

      myQuery = myQuery + "CONSTRAINT " + constraints[constraints.length - 1].getParams()[0];

      if (constraints[constraints.length - 1].getParams().length == 2) { // Primary
         myQuery = myQuery + " PRIMARY KEY (";
         myQuery = myQuery + constraints[constraints.length - 1].getParams()[1] + ")";

      } else { // Foreign
         myQuery = myQuery + " FOREIGN KEY (";
         myQuery = myQuery + constraints[constraints.length - 1].getParams()[1] + ") ";
         myQuery = myQuery + " REFERENCES ";
         myQuery = myQuery + constraints[constraints.length - 1].getParams()[2] + "(";
         myQuery = myQuery + constraints[constraints.length - 1].getParams()[3] + ")";

      }

      myQuery = myQuery + ");";

      return myQuery;

   }
   // read
   // update
   // delete
}
