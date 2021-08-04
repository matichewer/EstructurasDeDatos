package TDALista;

public class Nodo<E> implements Position<E> {

	private E element;
	private Nodo<E> next;

	public Nodo(E element, Nodo<E> next) {
		this.element = element;
		this.next = next;
	}

	public Nodo(E element) {
		this(element, null);
	}

	
	@Override
	public E element() {
		return element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	
	public Nodo<E> getNext() {
		return next;
	}

	public void setNext(Nodo<E> next) {
		this.next = next;
	}

}
