import nuestroCRUD.MiCRUD;
import nuestroCRUD.MyColumn;
import nuestroCRUD.MyConstraint;

public class App {
    public static void main(String[] args) {

        MiCRUD miCRUD = new MiCRUD("ferreteria");

        miCRUD.initDriver();
        miCRUD.initConnection();
        miCRUD.createStatement();

        MyColumn[] columns = new MyColumn[1];
        columns[1].setColName("lsk");
        columns[1].setColType("varchar(30)");

        MyConstraint constraint =  new MyConstraint(true);
        



        String[] vista = miCRUD.readBD(new String[] { "cliente.nombre as cliente", "empleado.nombre as empleado" },
                new String[] { "cliente", "empleado", "asesora" },
                "(cliente.id_cliente = asesora.id_cliente) AND (asesora.id_empleado = empleado.dni)");

        for (String string : vista) {
            System.out.println(string);

        }

        miCRUD.closeConnection();

    }

}
