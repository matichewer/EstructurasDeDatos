package TDALista;

/**
 * Class EmptyStackException
 * @author Matias David Schwerdt
 * Clase que modela la excepcion ante una operacion sobre una lista vacia
 */
public class EmptyListException extends Exception {
	
	/**
	 * Inicializa una nueva excepcion.
	 * @param msg describe el origen de la excepcion
	 */
	public EmptyListException(String msg) {
		super(msg);
	}
}
