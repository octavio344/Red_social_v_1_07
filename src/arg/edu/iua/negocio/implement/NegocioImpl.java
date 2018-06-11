/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.negocio.implement;

import arg.edu.iua.ModeloDAO.Implements.UserBDImp;
import arg.edu.iua.ModeloDAO.exceptions.NoUserException;
import arg.edu.iua.ModeloDAO.exceptions.PersistenceException;
import arg.edu.iua.modelo.Contacto;
import arg.edu.iua.modelo.Publicacion;
import arg.edu.iua.modelo.Usuario;
import arg.edu.iua.modelo.exception.NoAgeException;
import arg.edu.iua.modelo.exception.NoDataFoundException;
import arg.edu.iua.modelo.util.ErrorDeSintaxis;
import arg.edu.iua.negocio.NegocioInterface;
import arg.edu.iua.negocio.exceptions.BadCredentialsException;
import arg.edu.iua.negocio.exceptions.NoPasswordMatchException;
import arg.edu.iua.negocio.exceptions.ServicesException;
import arg.edu.iua.presentacion.Context;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Octavio
 */
public class NegocioImpl implements NegocioInterface{
    private UserBDImp db=new UserBDImp();
    
    public NegocioImpl() {
        //this.ct=ct;
    }
    
    
    
    @Override
    public Usuario iniciarSecion(String username, String password) throws BadCredentialsException, ServicesException, SQLException {
        try {
            Usuario u=db.searchUser(username);
            if(u.getPassword().equals(password)){
                db.devolverContactos(u);
                return u;
            }
            else{
                throw new BadCredentialsException();
            }
        } catch (NoUserException ex) {
                throw new BadCredentialsException();
        } catch (PersistenceException ex) {
            throw new ServicesException();
        } catch (SQLException ex) {
          throw  new SQLException();
        }
    }

    @Override
    public boolean existeUsuario(String username) throws ServicesException {
        try {
           Usuario u=db.searchUser(username);
           return true;
        } catch (NoUserException ex) {
           return false;
        } catch (PersistenceException ex) {
         throw new ServicesException();
        }
    }

    @Override
    public boolean registrarUsuario(Usuario u,String cpass,String fnac) throws ServicesException, NoDataFoundException,NoPasswordMatchException,NoAgeException {
        boolean b=false;
       ErrorDeSintaxis confi=u.chequeaCampos(cpass);
        System.out.println(confi);
       if(confi.getCampo().equals("ok")){
           if(u.getPassword().equals(cpass)){
               
               try {
                   db.registrarU(u,cpass,fnac);
                   b=true;
               } catch (SQLException ex) {
                   throw new ServicesException(ex.getMessage());
               }
               
               return b;
           }else{
               throw new NoPasswordMatchException("Las contraseñas no coinciden");
           }
           
       }else{
           throw new NoDataFoundException(String.format(confi.getCampo(),"El campo %s es obligatorio"));
       }
    }

    @Override
    public void devolverContact(Usuario u) throws SQLException {
        db.devolverContactos(u);
    }

    @Override
    public ArrayList<Contacto> devolverSeg(Usuario u) throws SQLException {
     return db.devolverSeguidores(u);
    }

    @Override
    public ArrayList<Contacto> devolverNSeg(Usuario u) throws SQLException {
     return db.devolverNSeg(u);
    }

    @Override
    public boolean seguirU(Usuario u, String uname) throws SQLException, PersistenceException {
     return db.seguirU(u, uname);
    }

    @Override
    public boolean dejarDeSeguirU(Usuario u, String uname) throws SQLException, PersistenceException {
     return db.dejarDeSeguirU(u, uname);
    }

    @Override
    public boolean realizarPub(Usuario u, String pub, String genero) throws SQLException {
     return db.realizarPubl(u, pub, genero);
    }

    @Override
    public ArrayList<Publicacion> devolverPubSeg(Usuario u) throws SQLException {
     return db.devolverPubSeg(u);
    }

    @Override
    public boolean eleminarPub(Usuario u, int id) throws SQLException {
     return db.eliminarPub(u, id);
    }

    @Override
    public boolean modificarDatos(Usuario u, String nombre, String apellido, String username, String contraseña, String fnac, String email) throws SQLException {
        if(nombre.equals("")||nombre.trim().length()<4){
            
        }else{
            db.modificarNombre(u, nombre);
        }
        if(apellido.equals("")||apellido.trim().length()<4){
            
        }else{
            db.modificarApellido(u, apellido);
        }
        if(username.equals("")||username.trim().length()<4){
            
        }else{
            db.modificarUsername(u, username);
        }
        if(contraseña.equals("")||contraseña.trim().length()<4){
            
        }else{
            db.modificarContraseña(u, contraseña);
        }if(fnac.equals("")||fnac.trim().length()<4){
            
        }else{
            db.modificarFechaNac(u, fnac);
        }
        if(email.equals("")||email.trim().length()<4){
            
        }else{
            db.modificarEmail(u, email);
        }
        return true;
    }

    @Override
    public Usuario buscarPorId(Usuario u) throws SQLException {
        try {
            return db.searchUserbyID(u.getId());
        } catch (NoUserException ex) {
            throw new SQLException();
        } catch (PersistenceException ex) {
            throw new SQLException();
        }
    }

    @Override
    public ArrayList<Publicacion> devolverPubBus(String busqueda) throws NoDataFoundException {
       return db.devolverPubBus(busqueda);
    }

 
    
    
    
    
}
