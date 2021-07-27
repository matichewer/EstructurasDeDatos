package TDACola;

public class ColaEnlazada<E> implements Queue<E> {

	protected Nodo<E> head, tail;
	protected int tamanio;

	public ColaEnlazada() {
		head = null;
		tail = null;
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
	public E front() throws EmptyQueueException {
		if (tamanio == 0)
			throw new EmptyQueueException("La cola se encuentra vacia.");
		return head.getElemento();
	}

	@Override
	public void enqueue(E element) {
		Nodo<E> nodo = new Nodo<E>(element, null);
		if (tamanio == 0)
			head = nodo;
		else
			tail.setSiguiente(nodo);
		tail = nodo;
		tamanio++;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (tamanio == 0)
			throw new EmptyQueueException("La cola se encuentra vacia");
		E aux = head.getElemento();
		head = head.getSiguiente();
		tamanio--;
		if (tamanio == 0)
			tail = null;
		return aux;
	}
	
}
