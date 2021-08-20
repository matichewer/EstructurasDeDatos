package TDAPila;


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

	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		return tamanio == 0;
	}

	@Override
	public E top() throws EmptyStackException {
		if( tamanio == 0 )
			throw new EmptyStackException("La pila se encuentra vacía.");
		return head.getElemento();
	}

	@Override
	public void push(E element) {
		Nodo<E> aux = new Nodo<E>(element, head);
		head = aux;
		tamanio++;
	}

	@Override
	public E pop() throws EmptyStackException {
		if( tamanio == 0 )
			throw new EmptyStackException("La pila se encuentra vacía.");
		E aux = head.getElemento();
		head = head.getSiguiente();
		tamanio--;
		return aux;
	}
	
}