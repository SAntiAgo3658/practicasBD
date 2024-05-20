import java.sql.SQLException;

import apuntes.CreateTableBank;
import nuestroCRUD.MiCRUD;
import nuestroCRUD.MyColumn;
import nuestroCRUD.MyConstraint;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        MiCRUD miCRUD = new MiCRUD("bank");

        MyColumn[] columnas = new MyColumn[2];
        columnas[0] = new MyColumn();
        columnas[0].setColName("nombre");
        columnas[0].setColType("varchar(32)");
        columnas[1] = new MyColumn();
        columnas[1].setColName("apellido");
        columnas[1].setColType("varchar(32)");
        columnas[1].setNulleable(true);
        MyConstraint[] restricciones = new MyConstraint[3];
        restricciones[0] = new MyConstraint(true);
        restricciones[0].setParams(new String[] {"pk_nombre", "nombre"});

        miCRUD.initDriver();
        miCRUD.initConnection();
        miCRUD.createStatement();
        String miQuery = miCRUD.createTable("enfermos", columnas, restricciones);
        
        if (miCRUD.useStatemente(miQuery)) {
            System.out.println("Creada tabla");
            
        } else {
            System.out.println("ERROR!!");
        }

        
        
        miCRUD.closeConnection();

    }
}
