package TDAMapeo;

import TDALista.*;

/**
 * Clase MapeoConHashAbierto
 * Implementacion de un mapeo utilizando hash abierto
 * @param <K> Tipo de dato de la clave del par.
 * @param <V> Tipo de dato del valor del par.
 */
public class MapeoConHashAbierto<K,V> implements Map<K, V> {

	protected PositionList<Entrada<K,V>> [] arreglo; // = buckets
	protected int N; // tamanio del arreglo, debe ser un numero primo
	protected int tamanio; // = cant de entradas = size

	/**
	 * Creo un mapeo con hash abierto
	 */
	public MapeoConHashAbierto(int N) {
		arreglo = (PositionList<Entrada<K,V>> []) new ListaDE_CC_EPyU[N];
		this.N = N;
		tamanio = 0;
		for (int i=0; i<N; i++)
			arreglo[i] = new ListaDE_CC_EPyU<>(); // inicializo cada lista del arreglo	
	}	

	public MapeoConHashAbierto(){
		this(997);
	}
	
	private void checkKey(K key) throws InvalidKeyException {
		if( key==null )
			throw new InvalidKeyException("La clave no puede ser nula");
	}
	
	private int hash(K key) {
		return key.hashCode() % N;
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
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		int i = hash(key);
		PositionList<Entrada<K,V>> lista = arreglo[i];		
		for(Entrada<K,V> entrada : lista)
			if (entrada.getKey().equals(key))
				return entrada.getValue();
		//throw new InvalidKeyException("MapeoConHashAbierto::get(key): Error. Clave no encontrada.");
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {	
		checkKey(key);
		int i = hash(key);
		PositionList<Entrada<K,V>> lista = arreglo[i];		
		for( Entrada<K,V> entrada : lista )
			if( entrada.getKey().equals(key) ){
				V valueToReturn = entrada.getValue();
				entrada.setValue(value);
				return valueToReturn;
			}		
		lista.addLast(new Entrada<K,V>(key, value));
		tamanio++;
		return value;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		int i = hash(key);
		PositionList<Entrada<K,V>> lista = arreglo[i];
		try {
			for( Position<Entrada<K,V>> pos : lista.positions())
				if( pos.element().getKey().equals(key) ){
					tamanio--;
					return lista.remove(pos).getValue();
				}			
			//throw new InvalidKeyException("MapeoConHashAbierto::remove(key): Error. Clave no encontrada.");
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterable<K> keys() {		
		PositionList<K> claves = new ListaDE_CC_EPyU<K>();
		for( int i=0; i<N; i++ )
			for( Entrada<K,V> entrada : arreglo[i] )
				claves.addLast(entrada.getKey());
		return claves;
	}

	@Override
	public Iterable<V> values() {		
		PositionList<V> valores = new ListaDE_CC_EPyU<V>();
		for (int i = 0; i < N; i++)
			for (Entrada<K, V> entrada : arreglo[i])
				valores.addLast(entrada.getValue());
		return valores;
	}

	@Override
	public Iterable<Entry<K,V>> entries() {	
		PositionList<Entry<K,V>> entradas = new ListaDE_CC_EPyU<Entry<K,V>>();
		for (int i = 0; i < N; i++)
			for (Entrada<K,V> entrada : arreglo[i])
				entradas.addLast(entrada);		
		return entradas;
	}

}