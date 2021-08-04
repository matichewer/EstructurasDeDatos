package TDALista;

import java.util.Iterator;


/*
 * Lista Doblemente Enlazada
 * 		 Con Centinelas
 * 		 con Enlace a la Primer y Ultima posicion
 */
public class ListaDE_CC_EPyU<E> implements PositionList<E> {

	protected DNodo<E> header, trailer;
	protected int tamanio;

	public ListaDE_CC_EPyU() {
		header = new DNodo<E>(null, null, null);
		trailer = new DNodo<E>(header, null, null);
		header.setNext(trailer);
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
		if( tamanio == 0 )
			throw new EmptyListException("first(): la lista se encuentra vacia");
		return header.getNext();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("last(): la lista se encuentra vacia");
		return trailer.getPrev();
	}

	/**
	 * Chequea que la posicion sea correcta
	 * 
	 * @param p posicion a chequear
	 * @return nodo chequeado que sea valido
	 * @throws InvalidPositionException si la posicion es nula
	 */
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if( p == null )
			throw new InvalidPositionException("La posición es nula");
		if( p == header )
			throw new InvalidPositionException("La header no es una posicion valida");
		if( p == trailer )
			throw new InvalidPositionException("La trailer no es una posicion valida");
		if( p.element() == null )
			throw new InvalidPositionException("La posicion fue eliminada previamente");
		try {
			DNodo<E> n = (DNodo<E>) p;
			if ((n.getPrev() == null) || (n.getNext() == null))
				throw new InvalidPositionException("La posicion no tiene anterior o siguiente");
			return n;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("p no es de tipo Nodo E");
		}
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if (n.getNext() == trailer)
			throw new BoundaryViolationException("Next(p): el ultimo no tiene siguiente");
		return n.getNext();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if (n.getPrev() == header)
			throw new BoundaryViolationException("Prev: el primero no tiene anterior");
		return n.getPrev();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> nuevo = new DNodo<E>(header, element, header.getNext());
		header.getNext().setPrev(nuevo);
		header.setNext(nuevo);
		tamanio++;
	}

	@Override
	public void addLast(E element) {
		DNodo<E> nuevo = new DNodo<E>(trailer.getPrev(), element, trailer);
		trailer.getPrev().setNext(nuevo);
		trailer.setPrev(nuevo);
		tamanio++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(n, element, n.getNext());
		n.getNext().setPrev(nuevo);
		n.setNext(nuevo);
		tamanio++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(n.getPrev(), element, n);
		n.getPrev().setNext(nuevo);
		n.setPrev(nuevo);
		tamanio++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		E oldElement = n.element();
		n.getPrev().setNext(n.getNext());
		n.getNext().setPrev(n.getPrev());
		n.setPrev(null);
		n.setNext(null);
		tamanio--;
		return oldElement;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("set con lista vacia");
		DNodo<E> n = checkPosition(p);
		E oldElement = n.element();
		n.setElement(element);
		return oldElement;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> iterable = new ListaDE_CC_EPyU<Position<E>>();
		DNodo<E> cursor = header.getNext();
		while (cursor != trailer) {
			iterable.addLast(cursor);
			cursor = cursor.getNext();
		}
		return iterable;
	}
}
