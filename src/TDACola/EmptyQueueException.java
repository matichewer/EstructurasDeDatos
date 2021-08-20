package TDACola;

/**
 * Clase EmptyQueueException
 * Clase que modela la excepcion ante una operacion sobre una cola vacia
 */

public class EmptyQueueException extends Exception{
	
	/**
	 * Inicializa una nueva excepcion.
	 * @param msg describe el origen de la excepcion
	 */
	public EmptyQueueException(String msg) {
		super(msg);
	}
}