package ABB;

import java.util.Comparator;

public class ComparatorABB<K extends Comparable<K>> implements Comparator<K> {

	public int compare(K o1, K o2) {
		return o1.compareTo(o2);
	}

}
