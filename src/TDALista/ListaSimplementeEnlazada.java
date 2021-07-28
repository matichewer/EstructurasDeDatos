package TDALista;

import java.util.Iterator;

public class ListaSimplementeEnlazada<E> implements PositionList<E> {

	protected Nodo<E> cabeza;
	protected int tamanio;

	public ListaSimplementeEnlazada() {
		cabeza = null;
		tamanio = 0;
	}

	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		return cabeza == null;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if (cabeza == null)
			throw new EmptyListException("first(): Quiere ejecutar first con una lista vacia");
		return cabeza;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (cabeza == null)
			throw new EmptyListException("last(): Quiere ejecutar last con una lista vacia");
		Nodo<E> n = cabeza;
		while (n.getSiguiente() != null)
			n = n.getSiguiente();
		return n;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n = checkPosition(p);
		if( n.getSiguiente() == null )
			throw new BoundaryViolationException("Next(p): el ultimo no tiene siguiente");
		return n.getSiguiente();
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
		try {
			if( p == first() )
				throw new BoundaryViolationException("prev(p): el primero no tiene previo");
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		Nodo<E> n = checkPosition(p);
		Nodo<E> aux = cabeza;
		while (aux.getSiguiente() != n && aux.getSiguiente() != null)
			aux = aux.getSiguiente();
		if (aux.getSiguiente() == null)
			throw new InvalidPositionException("prev(p): la posicion no pertenece a la lista");
		return aux;

	}

	@Override
	public void addFirst(E elem) {
		cabeza = new Nodo<E>(elem, cabeza);
		tamanio++;
	}

	@Override
	public void addLast(E element) {
		if( isEmpty() )
			addFirst(element);
		else {
			Nodo<E> p = cabeza;
			while (p.getSiguiente() != null)
				p = p.getSiguiente();
			p.setSiguiente(new Nodo<E>(element));
			tamanio++;
		}
	}

	@Override
	public void addAfter(Position<E> p, E elem) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		Nodo<E> nuevo = new Nodo<E>(elem);
		nuevo.setSiguiente(n.getSiguiente());
		n.setSiguiente(nuevo);
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
				anterior.setSiguiente(nuevo);
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
		if( n == cabeza )
			cabeza = cabeza.getSiguiente();
		else {
			try {
				Nodo<E> anterior = (Nodo<E>) prev(p);
				anterior.setSiguiente(n.getSiguiente());
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
		n.setElemento(element);
		return oldElement;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> iterable = new ListaSimplementeEnlazada<Position<E>>();
		Nodo<E> actual = cabeza;
		while (actual != null) {
			iterable.addLast(actual);
			actual = actual.getSiguiente();
		}
		return iterable;
	}
	
}
