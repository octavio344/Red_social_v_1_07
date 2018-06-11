/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.ModeloDAO.exceptions;

/**
 *
 * @author Octavio
 */
public class NoUserException extends Exception{

    public NoUserException() {
    }

    public NoUserException(String string) {
        super(string);
    }

    public NoUserException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public NoUserException(Throwable thrwbl) {
        super(thrwbl);
    }

    public NoUserException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
