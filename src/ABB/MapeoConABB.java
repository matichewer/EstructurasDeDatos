package ABB;

import TDALista.*;

/**
 * Implementacion de un Mapeo con un arbol Binario de Busqueda.
 * Asume el tipo de dato de las claves comparable.
 *
 * @param <K> Tipo de dato de las claves del mapeo.
 * @param <V> Tipo de dato de los valores del mapeo.
 */
public class MapeoConABB<K extends Comparable<K>, V> implements Map<K, V> {
	
	protected ABB<EntradaComparable<K,V>> abb;
	protected int size;
	
	/**
	 * Inicializa un mapeo vacio.
	 */
	public MapeoConABB(){
		abb = new ABB<EntradaComparable<K,V>>(null);
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException("");
		
		V toReturn = null;
		NodoABB<EntradaComparable<K,V>> nodo = abb.buscar(new EntradaComparable<K,V>(key, null));
		
		//Si no es un DUMMY
		if (nodo.getRotulo() != null)
			toReturn = nodo.getRotulo().getValue();
		
		return toReturn;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException("");
		
		V toReturn = null;
		NodoABB<EntradaComparable<K,V>> nodo = abb.buscar(new EntradaComparable<K,V>(key, null));
		
		//Si no es un DUMMY
		if (nodo.getRotulo() != null){
			toReturn = nodo.getRotulo().getValue();
			nodo.getRotulo().setValue(value);
		}else{
			abb.insertar(new EntradaComparable<K,V>(key, value));
			size++;
		}
		
		return toReturn;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException("");
		
		V toReturn = null;
		EntradaComparable<K,V> ent = abb.eliminar(new EntradaComparable<K,V>(key, null));
		if (ent != null){
			toReturn = ent.getValue();
			size--;
		}
		return toReturn;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> l = new ListaDE_CC_EPyU<K>();
		preordenkey(abb.getRoot(), l);
		return l;
	}
	/**
	 * Implementa un recorrido en pre-orden de las claves del mapeo.
	 * @param n Nodo del ABB a visitar.
	 * @param l Lista a retornar con las claves del mapeo en preorden.
	 */
	protected void preordenkey(NodoABB<EntradaComparable<K,V>> n, PositionList<K> l){
		if (n.getRotulo() != null){
			preordenkey(n.getLeft(), l);
			l.addLast(n.getRotulo().getKey());
			preordenkey(n.getRight(), l);
		}
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> l = new ListaDE_CC_EPyU<V>();
		preordenvalues(abb.getRoot(), l);
		return l;
	}
	/**
	 * Implementa un recorrido en pre-orden de los valores del mapeo.
	 * @param n Nodo del ABB a visitar.
	 * @param l Lista a retornar con los valores del mapeo en preorden.
	 */
	protected void preordenvalues(NodoABB<EntradaComparable<K,V>> n, PositionList<V> l){
		if (n.getRotulo() != null){
			preordenvalues(n.getLeft(), l);
			l.addLast(n.getRotulo().getValue());
			preordenvalues(n.getRight(), l);
		}
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> l = new ListaDE_CC_EPyU<Entry<K,V>>();
		preordenentries(abb.getRoot(), l);
		return l;
	}
	
	/**
	 * Implementa un recorrido en pre-orden de las entries del mapeo.
	 * @param n Nodo del ABB a visitar.
	 * @param l Lista a retornar con las entries del mapeo en preorden.
	 */
	protected void preordenentries(NodoABB<EntradaComparable<K,V>> n, PositionList<Entry<K,V>> l){
		if (n.getRotulo() != null){
			preordenentries(n.getLeft(), l);
			l.addLast((Entry<K,V>)n.getRotulo());
			preordenentries(n.getRight(), l);
		}
	}
}
