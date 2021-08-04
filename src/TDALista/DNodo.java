package TDALista;

public class DNodo<E> implements Position<E> {

	private E element;
	private DNodo<E> prev, next;

	public DNodo(DNodo<E> prev, E element, DNodo<E> next) {
		this.prev = prev;
		this.element = element;
		this.next = next;
	}
	
	public DNodo(E element) {
		this(null, element, null);
	}

	
	public DNodo<E> getPrev() {
		return prev;
	}
	
	public void setPrev(DNodo<E> prev) {
		this.prev = prev;
	}

	
	@Override
	public E element() {
		return element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	
	public DNodo<E> getNext() {
		return next;
	}
	
	public void setNext(DNodo<E> next) {
		this.next = next;
	}

}