/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.ModeloDAO;

import arg.edu.iua.ModeloDAO.exceptions.NoUserException;
import arg.edu.iua.ModeloDAO.exceptions.PersistenceException;
import arg.edu.iua.modelo.Contacto;
import arg.edu.iua.modelo.Publicacion;
import arg.edu.iua.modelo.Usuario;
import arg.edu.iua.modelo.exception.NoDataFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Octavio
 */
public interface PersistenciaInterface {
    
    public Usuario searchUser(String username) throws NoUserException,PersistenceException;
    
    public Usuario searchUserbyID(Integer id) throws NoUserException,PersistenceException;
    
    
    public String registrarU(Usuario u,String cpass,String fnac)throws SQLException,PersistenceException,NoDataFoundException;
    
    
    public void devolverContactos(Usuario u) throws SQLException;
    
    
    public ArrayList<Contacto> devolverSeguidores(Usuario u)throws SQLException;
    
    
    public ArrayList<Contacto> devolverNSeg(Usuario u) throws SQLException;
    
    
    public boolean seguirU(Usuario u,String Username)throws SQLException,PersistenceException;
    
     public boolean dejarDeSeguirU(Usuario u,String Username)throws SQLException,PersistenceException;
     
     public boolean realizarPubl(Usuario u,String pub,String genero)throws SQLException;
     
     
     public ArrayList<Publicacion> devolverPubSeg(Usuario u)throws SQLException;
     
     public boolean eliminarPub(Usuario u,int id)throws SQLException;
     
     public boolean modificarNombre(Usuario u,String nombre)throws SQLException;
     public boolean modificarApellido(Usuario u,String apellido) throws SQLException;
     public boolean modificarFechaNac(Usuario u,String fnac) throws SQLException;
      public boolean modificarUsername(Usuario u,String uname) throws SQLException ;
      public boolean modificarContraseña(Usuario u,String pass) throws SQLException;
      public boolean modificarEmail(Usuario u,String email) throws SQLException;
     
     
     public ArrayList<Publicacion> devolverPubBus(String busqueda)throws NoDataFoundException;
}
