package TDADiccionario;

public class Entrada<K,V> implements Entry<K,V>{
	
	private K clave;
	private V valor;
	
	/**
	 * Crea una entrada con los parametros
	 * @param k clave de la entrada
	 * @param v valor de la entrada
	 */
	public Entrada(K k, V v) {
		clave = k;
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
	 * Setea la clave
	 * @param k valor para asignarle a la clave
	 */
	public void setKey(K k) {
		clave = k;		
	}

	/**
	 * Setea el valor
	 * @param v valor para asignarle al valor
	 */
	public void setValue(V v) {
		valor = v;
	}
	
	@Override
	public String toString() {
		return "("+getKey()+","+getValue()+")";
	}
}
