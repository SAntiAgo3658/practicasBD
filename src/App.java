import java.sql.SQLException;

import apuntes.CreateTableBank;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        CreateTableBank miTabla = new CreateTableBank();
        miTabla.initConnection();
        miTabla.createStatement();
        miTabla.useSelect();

        

        miTabla.closeConnection();

    }
}
