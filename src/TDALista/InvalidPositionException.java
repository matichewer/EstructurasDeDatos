package TDALista;

/**
 * Class EmptyStackException
 * 
 * @author Matias David Schwerdt
 * Clase que modela la excepcion ante una operacion que utiliza una posicion inválida
 */
public class InvalidPositionException extends Exception{

	/**
	 * Inicializa una nueva excepcion.
	 * @param msg describe el origen de la excepcion
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
