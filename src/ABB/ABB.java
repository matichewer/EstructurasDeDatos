package ABB;


import java.util.Comparator;
/**
 * Implementaci�n de un �rbol Binario de B�squeda, considerando nodos DUMMY.
 * @param <E> Tipo de dato de los r�tulos del ABB.
 */
public class ABB<E extends Comparable<E>> {
	
	protected NodoABB<E> raiz;
	protected Comparator<E> comparador;
	
	/**
	 * Inicializa un ABB vac�o.
	 * Si no se parametriza un comparador de elementos, utiliza el comparador por defecto.
	 * @param comp Comparador de elementos del ABB.
	 */
	public ABB(Comparator<E> comp) {
		raiz = new NodoABB<E>(null, null);
		if (comp!= null)
			comparador = comp;
		else
			comparador = new DefaultComparator<E>();
	}
	/**
	 * Obtiene el nodo ra�z del ABB.
	 * @return Nodo ra�z del ABB.
	 */
	public NodoABB<E> getRoot(){
		return raiz;
	}
	/**
	 * Inserta un nuevo elemento al ABB, si este no existe.
	 * @param elemento Nuevo elemento a insertar.
	 * @return Verdadero si la inserci�n es posbile, falso en caso de que el elemento ya se encuentre en el ABB.
	 */
	public boolean insertar(E elemento){
		boolean toReturn = false;
		
		//Se obtiene la ubicaci�n del nuevo elemento en el ABB
		NodoABB<E> nodo = buscar(elemento);
		//Si no existe en el ABB un nodo con elemento igual al buscado
		if( nodo.getRotulo() == null ) {
			nodo.setRotulo(elemento);
			nodo.setLeft( new NodoABB<E>( null, nodo) );
			nodo.setRight( new NodoABB<E>( null, nodo ) );
			toReturn = true;
		}
		return toReturn;	
	}
	
	public void expandir(NodoABB<E> p) {
		p.setLeft(new NodoABB<E>(null, p));
		p.setRight(new NodoABB<E>(null, p));
	}
	
	/**
	 * Elimina el elemento del ABB, si este existe.
	 * @param elemento Elemento a eliminar del ABB.
	 * @return Elemento eliminado del ABB; null en caso contrario.
	 */
	public E eliminar(E elemento){
		E toReturn = null;
		//Obtiene la ubicaci�n que deber�a ocupar el elemento en el ABB
		NodoABB<E> nodo = buscar( elemento );
		//Si no es un nodo DUMMY (esto es, existe el elemento en el ABB)
		if(nodo.getRotulo() != null) {
			toReturn = nodo.getRotulo();
			eliminar_aux(nodo);
		}
		return toReturn;
	}
	/**
	 * M�todo auxiliar para la eliminaci�n de un nodo del ABB.
	 * @param nodo
	 */
	private void eliminar_aux(NodoABB<E> nodo){
		//Si el elemento se encuentra en una hoja del ABB.
		if(isExternal(nodo) ){
			nodo.setRotulo( null );
			nodo.setLeft(null); 
			nodo.setRight(null);
		}else{
			if (nodo == raiz){
				if (soloTieneHijoIzquierdo(nodo)){
					nodo.getLeft().setParent(null);
					raiz = nodo.getLeft();
				}else{
					if (soloTieneHijoDerecho(nodo)){
						nodo.getRight().setParent(null);
						raiz = nodo.getRight();
					}else{
						//Se modifica el r�tulo por el m�nimo valor del ABB en inorden a partir de nodo.
						nodo.setRotulo(eliminarMinimo(nodo.getRight()));
					}
				}
			}else{
				if(soloTieneHijoIzquierdo(nodo) ) {
					if( nodo.getParent().getLeft() == nodo ){
						nodo.getParent().setLeft(nodo.getLeft());
					}else{
						nodo.getParent().setRight(nodo.getLeft());
					}
					nodo.getLeft().setParent(nodo.getParent());
				 }else{
					 if(soloTieneHijoDerecho(nodo) ) {
						 if( nodo.getParent().getLeft() == nodo )
							 nodo.getParent().setLeft( nodo.getRight() );
						 else
							 nodo.getParent().setRight(nodo.getRight());
						 nodo.getRight().setParent( nodo.getParent() );
					 }else{
						//Se modifica el r�tulo por el m�nimo valor del ABB en inorden a partir de nodo.
						 nodo.setRotulo(eliminarMinimo(nodo.getRight()));
					 }
				 }
			}
		}
	}
	/**
	 * Establece si un nodo es hoja en el ABB.
	 * @param nodo A considerar si es hoja en el ABB.
	 * @return Verdadero si el nodo es hoja, falso en caso contrario.
	 */
	private boolean isExternal(NodoABB<E> nodo){
		return nodo.getLeft().getRotulo() == null && nodo.getRight().getRotulo() == null;  
	}
	/**
	 * Establece si un nodo solamente tiene hijo izquierdo.
	 * @param nodo A considerar si tiene solamente hijo izquierdo.
	 * @return Verdadero si nodo tiene solamente hijo izquierdo, falso en caso contrario.
	 */
	private boolean soloTieneHijoIzquierdo(NodoABB<E> nodo ) {
		return nodo.getLeft().getRotulo() != null && nodo.getRight().getRotulo() == null;
	}
	/**
	 * Establece si un nodo solamente tiene hijo derecho.
	 * @param nodo A considerar si tiene solamente hijo derecho.
	 * @return Verdadero si nodo tiene solamente hijo derecho, falso en caso contrario.
	 */
	private boolean soloTieneHijoDerecho(NodoABB<E> nodo ) {
		return nodo.getLeft().getRotulo() == null && nodo.getRight().getRotulo() != null;
	}
	/**
	 * Obtiene el nodo cuyo valor es m�nimo, a partir del sub�rbol izquierdo del nodo parametrizado.
	 * @param nodo A partir del cual se busca el elemento m�nimo en su sub�rbol izquierdo.
	 * @return Elemento m�nimo hallado.
	 */
	private E eliminarMinimo( NodoABB<E> nodo ) {
		E toReturn;
		if( nodo.getLeft().getRotulo() == null ){
			toReturn = nodo.getRotulo();
			if( nodo.getRight().getRotulo() == null ) {
				nodo.setRotulo( null ); 
				nodo.setLeft( null ); 
				nodo.setRight( null );
			} else { 
				nodo.getParent().setRight(nodo.getRight());
				nodo.getRight().setParent(nodo.getParent());
			}
		} else{
			toReturn = eliminarMinimo(nodo.getLeft());
		}
		return toReturn;
	}
	/**
	 * Chequea si un elemento pertenece al ABB.
	 * @param elemento Elemento a chequear su pertenencia.
 	 * @return Verdadero si el elemento pertenece al ABB, falso en caso contrario.
	 */
	public boolean pertenece(E elemento){
		return buscar(elemento).getRotulo() != null; 
	}
	
	/**
	 * Obtiene el nodo correspondiente donde se ubicar�a el elemento parametrizado.
	 * @param elemento A buscar dentro del ABB.
	 * @return Nodo cuyo elemento es el buscado; caso contrario, nodo DUMMY donde el elemento deber�a ser insertado.
	 */
	public NodoABB<E> buscar(E elemento){
		return buscar_aux(raiz, elemento);
	}
	/**
	 * M�todo auxiliar para computar la b�squeda del nodo en el ABB para un elemento dado.
	 */
	private NodoABB<E> buscar_aux(NodoABB<E> nodo, E elemento){
		NodoABB<E> toReturn;
		
		//Si la b�squeda arriba a un nodo DUMMY, lo retorna.
		if( nodo.getRotulo() == null ) 
			toReturn = nodo; 
		else{
			int c = comparador.compare(elemento, nodo.getRotulo());
			//Si los r�tulos son iguales, se retorna el nodo visitado.
			if( c == 0 ) 
				toReturn = nodo; 
			else
				//Si el r�tulo buscado es menor al r�tulo del nodo visitado, se contin�a por el sub�rbol izquierdo
				if( c < 0 ) 
					toReturn = buscar_aux(nodo.getLeft(), elemento);
				else
					toReturn = buscar_aux(nodo.getRight(), elemento);
		}
		return toReturn;
	}
}
