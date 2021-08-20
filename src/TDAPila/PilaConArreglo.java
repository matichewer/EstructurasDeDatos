package TDAPila;


/**
 * Clase PilaConArreglo
 * Implementacion de una pila generica usando un arreglo
 */

public class PilaConArreglo<E> implements Stack<E> {

	protected int tamanio;
	protected E[] datos;
	
	/**
	 * Crea una pila de tamanio indicado por parametro.
	 * @param max tamanio maximo que puede tener la pila.
	 */
	public PilaConArreglo(int max) {
		tamanio = 0;
		datos = (E[]) new Object[max];
	}
	
	/**
	 * Crea una pila con un tamanio maximo de 20 componentes.
	 */
	public PilaConArreglo() {
		this(20);
	}
	
	@Override
	public int size() {
		return tamanio;
	}
	
	@Override
	public boolean isEmpty() {
		return tamanio == 0;
	}

	@Override
	public E top()throws EmptyStackException{
		if( tamanio == 0 ) 
			throw new EmptyStackException("La pila se encuentra vaci�a.");
		return datos[tamanio-1];		
	}

	@Override
	public void push(E element) {
		if( tamanio == datos.length )
			agrandarPila();
		datos[tamanio++] = element;		
	}
	
	/**
	 * Copia el arreglo a un arreglo nuevo que tiene el doble de tamanio.
	 */
	private void agrandarPila() {
		E[] aux = (E[]) new Object[2*datos.length];
		for(int i=0; i<tamanio; i++) {
			aux[i] = datos[i];
		}	
		datos = aux;
	}

	@Override
	public E pop() throws EmptyStackException{
		if( tamanio == 0 )
			throw new EmptyStackException("La pila se encuentra vaci�a.");
		E aux = datos[--tamanio];
		datos[tamanio] = null;
		return aux;
	}			
}