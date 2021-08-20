package TDAPila;

/**
 * Class EmptyStackException 
 * Clase que modela la excepcion ante una operacion sobre una pila vacia
 */

public class EmptyStackException extends Exception{
	
	/**
	 * Inicializa una nueva excepcion.
	 * 
	 * @param msg describe el origen de la excepcion
	 */
	public EmptyStackException(String msg) {
		super(msg);
	}
}