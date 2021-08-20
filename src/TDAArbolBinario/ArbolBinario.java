package TDAArbolBinario;

import java.util.Iterator;
import TDALista.*;

public class ArbolBinario<E> implements BinaryTree<E>{
	
	protected BTPosition<E> raiz;
	protected int tamanio;
	
	public ArbolBinario() {
		tamanio = 0;
		raiz = null;
	}
	
	public ArbolBinario(E root){
		raiz = new BTNodo<E>(root, null);
		tamanio = 1;		
	}

	protected BTNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		BTNodo<E> nodo = null;
		try {
			nodo = (BTNodo<E>) p;
		} catch(ClassCastException e) {
			throw new InvalidPositionException("La posicion no es de tipo BTNodo<E>");
		}
		if(p == null  ||  p.element() == null)
			throw new InvalidPositionException("La posicion es nula");
		return nodo;
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
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista = new ListaDE_CC_EPyU<Position<E>>();
		if(!isEmpty())
			pre(lista, raiz);
		return lista;
	}

	private void pre(PositionList<Position<E>> lista, Position<E> r) {
		lista.addLast(r);
		try {
			for(Position<E> hijo : children(r))
				pre(lista, hijo);
		} catch(InvalidPositionException e) {
			e.getMessage();
		}
	}	
	

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		BTNodo<E> nodo = checkPosition(v);
		E elem = nodo.element();
		nodo.setElement(e);
		return elem;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(tamanio==0)
			throw new EmptyTreeException("root() con arbol vacio");
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> nodo = checkPosition(v);
		if(nodo == raiz)
			throw new BoundaryViolationException("parent(): la raiz no tiene padre");
		return nodo.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		BTNodo<E> nodo = checkPosition(v);
		PositionList<Position<E>> lista = new ListaDE_CC_EPyU<Position<E>>();
		if(nodo.getLeft() != null)
			lista.addLast(nodo.getLeft());
		if(nodo.getRight() != null)
			lista.addLast(nodo.getRight());
		return lista;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return hasLeft(v) || hasRight(v);
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !hasLeft(v) && !hasRight(v);
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTNodo<E> nodo = checkPosition(v);
		return nodo == raiz;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> lista = new ListaDE_CC_EPyU<E>();
		for( Position<E> pos : positions() ) 
			lista.addLast( pos.element() );
		return lista.iterator(); 
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException,
												  BoundaryViolationException {
		BTNodo<E> nodo = checkPosition(v);
		if(nodo.getLeft() == null)
			throw new BoundaryViolationException("left(): la posicion no tiene hijo izquierdo");
		return nodo.getLeft();
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException,
												   BoundaryViolationException {
		BTNodo<E> nodo = checkPosition(v);
		if(nodo.getRight() == null)
			throw new BoundaryViolationException("right(): la posicion no tiene hijo derecho");
		return nodo.getRight();
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNodo<E> nodo = checkPosition(v);
		return nodo.getLeft()!=null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNodo<E> nodo = checkPosition(v);
		return nodo.getRight()!=null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if(tamanio!=0)
			throw new InvalidOperationException("createRoot(): el arbol ya tiene una raiz");
		BTNodo<E> nodo = new BTNodo<E>(r, null);
		raiz = nodo;
		tamanio++;
		return nodo;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException,
														  InvalidPositionException {
		BTNodo<E> nodoPadre = checkPosition(v);
		if (nodoPadre.getLeft() != null)
			throw new InvalidOperationException("addLeft(): la posicion ya tiene un hijo izquierdo");
		BTNodo<E> nuevoHijo = new BTNodo<E>(r, nodoPadre);
		nodoPadre.setLeft(nuevoHijo);
		tamanio++;
		return nuevoHijo;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException,
														   InvalidPositionException {
		BTNodo<E> nodoPadre = checkPosition(v);
		if (nodoPadre.getRight() != null)
			throw new InvalidOperationException("addRight(): la posicion ya tiene un hijo derecho");
		BTNodo<E> nuevoHijo = new BTNodo<E>(r, nodoPadre);
		nodoPadre.setRight(nuevoHijo);
		tamanio++;
		return nuevoHijo;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		BTNodo<E> padre = checkPosition(v);
		BTNodo<E> abuelo = (BTNodo<E>) padre.getParent();
		BTNodo<E> hIzquierdo = (BTNodo<E>) padre.getLeft();
		BTNodo<E> hDerecho = (BTNodo<E>) padre.getRight();
		if(hIzquierdo!=null && hDerecho!=null) 
			throw new InvalidOperationException("remove(): la posicion tiene mas de un hijo");
		E toReturn = v.element();
		if(padre==raiz) {
			if(hIzquierdo!=null) { // si tiene solo 1 hijo, izquierdo
				raiz = hIzquierdo;
				hIzquierdo.setParent(null);
			}
			else 
				if(hDerecho!=null) {  // si tiene solo 1 hijo, derecho
					raiz = hDerecho;
					hDerecho.setParent(null);
				} else 
					raiz = null; // si no tiene hijos
		} else
			if(hIzquierdo!=null) { // si tiene hijo izquierdo
				hIzquierdo.setParent(abuelo);
				if(abuelo.getLeft() == padre) 
					abuelo.setLeft(hIzquierdo);
				else 
					abuelo.setRight(hIzquierdo);
			} else 
				if(hDerecho!=null) {
					hDerecho.setParent(abuelo);
					if(abuelo.getLeft()==padre) 
						abuelo.setLeft(hDerecho);
					else
						abuelo.setRight(hDerecho);
				}
				else
					if(abuelo.getLeft()==padre) 
						abuelo.setLeft(null);
					else
						abuelo.setRight(null);
		tamanio--;
		return toReturn;
	}
	

	@Override
	public void attach(Position<E> p, BinaryTree<E> t1, BinaryTree<E> t2) throws InvalidPositionException {
		BTPosition<E> raiz_local = checkPosition(p);
		if (raiz_local.getLeft() != null || raiz_local.getRight() != null)
			throw new InvalidPositionException("La posicion no corresponde a un nodo hoja");
		
		try {
			// Clonación de T1 como subárbol izquierdo
			if (!t1.isEmpty()) {
				Position<E> raiz_t1 = t1.root();
				BTPosition<E> hi_raiz_local = new BTNodo<E>(raiz_t1.element(), raiz_local);
				raiz_local.setLeft(hi_raiz_local);
				clonar(raiz_local.getLeft(), raiz_t1, t1);
			}
			// Clonación de T2 como subárbol derecho
			if (!t2.isEmpty()) {
				Position<E> raiz_t2 = t2.root();
				BTPosition<E> hd_raiz_local = new BTNodo<E>(raiz_t2.element(), raiz_local);
				raiz_local.setRight(hd_raiz_local);
				clonar(raiz_local.getRight(), raiz_t2, t2);
			}
			tamanio += t1.size() + t2.size();
		}catch(EmptyTreeException e) { 
			raiz_local.setLeft(null); 
			raiz_local.setRight(null); 
		}
	}
	
	protected void clonar(BTPosition<E> padre_local, Position<E> padre_t, BinaryTree<E> t) {
		try {
			//Si existe hijo izquierdo en T de padre_t, se clona este y el subárbol a partir del hijo izquierdo de padre_t.
			if (t.hasLeft(padre_t)) {
				Position<E> hi_padre_t = t.left(padre_t);
				BTPosition<E> hi_padre_local = new BTNodo<E>(hi_padre_t.element(), padre_local);
				padre_local.setLeft(hi_padre_local);
				clonar(hi_padre_local, hi_padre_t, t);
			}
			//Si existe hijo derecho en T de padre_t, se clona este y el subárbol a partir del hijo derecho de padre_t.
			if (t.hasRight(padre_t)) {
				Position<E> hd_padre_t = t.right(padre_t);
				BTPosition<E> hd_padre_local = new BTNodo<E>(hd_padre_t.element(), padre_local);
				padre_local.setRight(hd_padre_local);
				clonar(hd_padre_local, hd_padre_t, t);
			}
		}catch(InvalidPositionException | BoundaryViolationException e) {
			padre_local.setLeft(null);
			padre_local.setRight(null);
		}
	}
	
	@Override
	public BinaryTree<E> clone() {
		BinaryTree<E> toReturn = new ArbolBinario<E>();
		try {
			if(tamanio!=0) {
				Position<E> posClon = toReturn.createRoot(raiz.element());
				cloneAux(raiz, toReturn, posClon);
			}
		} catch(InvalidOperationException e) {
			e.getMessage();
		}
		return toReturn;
	}
	
	private BinaryTree<E> cloneAux(BTPosition<E> posOriginal, BinaryTree<E> clon, Position<E> posClon){
		Position<E> nuevaPosClon;
		try {
			if(hasLeft(posOriginal)) {
				nuevaPosClon = clon.addLeft(posClon, posOriginal.getLeft().element());
				cloneAux(posOriginal.getLeft(), clon, nuevaPosClon);
			}
			if(hasRight(posOriginal)) {
				nuevaPosClon = clon.addRight(posClon, posOriginal.getRight().element());
				cloneAux(posOriginal.getRight(), clon, nuevaPosClon);
			}
		}catch(InvalidOperationException | InvalidPositionException e) {
			e.getMessage();
		}
		return clon;
	}
	
	// TP 7  Ej 4
	public boolean esPropio() {
		boolean es = true;
		if(tamanio!=0)
			es = esPropioAux(raiz);
		return es;
	}
	
	private boolean esPropioAux(BTPosition<E> pos) {
		boolean es = true;
		try {
			if(es && hasLeft(pos))
				if(hasRight(pos))
					es = esPropioAux(pos.getLeft());
				else
					es = false;
			if(es && hasRight(pos))
				if(hasLeft(pos))
					es = esPropioAux(pos.getRight());
				else
					es = false;				
		}catch(InvalidPositionException e) {
			e.getMessage();
		}
		return es;
	}
	
	

	public String padresDeHojas() {
		String s = "";
		if(tamanio!=0) {
			s = padresDeHojasAux(raiz, null, 0);
		}
		return s;
	}
	
	private String padresDeHojasAux(BTPosition<E> pos, BTPosition<E> ultimoAgregado, int nivel) {
		String s = "";
		try {
			if(!hasLeft(pos) && !hasRight(pos)) {
				if(ultimoAgregado!=pos.getParent()) {
					ultimoAgregado = pos.getParent();
					s = s + ultimoAgregado.element().toString() + " - " + (nivel-1) + "\n";
				}
			}
			if(hasLeft(pos))
				s = s + padresDeHojasAux(pos.getLeft(), ultimoAgregado, nivel+1);
			if(hasRight(pos))
				s = s + padresDeHojasAux(pos.getRight(), ultimoAgregado, nivel+1);
		}catch(InvalidPositionException e) {
			e.getMessage();
		}
		return s;
	}
	
}