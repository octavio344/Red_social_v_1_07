/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.ModeloDAO.Implements;

import arg.edu.iua.ModeloDAO.PersistenciaInterface;
import arg.edu.iua.ModeloDAO.exceptions.NoUserException;
import arg.edu.iua.ModeloDAO.exceptions.PersistenceException;
import arg.edu.iua.modelo.Contacto;
import arg.edu.iua.modelo.Publicacion;
import arg.edu.iua.modelo.Usuario;
import arg.edu.iua.modelo.exception.NoDataFoundException;
import arg.edu.iua.modelo.util.ErrorDeSintaxis;
import arg.edu.iua.presentacion.Contactos;
import arg.edu.iua.presentacion.Context;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Octavio
 */
public class UserBDImp implements PersistenciaInterface{
        Connection con=new Context().getCon();

    public UserBDImp() {
    }
    
        
        
    /*public UserBDImp(Context con){
       this.con=con;
    }*/
    
    @Override
    public Usuario searchUser(String username) throws NoUserException, PersistenceException {
      Usuario u=new Usuario();
      u=null;
      
      String str="select * from usuario where username=?";
      PreparedStatement ps;
      ResultSet rs;
      
        try {
            
           
            ps=con.prepareStatement(str);
            ps.setString(1, username);
            rs=ps.executeQuery();
            if(rs.next()){
                return rsUsuario(rs);
            }else{
                str="select * from usuario where email=?";
                ps=con.prepareStatement(str);
                ps.setString(1, username);
                rs=ps.executeQuery();
                if(rs.next()){
                    return rsUsuario(rs);
                }
                else{
                    throw new NoUserException(String.format("El usuario/email %s no existe", username));
                }
                
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
    
    
     @Override
    public Usuario searchUserbyID(Integer id) throws NoUserException, PersistenceException {
      Usuario u=new Usuario();
      u=null;
      
      String str="select * from usuario where idusuario=?";
      PreparedStatement ps;
      ResultSet rs;
      
        try {
            
           
            ps=con.prepareStatement(str);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            if(rs.next()){
                return rsUsuario(rs);
            }else{
                
                    throw new NoUserException(String.format("El usuario con id %d no existe", id));
                
                
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }   
    }
    
    
    
    
    private Usuario rsUsuario(ResultSet rs) throws SQLException{
        Usuario u=new Usuario();
        u.setApellido(rs.getString("Apellido"));
        u.setFechaNac(rs.getDate("Fecha_nacimiento"));
        u.setNombre(rs.getString("Nombre"));
        u.setPassword(rs.getString("Contraseña"));
        u.setUsername(rs.getString("Username"));
        u.setEmail(rs.getString("Email"));
        u.setId(rs.getInt("Idusuario"));
        return u;
    }

    @Override
    public String registrarU(Usuario u,String cpass,String fnac) throws  SQLException,NoDataFoundException {
        ErrorDeSintaxis e=new ErrorDeSintaxis();
        String estado=new String();
        try{
             e=u.chequeaCampos(cpass);
            try{
                String str="insert into usuario (Nombre,Apellido,Username,Contraseña,Fecha_nacimiento,email) values (?,?,?,?,?,?)";
                PreparedStatement ps=con.prepareStatement(str);
                ps.setString(1,u.getNombre());
                ps.setString(2,u.getApellido());
                ps.setString(3, u.getUsername());
                ps.setString(4, u.getPassword());
                ps.setString(5, fnac);
                ps.setString(6, u.getEmail());
                if(ps.executeUpdate() > 0){
                    estado="El usuario "+u.getUsername()+" se agrego";
                }
            }
            catch(SQLException ex){
                throw new SQLException(ex.getMessage());
            }
            
        }
        catch(NoDataFoundException ex){
                throw new NoDataFoundException();
        }
        return estado;
    }

    @Override
    public void devolverContactos(Usuario u) throws SQLException {
        ArrayList<Contacto> co=new ArrayList<Contacto>();
        
        
        String query="select u.Idusuario,nombre,apellido,username,edad,fecha_nacimiento,email,edad from usuario u \n" +
        "inner join contactolista ct on ct.idamigo=u.idusuario where exists(\n" +
        "select idamigo from contactolista ct2 where idusuario=? and ct2.idamigo=ct.idamigo);";
        
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1, u.getId());
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
            Contacto cn=new Contacto(rs.getString("username"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("edad"), rs.getDate("fecha_nacimiento"), rs.getString("email"), rs.getInt("idusuario"));
            
            boolean b=false;
        for(int i=0;i<co.size();i++){
            if(co.get(i).getUsername().equals(cn.getUsername())){
                b=true;
            }
        }
        if(b==false){
            co.add(cn);
        }
        
        }
       u.setLc(co);
    }

    @Override
    public ArrayList<Contacto> devolverSeguidores(Usuario u) throws SQLException {
      ArrayList<Contacto> lSeg=new ArrayList<Contacto>();
      boolean b=false;
      String query="select ct.Idusuario,nombre,apellido,username,edad,fecha_nacimiento,email from contactolista ct \n" +
        "inner join usuario u on ct.idusuario=u.idusuario where exists(\n" +
        "select idusuario from contactolista ct2 where idamigo=? and ct2.idamigo=ct.idamigo);";
      
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1, u.getId());
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
            Contacto cn=new Contacto(rs.getString("username"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("edad"), rs.getDate("fecha_nacimiento"), rs.getString("email"), rs.getInt("idusuario"));
            b=false;
        for(int i=0;i<lSeg.size();i++){
            
            if(lSeg.get(i).getUsername().equals(cn.getUsername())){
               
                b=true;
            }
        }
        if(b==false){
            lSeg.add(cn);
        }
        
        
        }
       
        
        return lSeg;
    }

    @Override
    public ArrayList<Contacto> devolverNSeg(Usuario u) throws SQLException {
     ArrayList<Contacto> lcns = new ArrayList<Contacto>();
     boolean b=false;
     
     String query="select u.Idusuario,nombre,apellido,username,edad,fecha_nacimiento,email from usuario u \n" +
    " where not exists(select idamigo from contactolista ct2 where idusuario=? and ct2.idamigo=u.idusuario ) and idusuario!=?";
     
     PreparedStatement ps=con.prepareStatement(query);
     ps.setInt(1, u.getId());
     ps.setInt(2, u.getId());
     ResultSet rs=ps.executeQuery();
     
     while(rs.next()){
            Contacto cn=new Contacto(rs.getString("username"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("edad"), rs.getDate("fecha_nacimiento"), rs.getString("email"), rs.getInt("idusuario"));
            b=false;
        for(int i=0;i<lcns.size();i++){
            
            if(lcns.get(i).getUsername().equals(cn.getUsername())){
               
                b=true;
            }
        }
        if(b==false){
            lcns.add(cn);
        }
     }
     
     return lcns; 
   }

    @Override
    public boolean seguirU(Usuario u,String uname) throws SQLException, PersistenceException {
        boolean b=false;
            try {
                String query="INSERT INTO userdb.contactolista (Idusuario,Idamigo) VALUES (?, ?)";
                PreparedStatement ps=con.prepareStatement(query);
               
                if(u.getId().equals(searchUser(uname).getId())){
                    throw new PersistenceException("Usted no puede seguirse a si mismo");
                }
                ps.setInt(1, u.getId());
                ps.setInt(2, searchUser(uname).getId());
                ps.executeUpdate();
                b=true;
            } catch (NoUserException ex) {
                throw new PersistenceException(ex.getMessage());
            }
            return b;
    }

    @Override
    public boolean dejarDeSeguirU(Usuario u,String uname) throws SQLException, PersistenceException {
        boolean b=false;
            try {
                String query="DELETE FROM userdb.contactolista WHERE (Idusuario= ?) and (Idamigo = ?)";
                PreparedStatement ps=con.prepareStatement(query);
                if(u.getId().equals(searchUser(uname).getId())){
                    throw new PersistenceException("Usted no puede eliminarse a si mismo");
                }
                ps.setInt(1, u.getId());
                ps.setInt(2, searchUser(uname).getId());
                int j=ps.executeUpdate();
                if(j==0){
                    throw new SQLException();
                }
                b=true;
            } catch (NoUserException ex) {
             throw new PersistenceException(ex.getMessage());
            }
            return b;
    }

   

    @Override
    public boolean realizarPubl(Usuario u, String pub, String genero) throws SQLException {
        boolean b=false;
        try{
        String query="INSERT INTO userdb.publicacion (idusuario, publicacion, genero) VALUES (?,?,?);";   
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1, u.getId());
        ps.setString(2, pub);
        ps.setString(3, genero);
        ps.executeUpdate();
        b=true;
        }catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
        return b;
    }

    @Override
    public ArrayList<Publicacion> devolverPubSeg(Usuario u) throws SQLException {
      ArrayList<Publicacion> lps = new ArrayList<Publicacion>();
        String query="select idpublicacion,username,publicacion,genero from publicacion p1 inner join usuario u  on u.idusuario=p1.idusuario where exists\n" +
        "(select p.idusuario from publicacion p inner join \n" +
        "contactolista ct on ct.Idamigo=p.idusuario where ct.idusuario=? and p1.idusuario=p.idusuario) or p1.idusuario=?";
        
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1, u.getId());
        ps.setInt(2, u.getId());
        ResultSet rs=ps.executeQuery();
         
        while(rs.next()){
            Publicacion p=new Publicacion(rs.getString("publicacion"),rs.getString("genero"),rs.getString("username"),rs.getInt("idpublicacion"));
            boolean b=false;
            for(int i=0;i<lps.size();i++){
            
            if(lps.get(i).getIdpublicacion().equals(p.getIdpublicacion())){
               
                b=true;
            }
        }
        if(b==false){
            lps.add(p);
        }
     }
     
     return lps; 
        }

    @Override
    public boolean eliminarPub(Usuario u, int id) throws SQLException {
        boolean b=false;
        String query="DELETE FROM userdb.publicacion WHERE (Idusuario=?) and (Idpublicacion=?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1, u.getId());
        ps.setInt(2, id);
        if(ps.executeUpdate()==0){
            throw new SQLException();
        }else{
            b=true;
        }
        return b;
        }

    @Override
    public boolean modificarNombre(Usuario u,String nombre) throws SQLException {
        boolean b=false;
        String query="UPDATE userdb.usuario SET Nombre=? WHERE (Idusuario = ?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, u.getId());
        if(ps.executeUpdate()==0){
            throw new SQLException();
        }
        else{
            b=true;
        }
        return b;
    }
    
    @Override
    public boolean modificarApellido(Usuario u,String apellido) throws SQLException {
        boolean b=false;
        String query="UPDATE userdb.usuario SET Apellido=? WHERE (Idusuario = ?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1, apellido);
        ps.setInt(2, u.getId());
        if(ps.executeUpdate()==0){
            throw new SQLException();
        }
        else{
            b=true;
        }
        return b;
    }
    
    @Override
    public boolean modificarFechaNac(Usuario u,String fnac) throws SQLException {
        boolean b=false;
        String query="UPDATE userdb.usuario SET Fecha_nacimiento=? WHERE (Idusuario = ?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1, fnac);
        ps.setInt(2, u.getId());
        if(ps.executeUpdate()==0){
            throw new SQLException();
        }
        else{
            b=true;
        }
        return b;
    }
    
   
    
    @Override
    public boolean modificarUsername(Usuario u,String uname) throws SQLException {
        boolean b=false;
        String query="UPDATE userdb.usuario SET Username=? WHERE (Idusuario = ?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1, uname);
        ps.setInt(2, u.getId());
        if(ps.executeUpdate()==0){
            throw new SQLException();
        }
        else{
            b=true;
        }
        return b;
    }
    
    @Override
    public boolean modificarContraseña(Usuario u,String pass) throws SQLException {
        boolean b=false;
        String query="UPDATE userdb.usuario SET Contraseña=? WHERE (Idusuario = ?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1, pass);
        ps.setInt(2, u.getId());
        if(ps.executeUpdate()==0){
            throw new SQLException();
        }
        else{
            b=true;
        }
        return b;
    }
    
    @Override
    public boolean modificarEmail(Usuario u,String email) throws SQLException {
        boolean b=false;
        String query="UPDATE userdb.usuario SET Email=? WHERE (Idusuario = ?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1, email);
        ps.setInt(2, u.getId());
        if(ps.executeUpdate()==0){
            throw new SQLException();
        }
        else{
            b=true;
        }
        return b;
    }

    @Override
    public ArrayList<Publicacion> devolverPubBus(String busqueda) throws NoDataFoundException {
            ArrayList<Publicacion> lp=new ArrayList<Publicacion>();
            try {
                
                
                String query="SELECT username,idpublicacion,publicacion,genero FROM userdb.publicacion p inner join usuario u on\n" +
                "u.idusuario=p.idusuario where publicacion like ?";
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, "%"+busqueda+"%");
                ResultSet rs=ps.executeQuery();
                
                while(rs.next()){
                       Publicacion p=new Publicacion(rs.getString("publicacion"),rs.getString("genero"),rs.getString("username"),rs.getInt("idpublicacion"));
                    boolean b=false;
                    for(int i=0;i<lp.size();i++){
            
                    if(lp.get(i).getIdpublicacion().equals(p.getIdpublicacion())){
               
                        b=true;
                 }
                }
                if(b==false){
                    lp.add(p);
                }
                }
            } catch (SQLException ex) {
               throw new NoDataFoundException(ex.getMessage());
            }
        
        return lp;
    }


}

    
   
    
    

