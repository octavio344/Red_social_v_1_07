/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.modelo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Octavio
 */
public class Contacto extends Perfil{
    String username;

    public Contacto(String username, String nombre, String apellido, Integer edad, Date fechaNac, String email, Integer id) {
        super(nombre, apellido, edad, email);
        this.username = username;
    }
    
    
   
       
   

    public Contacto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String Username) {
        this.username = username;
    }

    
  
    
    
    
}
