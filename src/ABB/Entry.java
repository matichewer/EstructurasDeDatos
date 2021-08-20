package ABB;
/**
 * Modela un par Clave-Valor denominado entrada.
 * @param <K> Tipo de dato de la clave del par.
 * @param <V> Tipo de dato del valor del par.
 */
public interface Entry<K, V> {
	/**
	 * Obtiene la clave asociada a la entrada.
	 * @return Clave de la entrada.
	 */
	public K getKey();
	/**
	 * Obtiene el valor asociado a la entrada.
	 * @return Valor de la entrada.
	 */
	public V getValue();
	
}
