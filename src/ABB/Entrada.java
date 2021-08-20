package ABB;
/**
 * Implementacion de una Entrada que almacena un par Clave-Valor.
 * @param <K> Tipo de dato de la clave del par.
 * @param <V> Tipo de dato del valor del par.
 */
public class Entrada<K, V> implements Entry<K,V> {
	
	protected K clave;
	protected V valor;
	
	/**
	 * Incializa una entrada con clave y valor.
	 * @param c Clave de la nueva entrada.
	 * @param v Valor de la nueva entrada.
	 */
	public Entrada(K c, V v){
		clave = c;
		valor = v;
	}
	
	@Override
	public K getKey() {
		return clave;
	}

	@Override
	public V getValue() {
		return valor;
	}
	/**
	 * Modifica la clave de la entrada.
	 * @param k Nueva clave de la entrada.
	 */
	public void setKey(K k){
		clave = k;
	}
	/**
	 * Modifica el valor de la entrada.
	 * @param v Nuevo valor de la entrada.
	 */
	public void setValue(V v){
		valor = v;
	}
	

	public String toString() {
		return "("+clave.toString() + ";" + valor.toString()+")";
	}
}
