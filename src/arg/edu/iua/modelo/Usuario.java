/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.modelo;

import arg.edu.iua.modelo.exception.NoAgeException;
import arg.edu.iua.modelo.exception.NoDataFoundException;
import arg.edu.iua.modelo.util.ErrorDeSintaxis;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author curso
 */
public class Usuario extends Perfil {
    String username;
    String password;
    ArrayList<Contacto> lc=new ArrayList<Contacto>();
     ArrayList<Publicacion> lp=new ArrayList<Publicacion>();

    public ArrayList<Contacto> getContactos() {
        return lc;
    }

    public ArrayList<Contacto> getLc() {
        return lc;
    }

    public void setLc(ArrayList<Contacto> lc) {
        this.lc = lc;
    }

    public ArrayList<Publicacion> getLp() {
        return lp;
    }

    public void setLp(ArrayList<Publicacion> lp) {
        this.lp = lp;
    }

    
    
    public void añdirContacto(Contacto c){
        lc.add(c);
    }

    public Contacto devolverCont(String usr){
        for(int i=0;i<lc.size();i++){
            if(lc.get(i).getUsername().equals(usr)==true){
                return lc.get(i);
            }
        }
        return null;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    

    public Usuario() {
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        
    }

  

    public Usuario(String username, String password, String nombre, String apellido, Date fnac, String email) {
        super(nombre, apellido,fnac, email);
        this.username = username;
        this.password = password;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

  
    public ErrorDeSintaxis chequeaCampos(String cpass) throws NoDataFoundException{
        if(getNombre()==null||getNombre().trim().length()<3){
            return new ErrorDeSintaxis("nombre","El campo nombre es obligatorio");
        }
        if(getApellido()==null||getApellido().trim().length()<3){
            return new ErrorDeSintaxis("apellido","El campo apellido es obligatorio");
        }
        if(getUsername()==null||getUsername().trim().length()<4){
            return new ErrorDeSintaxis("username","El campo username debe contener al menos 4 caracteres");
        }
        if(getPassword()==null||getPassword().trim().length()<6){
            return new ErrorDeSintaxis("password", "La contraseña debe tener al menos 6 caracteres");
        }
        if(cpass==null||getPassword().trim().length()<6){
            return new ErrorDeSintaxis("Conf password","El campo reingresar contraseña es obligatorio");
        }
        if(getEmail()==null||getEmail().trim().length()<2){
            return new ErrorDeSintaxis("Email","El campo email es obligatorio");
        }
        else{
            return  new ErrorDeSintaxis("ok","todo ok");
        }
        
        
        
        
    }
    
    

   public String cheuqueaEdad(int edad)throws NoAgeException{
            String str=null;
            
            if(edad<16){
                throw new NoAgeException();
            }
            else{
                return str;
            }
        }

    
    
    
}
