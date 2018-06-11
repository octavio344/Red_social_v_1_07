/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.negocio.exceptions;

/**
 *
 * @author Octavio
 */
public class BadCredentialsException extends Exception{

    public BadCredentialsException() {
    }

    public BadCredentialsException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public BadCredentialsException(Throwable thrwbl) {
        super(thrwbl);
    }

    public BadCredentialsException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
    
    
}
