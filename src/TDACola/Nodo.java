package TDACola;

public class Nodo<E> {

	private E elemento;
	private Nodo<E> siguiente;

	// Constructores:
	public Nodo(E item, Nodo<E> sig) {
		elemento = item;
		siguiente = sig;
	}

	public Nodo(E item) {
		this(item, null);
	}

	// Setters:
	public void setElemento(E elem) {
		elemento = elem;
	}

	public void setSiguiente(Nodo<E> sig) {
		siguiente = sig;
	}

	// Getters:
	public E getElemento() {
		return elemento;
	}

	public Nodo<E> getSiguiente() {
		return siguiente;
	}
}