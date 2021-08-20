package TDAColaConPrioridad;

import java.util.Comparator;
/**
 * Clase que modela un comparador por defecto.
 * Asume el tipo de dato E, comparable.
 */
public class DefaultComparator<E extends Comparable<E>> implements Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
		return o1.compareTo(o2);
	}
}
