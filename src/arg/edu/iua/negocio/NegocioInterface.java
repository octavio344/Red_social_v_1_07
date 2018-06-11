/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.negocio;

import arg.edu.iua.ModeloDAO.exceptions.PersistenceException;
import arg.edu.iua.modelo.Contacto;
import arg.edu.iua.modelo.Publicacion;
import arg.edu.iua.modelo.Usuario;
import arg.edu.iua.modelo.exception.NoAgeException;
import arg.edu.iua.modelo.exception.NoDataFoundException;
import arg.edu.iua.negocio.exceptions.BadCredentialsException;
import arg.edu.iua.negocio.exceptions.NoPasswordMatchException;
import arg.edu.iua.negocio.exceptions.ServicesException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Octavio
 */
public interface NegocioInterface {
        
    public Usuario iniciarSecion(String username,String password) throws BadCredentialsException,ServicesException,SQLException;

    public boolean existeUsuario(String username) throws ServicesException;
    
    public boolean registrarUsuario(Usuario u,String cpass,String fnac) throws ServicesException,NoDataFoundException,NoPasswordMatchException,NoAgeException;
    
    public void devolverContact(Usuario u)throws SQLException;
    
    public ArrayList<Contacto> devolverSeg(Usuario u)throws SQLException;
    
    public ArrayList<Contacto> devolverNSeg(Usuario u) throws SQLException;
    
    public Usuario buscarPorId(Usuario u)throws SQLException;
    
    public boolean seguirU(Usuario u,String uname)throws SQLException, PersistenceException;
    
    public boolean dejarDeSeguirU(Usuario u,String uname)throws SQLException,PersistenceException;
    
    public boolean realizarPub(Usuario u,String pub,String genero)throws SQLException;
    
    public ArrayList<Publicacion> devolverPubSeg(Usuario u)throws SQLException;
    
    public boolean eleminarPub(Usuario u,int id)throws SQLException;
    
        public ArrayList<Publicacion> devolverPubBus(String busqueda)throws NoDataFoundException;
    
    public boolean modificarDatos(Usuario u,String nombre,String apellido,String username,String contraseña,String fnac,String email)throws SQLException;
}
