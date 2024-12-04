/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package Persistencia;

import java.sql.*;

/**
 *
 * @author oscar
 */
public class ConexionMySQL {
    private ConexionMySQL instance;
    private Connection cnn;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String user = "root";
    private String pss = "";
    private String nom_bd = "biblioteca";
    private String url = "jdbc:mysql://localhost:3306/"+nom_bd;
        
    public ConexionMySQL(){
        try{
            Class.forName(driver);
            cnn = DriverManager.getConnection(this.url, this.user, this.pss);
            System.out.println("Conexion exitosa");
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Error en la conexion");
            System.out.println(e.getMessage());
        }
    }
    
    public ConexionMySQL getInstance(){
        if(instance == null){
            instance = new ConexionMySQL();
        }
        return instance;
    }

    public Connection getCnn() {
        return cnn;
    }
    
    public void cerrarConexion(){
        try{
         if(cnn != null){
            if(! cnn.isClosed()){
                cnn.close();
            }
        }   
        }catch(SQLException ex){
            System.out.println("excepcion en la base de datos: "
            + ex.getMessage()
            );
        }
    }
}