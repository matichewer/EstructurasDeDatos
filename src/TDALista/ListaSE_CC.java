package TDALista;

import java.util.Iterator;


/*
 * ListaSE_CC:
	 * Lista Simplemente Enlazada
	 * 		 Con un Centinela (nodo dummy)
 */
public class ListaSE_CC<E> implements PositionList<E> {
	private Nodo<E> head;
	private int tamanio;

	public ListaSE_CC() {
		head = new Nodo<E>(null,null);
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
	public Position<E> first() throws EmptyListException {
		if (tamanio == 0)
			throw new EmptyListException("first(): Quiere ejecutar first con una lista vacia");
		return head.getNext();
	}

	@Override
	public Position<E> last() throws EmptyListException { // O(N)
		if (tamanio == 0)
			throw new EmptyListException("last(): Quiere ejecutar last con una lista vacia");
		Nodo<E> aux = head.getNext();
		while (aux.getNext() != null)
			aux = aux.getNext();
		return aux;
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

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> aux = checkPosition(p);
		if (aux.getNext() == null)
			throw new BoundaryViolationException("next(p): el ultimo no tiene siguiente");
		return aux.getNext();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> aux = checkPosition(p); 	// O(N)
		if (aux == head.getNext())
			throw new BoundaryViolationException("el primero no tiene previo");
		Nodo<E> n = head.getNext();
		while (n.getNext() != aux && n.getNext() != null)
			n = n.getNext();
		if (n.getNext() == null)
			throw new InvalidPositionException("posicion no pertenece");
		return n;
	}

	@Override
	public void addFirst(E element) {
		Nodo<E> nuevo = new Nodo<E>(element, head.getNext());
		head.setNext(nuevo);
		tamanio++;
	}

	@Override
	public void addLast(E element) {  // O(N)
		Nodo<E> nuevo = new Nodo<E>(element);		
		if (tamanio == 0) {
			head.setNext(nuevo);
		} else {		
			Nodo<E> aux = head.getNext();
			while (aux.getNext() != null)
				aux = aux.getNext();
			aux.setNext(nuevo);
		}
		tamanio++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> aux = checkPosition(p);
		Nodo<E> nuevo = new Nodo<E>(element, aux.getNext());
		aux.setNext(nuevo);
		tamanio++;
		// O(1)
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		Nodo<E> nuevo = new Nodo<E>(element, n);
		if (n == head.getNext())
			head.setNext(nuevo);
		else {
			Nodo<E> cursor = head.getNext();
			while (cursor.getNext() != n && cursor.getNext() != null)
				cursor = cursor.getNext();
			if (cursor.getNext() == null)
				throw new InvalidPositionException("addBefore(p): la posicion no pertenece a la lista");
			cursor.setNext(nuevo);
		}
		tamanio++;
		// T addBefore(n) = O(1) + max(TaddFirst(n), Tprev(n)) = O(n)
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if (tamanio == 0)
			throw new InvalidPositionException("remove(p): posicion invalida");
		Nodo<E> aux = checkPosition(p);
		E oldElement = aux.element();
		if (aux == head.getNext())
			head.setNext(aux.getNext());
		else {
			Nodo<E> cursor = head.getNext();
			while (cursor.getNext() != aux && cursor.getNext() != null)
				cursor = cursor.getNext();
			if (cursor.getNext() == null)
				throw new InvalidPositionException("remove(p): la posicion no pertenece a la lista");
			cursor.setNext(aux.getNext());
		}
		tamanio--;
		return oldElement;
		// Tremove(n) = O(1) + Tfirst(n) + max(O(1), Tprev(n)+O(1))= O(n)

	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if( tamanio == 0 )
			throw new InvalidPositionException("set(p): la lista se encuentra vacia");
		Nodo<E> nodo = checkPosition(p);
		E oldElement = nodo.element();
		nodo.setElement(element);
		return oldElement;
		// O(1)
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
		// O(1)
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> iterable = new ListaSE_CC<Position<E>>();
		Nodo<E> actual = head.getNext();
		while (actual != null) {
			iterable.addLast(actual);
			actual = actual.getNext();
		}
		return iterable;
		// O(N)
	}
}
