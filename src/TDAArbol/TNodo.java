package TDAArbol;

import TDALista.*;

/**
* Clase TNodo
* Implementacion de un nodo de arbol con enlace al padre y una lista de hijos
*/

public class TNodo<E> implements Position<E>{
	
	private E elem;
	private TNodo<E> padre;
	private PositionList<TNodo<E>> hijos;
		
	/**
	 * Crea un nuevo TNodo con el elemento y el padre pasado por parámetro
	 * @param elem elemento de tipo generico del nodo
	 * @param padre referencia al TNodo padre
	 */
	public TNodo(E elem, TNodo<E> padre) {
		this.elem = elem;
		this.padre = padre;
		hijos = new ListaDE_CC_EPyU<TNodo<E>>();
	}
	
	@Override
	public E element() {
		return elem;
	}
	
	/**
	 * Setea el elemento
	 * @param elem elemento a asignar
	 */
	public void setElemento(E elem) { 
		this.elem = elem; 
	}
	
	/**
	 *  Obtiene los hijos
	 * @return lista de hijos
	 */
	public PositionList<TNodo<E>> getHijos() { 
		return hijos; 
	}
	
	/**
	 * Obtiene el padre
	 * @return padre
	 */
	public TNodo<E> getPadre() { 
		return padre; 
	}
	/**
	 * Setea el padre
	 * @param padre padre a asignar
	 */
	public void setPadre(TNodo<E> padre) {
		this.padre = padre;
	}
}
