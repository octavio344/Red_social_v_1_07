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
public class NoPasswordMatchException extends Exception{

    public NoPasswordMatchException() {
    }

    public NoPasswordMatchException(String string) {
        super(string);
    }

    public NoPasswordMatchException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public NoPasswordMatchException(Throwable thrwbl) {
        super(thrwbl);
    }

    public NoPasswordMatchException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
