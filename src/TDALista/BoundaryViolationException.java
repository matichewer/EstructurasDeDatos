package TDALista;

/**
 * Class EmptyStackException
 * @author Matias David Schwerdt
 * Clase que modela la excepcion ante una operacion que excede los límites de la estructura
 */
public class BoundaryViolationException extends Exception{
	
	/**
	 * Inicializa una nueva excepcion.
	 * @param msg describe el origen de la excepcion
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}

}
