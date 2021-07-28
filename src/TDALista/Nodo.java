package TDALista;

public class Nodo<E> implements Position<E> {

	private E elemento;
	private Nodo<E> siguiente;

	public Nodo(E elem, Nodo<E> sig) {
		elemento = elem;
		siguiente = sig;
	}

	public Nodo(E elem) {
		this(elem, null);
	}

	public Nodo<E> getSiguiente() {
		return siguiente;
	}

	public void setElemento(E elem) {
		elemento = elem;
	}

	public void setSiguiente(Nodo<E> sig) {
		siguiente = sig;
	}

	public E element() {
		return elemento;
	}
}
