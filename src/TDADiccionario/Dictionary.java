package TDADiccionario;


public interface Dictionary<K,V>
{
	/**
	 * Consulta el numero de entradas del diccionario.
	 * @return Numero de entradas del diccionario.
	 */
	public int size();
	
	/**
	 * Consulta si el diccionario está vacio.
	 * @return Verdadero si el diccionario está vacio, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Busca una entrada con clave igual a una clave dada y la devuelve, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parametro es invalida.
	 */
	public Entry<K,V> find(K key) throws InvalidKeyException;
	
	/**
	 * Retorna una coleccion iterable que contiene todas las entradas con clave igual a una clave dada.
	 * @param key Clave de las entradas a buscar.
	 * @return Coleccion iterable de las entradas encontradas.
	 * @throws InvalidKeyException si la clave pasada por parametro es invalida.
	 */
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException;
	
	/**
	 * Inserta una entrada con una clave y un valor dado en el diccionario y retorna la entrada creada.
	 * @param key Clave de la entrada a crear.
	 * @return value Valor de la entrada a crear.
	 * @throws InvalidKeyException si la clave pasada por parametro es invalida.
	 */
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException;
	
	/**
	 * Remueve una entrada dada en el diccionario y devuelve la entrada removida.
	 * @param e Entrada a remover.
	 * @return Entrada removida.
	 * @throws InvalidEntryException si la entrada no está en el diccionario o es invalida.
	 */
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException;
	
	/**
	 * Retorna una coleccion iterable con todas las entradas en el diccionario.
	 * @return Coleccion iterable de todas las entradas.
	 */
	public Iterable<Entry<K,V>> entries();
	
}
