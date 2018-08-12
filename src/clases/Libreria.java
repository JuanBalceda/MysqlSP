/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jbalceda
 */
public class Libreria {

    String driver = "com.mysql.jdbc.Driver";
    String cadena = "jdbc:mysql://localhost:3306/inventario?"
            + "useUnicode=true&"
            + "useJDBCCompliantTimezoneShift=true&"
            + "useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC";
    String usuario = "root";
    String clave = "";

    Connection conexion;

    public Libreria() {
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(cadena, usuario, clave);
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver...");
        } catch (SQLException e) {
            System.out.println("No se pudo conectar con la base de datos...");
        }
    }

    public void procedure(String nombre, int cantidad, String categoria) {
        try {
            CallableStatement procedure = conexion.prepareCall("CALL reg_prod(?, ?, ?)");
            procedure.setString("nombre", nombre);
            procedure.setInt("cantidad", cantidad);
            procedure.setString("categoria", categoria);
            procedure.execute();
        } catch (SQLException e) {
        }
    }
    
    public ResultSet listado(){
        ResultSet data = null;
        try {
            Statement statement = conexion.createStatement();
            data = statement.executeQuery("CALL cons_prod()");
        } catch (SQLException e) {
        }
        return data;
    }
}
