package TDADiccionario;

import TDALista.*;

public class DiccionarioConLista<K,V> implements Dictionary<K,V> {
	
	protected PositionList<Entry<K,V>> lista;
	
	
	/**
	 * Crea un diccionario con lista doblemente enlazada
	 */
	public DiccionarioConLista() { 
		lista = new ListaDE_CC_EPyU<Entry<K,V>>(); 
	}
	
	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public int size() {
		return lista.size();
	}
	
	@Override
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException{
		if(key==null) 
			throw new InvalidKeyException("findAll(k): clave nula");
		PositionList<Entry<K,V>> listaToReturn = new ListaDE_CC_EPyU<Entry<K,V>>();
		for(Entry<K,V> p : lista) 
			if(p.getKey().equals(key))
				listaToReturn.addLast(p);
		return listaToReturn; // TfindAll(n)=O(n), asumiendo que addLast tiene O(1).
		// En caso de que addLast tenga O(n), entonces TfindAll(n) = n cuadrado
	}

	@Override
	public Entry<K,V> find(K key) throws InvalidKeyException{
		if(key==null) 
			throw new InvalidKeyException("find(k): clave nula");
		for(Entry<K,V> p : lista) 
			if(p.getKey().equals(key)) 
				return p;
		return null; // Tfind(n) = O(n)
	}

	@Override
	public Entry<K,V> insert(K key, V value)throws InvalidKeyException{
		if(key==null) 
			throw new InvalidKeyException("insert con clave nula");
		Entry<K,V> e = new Entrada<K,V>(key,value);
		lista.addLast(e); // asumo que addLast funciona en O(1)
		return e; // Tinsert(n) = O(1) , siendo n la cant de entradas al diccionario
	}

	@Override
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException{
		if(e==null) 
			throw new InvalidEntryException("remove(e): la entrada es nula");
		for(Position<Entry<K,V>> p : lista.positions()) 
			if(p.element() == e) {				
				try {
					lista.remove(p);
				} catch (InvalidPositionException e1) {
					e1.printStackTrace();
				}
				return e; // Tremove(n) = O(n), siendo n la cant de entradas al diccionario
			}
		throw new InvalidEntryException("remove(e): la entrada no existe");
	}

	@Override
	public Iterable<Entry<K,V>> entries(){
		return lista;
	}
}
