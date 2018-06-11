/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.modelo;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author curso
 */
public class Perfil {
    String nombre;
    String apellido;
    Integer edad;
    Date fechaNac;
    String email;
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
    Calendar cal=DateToCalendar(fechaNac);
    Calendar today = Calendar.getInstance();
     int diff_year = today.get(Calendar.YEAR) -  cal.get(Calendar.YEAR);
     int diff_month = today.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
      int diff_day = today.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH);
      
   //Si está en ese año pero todavía no los ha cumplido

       if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
            diff_year = diff_year - 1; 
        }
     
        return diff_year;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Perfil() {
    }

    public Perfil(String nombre, String apellido, Date fechaNac, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.email = email;
    }

  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

 
    
    
    public static Calendar DateToCalendar(Date fechaNac ) { 
 Calendar cal = null;
 try {   
  DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
  fechaNac = (Date)formatter.parse(fechaNac.toString()); 
  cal=Calendar.getInstance();
  cal.setTime(fechaNac);
  }
  catch (ParseException e)
  {
      System.out.println("Exception :"+e);  
  }  
  return cal;
 }

    public Perfil(String nombre, String apellido, Integer edad, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
    }
    
    
}
