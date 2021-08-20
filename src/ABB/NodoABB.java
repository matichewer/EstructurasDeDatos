package ABB;

/**
 * Implementa un nodo para un ABB.
 * @param <E> Tipo de dato del rotulo del nodo.
 */
public class NodoABB<E> {

	protected E rotulo;
	protected NodoABB<E> padre;
	protected NodoABB<E> hi;
	protected NodoABB<E> hd;
	/**
	 * Inicializa un nodo de ABB con rotulo y referencia al padre.
	 * @param r Rotulo del nuevo nodo.
	 * @param p Padre del nuevo nodo.
	 */
	public NodoABB(E r, NodoABB<E> p){
		rotulo = r;
		padre = p;		
	}
	
	/**
	 * Establece un nuevo rotulo para el nodo.
	 * @param r Nuevo rotulo a establecer.
	 */
	public void setRotulo(E r){ 
		rotulo = r;
	}
	
	/**
	 * Establece un nuevo padre para el nodo.
	 * @param p Nuevo padre a establecer.
	 */
	public void setParent(NodoABB<E> p){
		padre = p;
	}
	
	/**
	 * Establece un nuevo hijo izquierdo del nodo.
	 * @param i Hijo izquierdo a establecer.
	 */
	public void setLeft(NodoABB<E> i){
		hi = i;
	}
	
	/**
	 * Establece un nuevo hijo derecho del nodo.
	 * @param d Hijo derecho a establecer.
	 */
	public void setRight(NodoABB<E> d){
		hd = d;
	}
	
	/**
	 * Obtiene el rotulo del nodo.
	 * @return El rotulo del nodo.
	 */
	public E getRotulo(){ 
		return rotulo; 
	}
	
	/**
	 * Obtiene el padre del nodo.
	 * @return El padre del nodo.
	 */
	public NodoABB<E> getParent(){
		return padre;
	}
	
	/**
	 * Obtiene el hijo izquierdo del nodo.
	 * @return El hijo izquierdo del nodo.
	 */
	public NodoABB<E> getLeft(){
		return hi;
	}
	
	/**
	 * Obtiene el hijo derecho del nodo.
	 * @return El hijo derecho del nodo.
	 */
	public NodoABB<E> getRight(){
		return hd;
	}
}