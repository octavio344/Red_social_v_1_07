/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.modelo;

import java.util.Objects;

/**
 *
 * @author Octavio
 */
public class Publicacion {
    String publicacion;
    String genero;
    String username;
    Integer idpublicacion;

    public Publicacion() {
    }

    public Publicacion(String publicacion, String genero, String username, Integer idpublicacion) {
        this.publicacion = publicacion;
        this.genero = genero;
        this.username = username;
        this.idpublicacion = idpublicacion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdpublicacion() {
        return idpublicacion;
    }

    public void setIdpublicacion(Integer idpublicacion) {
        this.idpublicacion = idpublicacion;
    }

    
    
    

    public Publicacion(String publicacion, String genero) {
        this.publicacion = publicacion;
        this.genero = genero;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Publicacion other = (Publicacion) obj;
        if (!Objects.equals(this.publicacion, other.publicacion)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "@"+username+"           Genero:"+ genero+"\n                 Publicacion Nro"+idpublicacion+":"+ publicacion +  '.';
    }

   
    
    
    
}
