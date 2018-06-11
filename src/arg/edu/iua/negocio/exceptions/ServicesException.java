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
public class ServicesException extends Exception{

    public ServicesException() {
    }

    public ServicesException(String string) {
        super(string);
    }

    public ServicesException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ServicesException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ServicesException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
    
}
