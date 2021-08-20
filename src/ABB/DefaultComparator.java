package ABB;

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
	
	public static void main(String[] args) {
		java.util.Comparator<Integer> c = new DefaultComparator<Integer>();
		System.out.println(c.compare(5,4));
		System.out.println(c.compare(3,4));
		System.out.println(c.compare(4,4));
		System.out.println(c.compare(4,3));
		System.out.println(c.compare(4,5));
	}
}
