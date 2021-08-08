package TDAMapeo;

public interface Map<K,V> {
	
	/**
	 * Consulta el numero de entradas del mapeo.
	 * @return Numero de entradas del mapeo.
	 */
	public int size();
	
	/**
	 * Consulta si el mapeo esta vacio.
	 * @return Verdadero si el mapeo esta vacio, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Busca una entrada con clave igual a una clave dada y devuelve el valor asociado, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Valor de la entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parametro es invalida.
	 */
	public V get(K key)throws InvalidKeyException;
	
	/**
	 * Si el mapeo no tiene una entrada con clave key, inserta una entrada con clave key y valor value en el mapeo y devuelve null. 
	 * Si el mapeo ya tiene una entrada con clave key, entonces remplaza su valor y retorna el viejo valor.
	 * @param key Clave de la entrada a crear.
	 * @param value Valor de la entrada a crear. 
	 * @return Valor de la vieja entrada.
	 * @throws InvalidKeyException si la clave pasada por parametro es invalida.
	 */
	public V put(K key, V value) throws InvalidKeyException;
	
	/**
	 * Remueve la entrada con la clave dada en el mapeo y devuelve su valor, o nulo si no fue encontrada.
	 * @param e Entrada a remover.
	 * @return Valor de la entrada removida.
	 * @throws InvalidKeyException si la clave pasada por parametro es invalida.
	 */
	public V remove(K key) throws InvalidKeyException;
	
	/**
	 * Retorna una coleccion iterable con todas las claves del mapeo.
	 * @return Coleccion iterable con todas las claves del mapeo.
	 */
	public Iterable<K> keys();
	
	/**
	 * Retorna una coleccion iterable con todas los valores del mapeo.
	 * @return Coleccion iterable con todas los valores del mapeo.
	 */
	public Iterable<V> values();
	
	/**
	 * Retorna una coleccion iterable con todas las entradas del mapeo.
	 * @return Coleccion iterable con todas las entradas del mapeo.
	 */
	public Iterable<Entry<K,V>> entries();
}
