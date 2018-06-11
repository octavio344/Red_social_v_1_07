/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.presentacion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author curso
 */
public class Context {
   Connection con;
   
    public Context(){
    Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/UserDB","root","root");
            this.con=con;
           // System.out.println(con);
        } catch (Exception ex) {
           
        }
}

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
   /* public NegocioImpl construirNe(){
        return new NegocioImpl(ct);
    }*/
    
}
