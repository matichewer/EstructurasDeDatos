package TDAMapeo;

import TDALista.*;

public class MapeoConLista<K,V> implements Map<K,V> {
	
	protected PositionList<Entrada<K,V>> lista;

	public MapeoConLista() {
		lista = new ListaDE_CC_EPyU<Entrada<K,V>>();
	}
	
	@Override
	public int size() {
		return lista.size();
	}

	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public V get(K key) throws InvalidKeyException { // O(n)
		if (key==null)
			throw new InvalidKeyException("get con key nula");		
		for(Entrada<K,V> entrada : lista)
			if (entrada.getKey().equals(key))
				return entrada.getValue();
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {  // O(n)
		if( key == null)
			throw new InvalidKeyException("put(k,v): la clave no puede ser nula");
		for(Entrada<K,V> entrada : lista)
			if( entrada.getKey().equals(key) ) {
				V oldValue = entrada.getValue();
				entrada.setValue(value);
				return oldValue;
			}
		lista.addLast(new Entrada<K,V>(key,value));
		return null;
	}

	@Override
	public V remove(K key) throws InvalidKeyException { // O(n)
		if(key==null)
			throw new InvalidKeyException("remove(k): la clave no puede ser nula");
		for(Position<Entrada<K,V>> pos : lista.positions()) // necesito recorrer las posiciones para removerla de la lista
			if(pos.element().getKey().equals(key)) {
				V valueToReturn = pos.element().getValue();
				try {
					lista.remove(pos);
				}catch(InvalidPositionException e) {
					e.getMessage();
				}
				return valueToReturn;
			}
		return null;
	}

	@Override
	public Iterable<K> keys() { // O(n)
		PositionList<K> it = new ListaDE_CC_EPyU<K>();
		for(Entrada<K,V> entrada : lista)
			it.addLast(entrada.getKey());
		return it;			
	}

	@Override
	public Iterable<V> values() { // O(n)
		PositionList<V> it = new ListaDE_CC_EPyU<V>();
		for(Entrada<K,V> entrada : lista)
			it.addLast(entrada.getValue());
		return it;			
	}

	@Override
	public Iterable<Entry<K, V>> entries() { // O(n)
		PositionList<Entry<K,V>> it = new ListaDE_CC_EPyU<Entry<K,V>>();
		for(Entrada<K,V> entrada : lista)
			it.addLast(entrada);
		return it;				
	}

}
