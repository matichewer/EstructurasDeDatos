package TDADiccionario;

public interface Entry<K,V> {
	
	/**
	 * Devuelve la clave de la entrada
	 * @return clave de la entry
	 */
	public K getKey();
	
	/**
	 * Devuelve el valor de la entrada
	 * @return valor de la entry
	 */
	public V getValue();
	
}