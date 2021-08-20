package ABB;
/**
 * Implementaci�n de una Entrada Comparable que almacena un par Clave-Valor.
 * Asume el tipo de dato de la clave es comparable.
 * @author FJoaquin (federico.joaquin@cs.uns.edu.ar)
 *
 * @param <K> Tipo de dato de la clave de la entrada; se asume comparable.
 * @param <V> Tipo de dato del valor de la entrada.
 */
public class EntradaComparable<K extends Comparable<K>, V> extends Entrada<K, V> implements Comparable<EntradaComparable<K,V>> {
	/**
	 * Inicializa una nueva entrada comparable con clave y valor.
	 * @param c Clave de la nueva entrada comparable.
	 * @param v Valor de la nueva entrada comparable.
	 */
	public EntradaComparable(K c, V v){
		super(c, v);
	}

	@Override
	public int compareTo(EntradaComparable<K, V> o) {
		return clave.compareTo(o.getKey());
	}
}
