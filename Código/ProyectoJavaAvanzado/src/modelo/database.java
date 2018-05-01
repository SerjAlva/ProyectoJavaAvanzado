/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LeSerge
 */
public class database {
    
    private String db = "dbtest";
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost/"+db;
    private Connection conn = null;
    
    public database(){
        this.url = "jdbc:mysql://localhost/"+this.db;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection( this.url, this.user , this.password );         
        }catch(SQLException e){
            System.err.println( e.getMessage() );
        }catch(ClassNotFoundException e){
            System.err.println( e.getMessage() );
        }
    }


    public Connection getConexion()
    {
        return this.conn;
    }    
}
