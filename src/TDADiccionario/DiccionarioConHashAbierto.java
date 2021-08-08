package TDADiccionario;

import java.util.Iterator;
import TDALista.*;


/**
 * Clase Diccionario con hash abierto
 * Implementacion de un diccionario con hash abierto
 */

public class DiccionarioConHashAbierto<K,V> implements Dictionary<K,V>{
	
	private Dictionary<K,V> []A;
	private int tamanio; //cantidad de entrada en el diccionario
	private int N=97; //nro primo
	
	/**
	 * Creo un diccionario con hash abierto
	 */
	public DiccionarioConHashAbierto(){
		tamanio=0;
		A=(Dictionary<K,V> []) new DiccionarioConLista[N];
		for(int i=0;i<N;i++){
			A[i]=new DiccionarioConLista<K,V>();
		}
	}
	
	@Override
	public int size(){
		return tamanio;
	}
	
	@Override
	public boolean isEmpty(){
		return tamanio==0;
	}
	
	/**
	 * Funcion hash
	 * @param key clave a la cual se le aplica la funcion hash
	 * @return numero correspondiente al aplicar la funcion hash
	 */
	private int h(K key ){
		int i= key.hashCode();
		return (i%N);
	}

	/**
	 * Factor de carga
	 * @return numero correspondiente al factor de carga (cant de entradas dividido largo del arreglo)
	 */
	private float factorDeCarga(){
		return (tamanio/N);
	}
	
	/**
	 * Siguiente primo
	 * @param x un numero entero
	 * @return el siguiente numero primo al pasado por parametro
	 */
	private int siguientePrimo(int n){
		int nro=n+1;
		int i=1;
		int cant=0;
		boolean encontre=false;
		while(!encontre){
			while(i<10){
				if (nro%i==0){ cant++;}	
				i++;
			}
			if (cant==2){ 
				encontre=true;
			}	
			else{
				i=1;
				cant=0;
				nro++;
			}
		}
		return nro;		
	}
	
	/**
	 * Guarda las entradas del arreglo en uno nuevo
	 */
	private void rehashing(){
		int nuevoN= siguientePrimo(2*N);
		Dictionary<K,V> [] Aaux= (Dictionary<K,V> []) new DiccionarioConLista[nuevoN];
		for (int i=0;i<nuevoN;i++){
			Aaux[i]= new DiccionarioConLista<K,V>();			
		}
		for(int i=0;i<N;i++){
			Iterator<Entry<K,V>> it=A[i].entries().iterator();
			while(it.hasNext()){
				Entry<K,V> entrada=it.next();
				K k=entrada.getKey();
				V v=entrada.getValue();
				int u= (k.hashCode())%nuevoN;
				try{Aaux[u].insert(k,v);}
				catch(InvalidKeyException e){e.getMessage();}
			}
		}
		N= nuevoN;
		A=Aaux;
	}

	@Override
	public Entry<K,V> find(K key) throws InvalidKeyException{
		if (key==null) throw new InvalidKeyException("Clave invalida");
		return A[h(key)].find(key);		
	}

	@Override
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException{
		if (key==null) throw new InvalidKeyException("Clave invalida");
		return A[h(key)].findAll(key);
	}

	@Override
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		if (key==null) throw new InvalidKeyException("Clave invalida");
		if (factorDeCarga()>0.5){
			rehashing();
		}
		Entry<K,V> toReturn=A[h(key)].insert(key, value);
		tamanio++;
		return toReturn;
	}

	@Override
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException{
		if(e==null) throw new InvalidEntryException("Entrada invalida");
		Entry<K,V> toReturn=A[h(e.getKey())].remove(e);
		if (toReturn!=null){ tamanio--;}
		return toReturn;
	}

	@Override
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> l= new ListaDE_CC_EPyU<Entry<K,V>>();
		for(int i=0;i<A.length;i++){
			Iterator<Entry<K,V>> it=A[i].entries().iterator();
			while(it.hasNext()){
				l.addLast(it.next());
			}
		}
		return l;		
	}
}