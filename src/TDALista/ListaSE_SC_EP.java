package TDALista;

import java.util.Iterator;


/*
 * ListaSE_SC_EP:
	 * Lista Simplemente Enlazada
	 * 		 Sin Centinelas
	 * 		 con Enlace directo a la Primer posicion
 */
public class ListaSE_SC_EP<E> implements PositionList<E> {
	
	protected Nodo<E> head;
	protected int tamanio;

	public ListaSE_SC_EP() {
		head = null;
		tamanio = 0;
	}

	public int size() {
		return tamanio;
	}

	public boolean isEmpty() {
		return tamanio == 0;
	}

	public Position<E> first() throws EmptyListException {
		if( head == null )
			throw new EmptyListException("first(): Quiere ejecutar first con una lista vacia");
		return head;
	}

	public Position<E> last() throws EmptyListException { // O(N)
		if( head == null )
			throw new EmptyListException("last(): Quiere ejecutar last con una lista vacia");
		Nodo<E> aux = head;
		while (aux.getNext() != null)
			aux = aux.getNext();
		return aux;
	}

	public Position<E> next(Position<E> p) throws BoundaryViolationException, InvalidPositionException {
		Nodo<E> aux = checkPosition(p);
		if (aux.getNext() == null)
			throw new BoundaryViolationException("next(p): el ultimo no tiene siguiente");
		return aux.getNext();
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
			if (p == null)
				throw new InvalidPositionException("Posición nula");
			if (p.element() == null)
				throw new InvalidPositionException("Posicion eliminada previamente");
			return (Nodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("La posicion no pertenece a la lista");
		}
	}

	public Position<E> prev(Position<E> p) throws BoundaryViolationException, InvalidPositionException {
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
		// O(n)	
	}

	public void addFirst(E element) {
		head = new Nodo<E>(element, head);
		tamanio++;
	}

	public void addLast(E element) {  // O(N)
		if( tamanio == 0 )   // if isEmpty(), then addFirst(e)
			head = new Nodo<E>(element, head);	
		else {
			Nodo<E> aux = head;
			while (aux.getNext() != null)
				aux = aux.getNext();
			aux.setNext(new Nodo<E>(element));
		}
		tamanio++;
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> aux = checkPosition(p);
		Nodo<E> nuevo = new Nodo<E>(element, aux.getNext());
		aux.setNext(nuevo);
		tamanio++;
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		try {
			if( p == first() )
				addFirst(element);
			else {
				Nodo<E> anterior = (Nodo<E>) prev(p);
				Nodo<E> nuevo = new Nodo<E>(element, n);
				anterior.setNext(nuevo);
				tamanio++;
			}
		} catch (EmptyListException e) {
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			e.printStackTrace();
		}
		// T addBefore(n) = O(1) + max(TaddFirst(n), Tprev(n)) = O(n)
	}

	public E remove(Position<E> p) throws InvalidPositionException {
		if( isEmpty() )
			throw new InvalidPositionException("remove(p): la lista se encuentra vacia");
		Nodo<E> n = checkPosition(p);
		E oldElement = p.element();
		if( n == head )
			head = head.getNext();
		else {
			try {
				Nodo<E> anterior = (Nodo<E>) prev(p);
				anterior.setNext(n.getNext());
			} catch (BoundaryViolationException e) {
				e.printStackTrace();
			}
		}
		tamanio--;
		return oldElement;
		// Tremove(n) = O(1) + Tfirst(n) + max(O(1), Tprev(n)+O(1))= O(n)
	}

	

	public E set(Position<E> p, E element) throws InvalidPositionException {
		if( isEmpty() )
			throw new InvalidPositionException("set(p): la lista se encuentra vacia");
		Nodo<E> n = checkPosition(p);
		E oldElement = p.element();
		n.setElement(element);
		return oldElement;
	}

	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
		// O(1)
	}

	public Iterable<Position<E>> positions() { // O(N)
		PositionList<Position<E>> iterable = new ListaSE_SC_EP<Position<E>>();
		Nodo<E> actual = head;
		while (actual != null) {
			iterable.addLast(actual);
			actual = actual.getNext();
		}
		return iterable;
		// O(N)		
	}	
}