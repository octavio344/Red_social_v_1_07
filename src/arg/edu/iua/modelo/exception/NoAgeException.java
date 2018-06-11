/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.modelo.exception;

/**
 *
 * @author Octavio
 */
public class NoAgeException extends Exception{

    public NoAgeException(String string) {
        super(string);
    }

    public NoAgeException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public NoAgeException() {
    }

    
    public NoAgeException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
