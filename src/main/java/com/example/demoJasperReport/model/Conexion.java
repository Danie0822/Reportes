package com.example.demoJasperReport.model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection openDB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://Pokemones.mssql.somee.com;"
                    + "databaseName=Pokemones;"
                    + "user=AllanFuentes_SQLLogin_1;"
                    + "password=8gmxgqhcwo;"
                    + "trustServerCertificate=true";
            Connection con = DriverManager.getConnection(connectionUrl);
            return con;
        } catch (SQLException E) {
            System.out.println("ERROR DE CONEXION xd");
            int x = 1;
        }
        catch(ClassNotFoundException cnfex){
            System.out.println("error en clase conexion");
            int x = 1;
        }
        return null;
    }

}
