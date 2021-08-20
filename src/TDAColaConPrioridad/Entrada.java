package TDAColaConPrioridad;


public class Entrada<K,V> implements Entry<K,V>{
	
	protected K clave;
	protected V valor;
	
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
	
	public void setKey(K k) {
		clave = k;		
	}
	
	public void setValue(V v) {
		valor = v;
	}
	
	@Override
	public String toString() {
		return "("+getKey()+","+getValue()+")";
	}
}
