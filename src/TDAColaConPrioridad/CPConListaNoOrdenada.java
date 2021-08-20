package TDAColaConPrioridad;

import TDALista.*;

import java.util.Comparator;
import java.util.Iterator;

public class CPConListaNoOrdenada<K extends Comparable<K>,V> implements PriorityQueue<K,V>{
	
	private PositionList<Entry<K,V>> lista;
	protected Comparator <K> comparador;
	
	public CPConListaNoOrdenada(){
		lista= new ListaDE_CC_EPyU<Entry<K,V>>();		
	}
	
	public CPConListaNoOrdenada(Comparator<K> comp){
		lista= new ListaDE_CC_EPyU<Entry<K,V>>();
		if (comp!=null)
			comparador=comp;
		else 
			comparador= new DefaultComparator<K>();	
	}
	
	public int size(){
		return lista.size();
	}
	
	public boolean isEmpty(){
		return lista.isEmpty();
	}

	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (size()==0) throw new EmptyPriorityQueueException("dasdasd");
		Iterator<Entry<K,V>> it=lista.iterator();
		Entry<K,V> toReturn=null;
		K clave=null;
		if(it.hasNext()){
			toReturn= it.next();
			clave = toReturn.getKey();
		}
		while(it.hasNext()){
			Entry<K,V> nEntrada= it.next();
			if( nEntrada.getKey().compareTo(clave)<0){
				toReturn=nEntrada;
				clave=nEntrada.getKey();				
			}
		}
		
		return toReturn;
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if (key==null) throw new InvalidKeyException("Clave invalida");
		Entry<K,V> toReturn= new Entrada<K,V>(key,value);
		lista.addLast(toReturn);	
		return toReturn;
	}

	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if (size()==0) throw new EmptyPriorityQueueException("Lista vacia");
		
		Iterable<Position<Entry<K,V>>> pos= lista.positions();
		Iterator<Position<Entry<K,V>>> it=pos.iterator();
		Entry<K,V> toReturn=null;
		Position<Entry<K,V>> poss=null;
		Position<Entry<K,V>> nPoss=null;
		K clave=null;
		if(it.hasNext()){
			poss=it.next();
			toReturn= poss.element();
			clave = toReturn.getKey();
		}
		while(it.hasNext()){
			nPoss=it.next();
			Entry<K,V> nEntrada= nPoss.element();
			if( nEntrada.getKey().compareTo(clave)<0){
				toReturn=nEntrada;
				poss=nPoss;
				clave=nEntrada.getKey();				
			}
		}
		
		try{
			return lista.remove(poss);
			
		}
		catch(InvalidPositionException e){throw new EmptyPriorityQueueException("Chau");}
		
	}
	
}
