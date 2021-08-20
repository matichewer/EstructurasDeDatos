package TDAArbolBinario;

/**
 * Interface BinaryTree
 * Exstiende la interfaz GTTree, presentada por Goodrich y Tamassia en su
 * cuarta edicion (la interfaz para arbol que no incluye operaciones de modificacion).
 */

public interface BinaryTree<E> extends GTTree<E> {
	
	/**
	 * Devuelve la posicion del hijo izquierdo de v.
	 * @param v Posicion de un nodo.
	 * @return Posicion del hijo izquierdo de v.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 * @throws BoundaryViolationException si v no tiene hijo izquierdo.
	 */
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve la posicion del hijo derecho de v.
	 * @param v Posicion de un nodo.
	 * @return Posicion del hijo derecho de v.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.
	 * @throws BoundaryViolationException si v no tiene hijo derecho.
	 */
	public Position<E> right(Position<E> v)throws InvalidPositionException, BoundaryViolationException;

	/**
	 * Testea si v tiene un hijo izquierdo.
	 * @param v Posicion de un nodo.
	 * @return Verdadero si v tiene un hijo izquierdo y falso en caso contrario.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.	
	 */
	public boolean hasLeft(Position<E> v) throws InvalidPositionException;
	
	
	/**
	 * Testea si v tiene un hijo derecho.
	 * @param v Posicion de un nodo.
	 * @return Verdadero si v tiene un hijo derecho y falso en caso contrario.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida.	
	 */
	public boolean hasRight(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Crea un nodo con rotulo e como raiz del arbol.
	 * @param E Rótulo que se asignará a la raiz del arbol.
	 * @throws InvalidOperationException si el arbol ya tiene un nodo raiz.
	 */
	public Position<E> createRoot(E r) throws InvalidOperationException;
	
	
	/**
	 * Agrega un nodo con rotulo r como hijo izquierdo de un nodo dado.
	 * @param r Rótulo del nuevo nodo.
	 * @param v Posicion del nodo padre.
	 * @return La posicion del nuevo nodo creado.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida o el arbol está vacio.
	 * @throws InvalidOperationException si v ya tiene un hijo izquierdo.
	 */
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException;


	/**
	 * Agrega un nodo con rotulo r como hijo derecho de un nodo dado.
	 * @param r Rótulo del nuevo nodo.
	 * @param v Posicion del nodo padre.
	 * @return La posicion del nuevo nodo creado.
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida o el arbol está vacio.
	 * @throws InvalidOperationException si v ya tiene un hijo derecho.
	 */
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, 
														   InvalidPositionException;


	/**
	 * Elimina el nodo referenciado por una posicion dada. Si el nodo tiene un unico hijo, el nodo eliminado será reemplazado por su unico hijo.
	 * @param v Posicion del nodo a eliminar.
	 * @return el rotulo del nodo eliminado.
     * @throws InvalidPositionException si la posicion pasada por parametro es invalida o el arbol está vacio.
	 * @throws InvalidOperationException si el nodo a eliminar tiene mas de un hijo.
     */
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException;

	/**
	 * Inserta a los arboles T1 y T2 como subarboles hijos de la hoja v (izquierdo y derecho respectivamente).
	 * @param v Posicion de una hoja del arbol.
	 * @param T1 arbol binario a insertar como hijo izquierdo de v.
	 * @param T2 arbol binario a insertar como hijo derecho de v. 
	 * @throws InvalidPositionException si la posicion pasada por parametro es invalida o el arbol está vacio, o v no corresponde a una hoja.
	 */
	//Pone a T1 y a T2 como subarboles de la hoja v, izquierdo y derecho respectivamente, si v no era hoja da InvalidPositionException.
	  public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException;
}