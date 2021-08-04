package TDALista;

import java.util.Iterator;


public class ListaDE_SC_EPyU<E> implements PositionList<E>{
	
	private DNodo<E> header,trailer;
	private int tamanio;


	public ListaDE_SC_EPyU() {
		header = null;
		trailer = null;
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
		if (tamanio==0)
			throw new EmptyListException("first(): la lista se encuentra vacia");
		return header;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if( tamanio == 0 )
			throw new EmptyListException("last(): la lista se encuentra vacia");
		return trailer;
	}

	/**
	 * Chequea que la posicion sea correcta
	 * 
	 * @param p posicion a chequear
	 * @return nodo chequeado que sea valido
	 * @throws InvalidPositionException si la posicion es nula
	 */
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if(p == null) 
				throw new InvalidPositionException("La posición es nula");
			if (p.element() == null)
				throw new InvalidPositionException("La posicion fue eliminada previamente");
			return (DNodo<E>) p;
		} catch(ClassCastException e ) {
			throw new InvalidPositionException("p no es de tipo Nodo E");
		}
	}
	
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> aux = checkPosition(p);
		if( aux == trailer )
			throw new BoundaryViolationException("next(p): el ultimo no tiene siguiente");
		return aux.getNext();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> aux = checkPosition(p);
		if( aux==header )
			throw new BoundaryViolationException("Prev: el primero no tiene anterior");
		return aux.getPrev();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> nuevo = new DNodo<E>(null, element, null);
		if (tamanio==0) {
			header = nuevo;
			trailer = nuevo;
		}
		else {
			nuevo.setNext(header);
			header.setPrev(nuevo);
			header = nuevo;
		}
		tamanio++;
	}

	@Override
	public void addLast(E element) {
		DNodo<E> nuevo = new DNodo<E>(null, element, null);
		if (tamanio==0) 
			header = nuevo;
		else {
			nuevo.setPrev(trailer);
			trailer.setNext(nuevo);
		}
		trailer = nuevo;
		tamanio++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> aux = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		if( aux==trailer ) {
			trailer.setNext(nuevo);
			nuevo.setPrev(trailer);
			trailer = nuevo;
		} else {
			nuevo.setPrev(aux);
			nuevo.setNext(aux.getNext());
			aux.getNext().setPrev(nuevo);
			aux.setNext(nuevo);
		}		
		tamanio++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> aux = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		if( aux == header ) {
			header.setPrev(nuevo);
			nuevo.setNext(header);
			header=nuevo;
		} else {
			nuevo.setNext(aux);
			nuevo.setPrev(aux.getPrev());
			aux.getPrev().setNext(nuevo);
		    aux.setPrev(nuevo);
		}		
		tamanio++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> aux = checkPosition(p);
		if(tamanio == 0 )
			throw new InvalidPositionException("remove(p): lista vacia");		
		E oldElement = aux.element();
		if (tamanio==1) {			
			header=null;
			trailer=null;
		} else
			if( aux==trailer )
				trailer = trailer.getPrev();
			else
				if( aux==header ) 
					header = header.getNext();
				else {
					aux.getPrev().setNext(aux.getNext());
					aux.getNext().setPrev(aux.getPrev());
				}		
		 tamanio--;	
		 return oldElement;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		 DNodo<E> n=checkPosition(p);
		 if( tamanio==0 )
			 throw new InvalidPositionException("set con lista vacia");
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
		 PositionList<Position<E>> list=new ListaDE_SC_EPyU<Position<E>>();
		 DNodo<E> cursor = header;
			while(cursor != null){
				list.addLast(cursor);
				cursor = cursor.getNext();
			}
			return list;
	}

}
