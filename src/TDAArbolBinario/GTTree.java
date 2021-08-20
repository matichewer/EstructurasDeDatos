package TDAArbolBinario;

import java.util.Iterator;
import TDALista.*;
import TDAArbol.*;


/**
 * Interface GTTree
 * Es la interfaz presentada por Goodrich y Tamassia en su cuarta edicion. 
 * Incluye las operaciones necesarias para manejar un arbol general sin proveer operaciones para modificarlo.
 */

public interface GTTree<E> extends Iterable<E>
{
	/**
	 * Consulta la cantidad de nodos en el arbol.
	 * @return Cantidad de nodos en el arbol.
	 */
	public int size();
	
	/**
	 * Consulta si el arbol está vacío.
	 * @return Verdadero si el arbol está vacío, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve un iterador de los elementos almacenados en el arbol en preorden.
	 * @return Iterador de los elementos almacenados en el arbol.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Devuelve una coleccion iterable de las posiciones de los nodos del arbol.
	 * @return Coleccion iterable de las posiciones de los nodos del arbol.
	 */
	public Iterable<Position<E>> positions();
	
	/**
	 * Reemplaza el elemento almacenado en la posicion dada por el elemento pasado por parametro. Devuelve el elemento reemplazado.
	 * @param v Posicion de un nodo.
	 * @param e Elemento a reemplazar en la posicion pasada por parametro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException;
	
	/**
	 * Devuelve la posicion de la raiz del arbol.
	 * @return Posicion de la raiz del arbol.
	 * @throws EmptyTreeException si el arbol está vacío.
	 */
	public Position<E> root() throws EmptyTreeException;
	
	/**
	 * Devuelve la posicion del nodo padre del nodo correspondiente a una posicion dada.
	 * @param v Posicion de un nodo.
	 * @return Posicion del nodo padre del nodo correspondiente a la posicion dada.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 * @throws BoundaryViolationException si la posicion pasada por parametro corresponde a la raiz del arbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve una colección iterable de los hijos del nodo correspondiente a una posicion dada.
	 * @param v Posición de un nodo.
	 * @return Colección iterable de los hijos del nodo correspondiente a la posicion pasada por parametro.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posicion corresponde a un nodo interno.
	 * @param v Posicion de un nodo.
	 * @return Verdadero si la posicion pasada por parametro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posicion dada corresponde a un nodo externo.
	 * @param v Posicion de un nodo.
	 * @return Verdadero si la posicion pasada por parametro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posicion dada corresponde a la raiz del arbol.
	 * @param v Posicion de un nodo.
	 * @return Verdadero, si la posicion pasada por parametro corresponde a la raiz del arbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException;
	

}