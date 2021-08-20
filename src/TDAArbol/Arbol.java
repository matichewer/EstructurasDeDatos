package TDAArbol;

import java.util.Iterator;
import TDALista.*;

/**
 * Clase Arbol
 * Implementación de un arbol genérico
 */
public class Arbol<E> implements Tree<E> {

	protected TNodo<E> raiz;
	protected int tamanio;
	
	/**
	 * Crea un arbol vacio
	 */
	public Arbol() {
		raiz = null;
		tamanio = 0;
	}
	
	/**
	 * Crea un arbol de tamaño 1, cuya raiz contiene el elemento de tipo E pasado por parametro
	 * @param elem Elemento que estará dentro de la raiz del arbol
	 */
	public Arbol(E elem) {
		raiz = new TNodo<E>(elem,null);
		tamanio = 1;		
	}
	
	/**
	 * Chequea si la posicion pasada por parametro es válida
	 * @param p posicion a chequear
	 * @return nodo chequeado
	 * @throws InvalidPositionException si la posicion es invalida
	 */
	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		TNodo <E> n = null;
		try {
			n = (TNodo<E>) p;
		} catch(ClassCastException e) {
			throw new InvalidPositionException("La posicion no es de tipo TNodo");
		}
		if(p == null  ||  p.element() == null)
			throw new InvalidPositionException("La posicion es nula");
		return n;
	}
	
	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		return raiz == null;
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> n = checkPosition(v);
		E elem = n.element();
		n.setElemento(e);
		return elem;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException("root(): Arbol vacio");
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> n = checkPosition(v);
		if(n==raiz)
			throw new BoundaryViolationException("parent(v): la posicion no puede ser el padre");
		return n.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> p = checkPosition(v);
		PositionList<Position<E>> lista = new ListaDE_CC_EPyU<Position<E>>();
		for(TNodo<E> n : p.getHijos())
			lista.addLast(n);
		return lista;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> n = checkPosition(v);
		return !n.getHijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> n = checkPosition(v);
		return n.getHijos().isEmpty();
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> n = checkPosition(v);
		return n==raiz;
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(!isEmpty())
			throw new InvalidOperationException("createRoot(): Arbol no vacio");
		raiz = new TNodo<E>(e,null);
		tamanio = 1;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		if(tamanio==0)
			throw new InvalidPositionException("addFirstChild: arbol vacio");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> nodo = new TNodo<E>(e,padre);
		padre.getHijos().addFirst(nodo);
		tamanio++;
		return nodo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		if(tamanio==0)
			throw new InvalidPositionException("addLastChild: arbol vacio");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> nodo = new TNodo<E>(e,padre);
		padre.getHijos().addLast(nodo);
		tamanio++;
		return nodo;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E elem) throws InvalidPositionException {
		if(isEmpty())
			throw new InvalidPositionException("addBefore: arbol vacio");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermanoDerecho = checkPosition(rb);
		TNodo<E> nuevo = new TNodo<E>(elem,padre);
		
		PositionList<TNodo<E>> hijosDelPadre = padre.getHijos();
		boolean encontre = false; 
		try {
			if(hijosDelPadre.isEmpty())
				throw new InvalidPositionException("rb no es hijo de p");
			Position<TNodo<E>> pos = hijosDelPadre.first();
			while(pos != null && !encontre)
				if(hermanoDerecho == pos.element())
					encontre = true;
				else
					pos = (pos != hijosDelPadre.last() ? hijosDelPadre.next(pos) : null);
			if(!encontre)
				throw new InvalidPositionException("p no es padre de rb");
			hijosDelPadre.addBefore(pos,nuevo);
			tamanio++;
			return nuevo;
		}catch(EmptyListException | BoundaryViolationException j) {
			throw new InvalidPositionException("p no es padre de rb");
		}
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E elem) throws InvalidPositionException {
		if(isEmpty())
			throw new InvalidPositionException("addAfter: Arbol vacio");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermanoIzquierdo = checkPosition(lb);
		TNodo<E> nuevo = new TNodo<E>(elem,padre);
		
		PositionList<TNodo<E>> hijosDelPadre = padre.getHijos();
		boolean encontre = false;
		try {
			if(hijosDelPadre.isEmpty())
				throw new InvalidPositionException("lb no es hijo de p");
			Position<TNodo<E>> pos = hijosDelPadre.first();
			while(pos != null && !encontre)
				if( hermanoIzquierdo == pos.element() )
					encontre = true;
				else
					pos = (pos != hijosDelPadre.last() ? hijosDelPadre.next(pos) : null);
			if(!encontre)
				throw new InvalidPositionException("p no es padre de lb");
			hijosDelPadre.addAfter(pos,nuevo);
			tamanio++;
			return nuevo;
		}catch(EmptyListException | BoundaryViolationException j) {
			throw new InvalidPositionException("p no es padre de lb");
		}
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		if(tamanio==0)
			throw new InvalidPositionException("arbol vacio");
		TNodo<E> n = checkPosition(p);
		if(!n.getHijos().isEmpty())
			throw new InvalidPositionException("No es un nodo externo");
		removeNode(n);
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		if(tamanio==0)
			throw new InvalidPositionException("arbol vacio");
		TNodo<E> n = checkPosition(p);
		if(n.getHijos().isEmpty())
			throw new InvalidPositionException("No es un nodo interno");
		removeNode(n);
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		if(tamanio==0)
			throw new InvalidPositionException("arbol vacio");
		TNodo<E> nEliminar = checkPosition(p);	
		TNodo<E> padre = nEliminar.getPadre();
		PositionList<TNodo<E>> hijos = nEliminar.getHijos();
		try{
			if (nEliminar == raiz){
				if (hijos.size() == 0)
					raiz = null;
				else{
					if (hijos.size() == 1){
						TNodo<E> hijo = hijos.remove(hijos.first());
						hijo.setPadre(null);
						raiz = hijo;
					}else
						throw new InvalidPositionException("No se puede eliminar raíz con hijos > 1");
				}
			}	else{
				PositionList<TNodo<E>> hermanos = padre.getHijos();				
				Position<TNodo<E>> posListaHermanos = hermanos.isEmpty() ? null : hermanos.first();
				while(posListaHermanos != null && posListaHermanos.element() != nEliminar){
					posListaHermanos = (hermanos.last() == posListaHermanos) ? null : hermanos.next(posListaHermanos);
				}
				if (posListaHermanos == null)
					throw new InvalidPositionException("La posición p no se encuentra en la lista del padre");
				
				while(!hijos.isEmpty()){
					TNodo<E> hijo = hijos.remove(hijos.first());
					hijo.setPadre(padre);
					hermanos.addBefore(posListaHermanos, hijo);
				}
				hermanos.remove(posListaHermanos);
			} 
				nEliminar.setPadre(null);
				nEliminar.setElemento(null);
				tamanio--;
		}catch(EmptyListException | BoundaryViolationException e){
			e.getMessage();
		}
	}
	
	@Override
	public Iterator<E> iterator() { 
		PositionList<E> lista = new ListaDE_CC_EPyU<E>();
		for( Position<E> pos : positions() ) 
			lista.addLast( pos.element() );
		return lista.iterator(); 
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista = new ListaDE_CC_EPyU<Position<E>>();
		if(tamanio!=0)
			pre(lista, raiz);
		return lista;
	}
	
	/**
	 * Añade el TNodo "r" al final de la lista de posiciones "lista"
	 * @param lista lista de hijos
	 * @param r nodo de la lista de hijos
	 */
	private void pre(PositionList<Position<E>> lista, TNodo<E> r) {
		lista.addLast(r);
		for(TNodo<E> hijo : r.getHijos())
			pre(lista, hijo);
	}
}
