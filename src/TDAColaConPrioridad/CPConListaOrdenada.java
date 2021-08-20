package TDAColaConPrioridad;
import TDALista.*;
import java.util.Iterator;
import java.util.Comparator;

public class CPConListaOrdenada<K extends Comparable<K>,V> implements PriorityQueue<K,V> {
	
	protected PositionList<Entry<K,V>> lista;
	protected Comparator <K> comparador;
	
	public CPConListaOrdenada(){
		lista= new ListaDE_CC_EPyU<Entry<K,V>>();
	}
	
	public CPConListaOrdenada(Comparator<K> comp){
		lista= new ListaDE_CC_EPyU<Entry<K,V>>();
		if (comp!=null)
			comparador=comp;
		else 
			comparador= new DefaultComparator<K>();	
	}	
	
	public int size() {
		return lista.size();
	}

	public boolean isEmpty() {
		return lista.isEmpty();
	}

	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (size()==0) throw new EmptyPriorityQueueException("Cola vacia");
		Entry<K,V> toReturn=null;
		try {
			toReturn=lista.first().element();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if (key==null) throw new InvalidKeyException("Clave invalida");
		
		Iterable<Position<Entry<K,V>>> pos= lista.positions();
		Iterator<Position<Entry<K,V>>> it=pos.iterator();
		Entry<K,V> toReturn=new Entrada<K,V>(key,value);
		Position<Entry<K,V>> poss=null;
		boolean inserte=false;
		Entry<K,V> entrada=null;
		if(it.hasNext()){
			poss=it.next();
			entrada= poss.element();
			if (entrada.getKey().compareTo(key)>0){
				lista.addFirst(toReturn);
				inserte=true;
			}
		}
		while(it.hasNext() && !inserte){
			poss=it.next();
			entrada= poss.element();
			if (entrada.getKey().compareTo(key)>0){
				try {
					lista.addBefore(poss, toReturn);
				} catch (InvalidPositionException e) {
					e.printStackTrace();
				}
				inserte=true;
			}
		}
		if (!inserte){
			lista.addLast(toReturn);
		}
		return toReturn;
	}

	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if (size()==0) throw new EmptyPriorityQueueException("Cola vacia");
		Entry<K, V> toReturn=null;
		try {
			toReturn = lista.remove(lista.first());
			}
		catch (InvalidPositionException e) {
				e.printStackTrace();
		}
		catch (EmptyListException e) {
			e.printStackTrace();
		}
		return toReturn;

	}

}
