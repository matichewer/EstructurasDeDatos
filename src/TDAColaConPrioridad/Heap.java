package TDAColaConPrioridad;

import java.util.Comparator;

public class Heap<K,V>  implements PriorityQueue<K,V>{
	
	protected Entrada<K,V> [] elementos;
	protected Comparator<K> comp;
	protected int size;
	
	public Heap(Comparator<K> compa){
		elementos = (Entrada<K,V> []) new Entrada[10000];
		comp= compa;
		size=0;
	}
	
	public Heap(){
		elementos = (Entrada<K,V> []) new Entrada[10000];
		comp= null;
		size=0;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return (size==0);
	}
	
	public Entry<K,V> min() throws EmptyPriorityQueueException{
		if (isEmpty() ) throw new EmptyPriorityQueueException("Cola vacia");
		return elementos[1];
	}
	
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		if (key == null) throw new InvalidKeyException("Insert: posicion invalida");
		
		Entrada<K,V> entrada= new Entrada<K,V>(key,value);
		elementos[++size]= entrada;
		
		//burbujeo para arriba
		int i=size;
		boolean seguir=true;
		while (i>1 && seguir){
			
			Entrada<K,V> elemActual=elementos[i];
			Entrada<K,V> elemPadre=elementos[i/2];
			if (comp.compare(elemActual.getKey(),elemPadre.getKey())<0){
				Entrada<K,V> aux=elementos[i];
				elementos[i]=elementos[i/2];
				elementos[i/2]=aux;
				i/=2;
			}
			else{//si no se puede intercambiar es pq esta ordenada
				seguir=false;				
			}
		}
		return entrada;				
	}
	
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException{
		if (isEmpty()) throw new EmptyPriorityQueueException("Cola vacia");
		
		Entry<K,V> entrada= min(); //salvo valor a retornar
		if (size==1) { 
			elementos[1]=null; 
			size--; 
			return entrada;
		}
		else{
			//paso el ultimo a la raiz y lo borro del final
			elementos[1]=elementos[size];
			elementos[size]=null;
			size--;
			//burbujeo la raiz abajo
			int i=1; //estoy parado en la raiz
			boolean seguir=true;
			while (seguir){
				//calculo la posicion de los hijos
				int hi=i*2;
				int hd=i*2+1;
				boolean tieneHI= hi<=size();
				boolean tieneHD= hd<=size();
				if (!tieneHI) seguir=false;
				else{
					int m; //minimo d elos hijos de i
					if (tieneHD){
						//calculo cual es el menor de los hijos
						if (comp.compare(elementos[hi].getKey(),elementos[hd].getKey())<0) m=hi;
						else m=hd;
					}
					else m=hi;
					//me fijo si hay que intercambiar el actual con el menor de sus hijos
					if (comp.compare(elementos[i].getKey(),elementos[m].getKey())>0){
						Entrada<K,V> aux=elementos[i];
						elementos[i]=elementos[m];
						elementos[m]=aux;
						i=m;//ahora actualizo a partir de m
					} else seguir=false;
				}
			}
			return entrada;
		}
	}	
}