package TDALista;

import java.util.Iterator;


/*  
 * ListaSE_SC_EPyU:
	 * Lista Simplemente Enlazada, 
	 * 		 Sin Centinelas,
	 * 		 con Enlace directo a la Primer y Ultima posicion 
 */
public class ListaSE_SC_EPyU<E> implements PositionList<E> {

	protected Nodo<E> head, tail; // cabeza, rabo
	protected int tamanio;

	public ListaSE_SC_EPyU() {
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
		return head == null;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if( tamanio == 0 )
			throw new EmptyListException("first(): no se puede obtener la primera posicion de una lista vacia");
		return head;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if ( tamanio == 0 )
			throw new EmptyListException("last(): no se puede obtener la ultima posicion de una lista vacia");
		return tail;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n = checkPosition(p);
		if( n.getNext() == null )
			throw new BoundaryViolationException("Next(p): el ultimo no tiene siguiente");
		return n.getNext();
	}

	/**
	 * Chequea que la posicion sea correcta
	 * 
	 * @param p posicion a chequear
	 * @return nodo chequeado que sea valido
	 * @throws InvalidPositionException si la posicion es nula
	 */
	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		try {
			if( p == null )
				throw new InvalidPositionException("Posicion nula");
			if( p.element() == null )
				throw new InvalidPositionException("Posicion eliminada previamente");
			return (Nodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Posicion no es de tipo Nodo E");
		}
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if( tamanio == 0 )
			throw new InvalidPositionException("prev(p): la lista se encuentra vacia");
		try {
			if( p == first() )
				throw new BoundaryViolationException("prev(p): el primero no tiene previo");
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		Nodo<E> n = checkPosition(p);
		Nodo<E> aux = head;
		while (aux.getNext() != n && aux.getNext() != null)
			aux = aux.getNext();
		if (aux.getNext() == null)
			throw new InvalidPositionException("prev(p): la posicion no pertenece a la lista");
		return aux;

	}

	@Override
	public void addFirst(E elem) {
		Nodo<E> n = new Nodo<E>(elem, head);
		head = n;
		if( tamanio == 0 )
			tail = n;
		tamanio++;
	}

	@Override
	public void addLast(E elem) {
		Nodo<E> n = new Nodo<E>(elem);
		if( tamanio == 0 ) {
			tail = n;
			head = n;
		} else {
			tail.setNext(n);
			tail = n;			
		}			
		tamanio++;
	}

	@Override
	public void addAfter(Position<E> p, E elem) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		Nodo<E> nuevo = new Nodo<E>(elem);
		nuevo.setNext(n.getNext());
		n.setNext(nuevo);
		if( n == tail )
			tail = nuevo;
		tamanio++;
	}

	@Override
	public void addBefore(Position<E> p, E elem) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		try {
			if( p == first() )
				addFirst(elem);
			else {
				Nodo<E> anterior = (Nodo<E>) prev(p);
				Nodo<E> nuevo = new Nodo<E>(elem, n);
				anterior.setNext(nuevo);
				tamanio++;
			}
		} catch (EmptyListException e) {
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if( isEmpty() )
			throw new InvalidPositionException("remove(p): la lista se encuentra vacia");
		Nodo<E> n = checkPosition(p);
		E oldElement = p.element();
		if( n == head ) { // head=tail
			head = head.getNext();
			if( tamanio == 1 )
				tail = null;
		}
		else {	
			try {
				Nodo<E> anterior = (Nodo<E>) prev(p);
				if( n == tail ) {
					anterior.setNext(null);
					tail = anterior;
				} else
					anterior.setNext(n.getNext());
			} catch (BoundaryViolationException e) {
				e.printStackTrace();
			}
		}
		tamanio--;
		return oldElement;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if( isEmpty() )
			throw new InvalidPositionException("set(p): la lista se encuentra vacia");
		Nodo<E> n = checkPosition(p);
		E oldElement = p.element();
		n.setElement(element);
		return oldElement;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}
	
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> iterable = new ListaSE_SC_EPyU<Position<E>>();
		Nodo<E> actual = head;
		while (actual != null) {
			iterable.addLast(actual);
			actual = actual.getNext();
		}
		return iterable;
	}
	
}
