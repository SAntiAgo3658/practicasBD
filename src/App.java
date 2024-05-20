import java.sql.SQLException;

import apuntes.CreateTableBank;
import nuestroCRUD.MiCRUD;
import nuestroCRUD.MyColumn;
import nuestroCRUD.MyConstraint;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        MiCRUD prueba = new MiCRUD("bank");

        MyColumn[] columnas = new MyColumn[1];
        columnas[0] = new MyColumn();
        columnas[0].setColName("nombre");
        columnas[0].setColType("varchar(32)");
        MyConstraint[] restricciones = new MyConstraint[1];
        restricciones[0] = new MyConstraint(true);
        restricciones[0].setParams(new String[] {"pk_nombre", "nombre"});
        
        System.out.println(prueba.createTable("mi tabla", columnas , restricciones));

    }
}
