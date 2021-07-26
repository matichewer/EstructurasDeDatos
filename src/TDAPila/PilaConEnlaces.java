package TDAPila;


/**
 * Clase PilaConEnlaces
 * 
 * @author Matias David Schwerdt
 */

public class PilaConEnlaces<E> implements Stack<E> {

	protected Nodo<E> head;
	protected int tamanio;

	/**
	 * Crea una pila vacía con nodos.
	 */
	public PilaConEnlaces() {
		head = null;
		tamanio = 0;
	}

	/**
	 * @override
	 */
	public int size() {
		return tamanio;
	}

	/**
	 * @override
	 */
	public boolean isEmpty() {
		return tamanio == 0;
	}

	/**
	 * @override
	 */
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("La pila se encuentra vacía.");
		return head.getElemento();
	}

	/**
	 * @override
	 */
	public void push(E element) {
		Nodo<E> aux = new Nodo<E>(element, head);
		head = aux;
		tamanio++;
	}

	/**
	 * @override
	 */
	public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("La pila se encuentra vacía.");
		E aux = head.getElemento();
		head = head.getSiguiente();
		tamanio--;
		return aux;
	}
}