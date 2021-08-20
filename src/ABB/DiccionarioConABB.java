package ABB;


// REVISAR EL TESTER PORQUE NO TERMINABA DE DAR LUZ VERDE !!!


import java.util.Iterator;

import TDALista.*;


public class DiccionarioConABB<K extends Comparable<K>, V> implements Dictionary<K, V> {
	protected ABB<EntradaComparable<K,PositionList<Entry<K,V>>>> abb;
	protected int tamanio;

	public DiccionarioConABB() {
		abb = new ABB<EntradaComparable<K,PositionList<Entry<K,V>>>>(null);
	    tamanio = 0;
	}
	
	public int size() {
		return tamanio;
	}


	public boolean isEmpty() {
		return tamanio==0;
	}


	@Override
	public Entry<K,V> find(K key) throws InvalidKeyException {
		checkKey(key);
		EntradaComparable<K,PositionList<Entry<K,V>>> entrada = new EntradaComparable<K,PositionList<Entry<K,V>>>(key, null);
		NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo;
		
		nodo = abb.buscar(entrada);
	
		if (nodo.getRotulo() !=null) {
			try {
				//System.out.println("buscando la clave: "+key);
				return nodo.getRotulo().getValue().first().element();
					//	new EntradaComparable<K,V>(key, nodo.getRotulo().getValue().first().element().getValue());
			} catch (EmptyListException e) {
				System.out.println("error buscando la clave: "+key);
				e.printStackTrace();
			}
	   }
		
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		EntradaComparable<K,PositionList<Entry<K,V>>> entrada;
		NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo;
		PositionList<Entry<K,V>> L=new ListaDE_CC_EPyU<Entry<K,V>>();
		entrada = new EntradaComparable<K,PositionList<Entry<K,V>>>(key, null);
		nodo = abb.buscar(entrada);
		if (nodo.getRotulo() !=null) {
	    for(Entry<K,V> e: nodo.getRotulo().getValue())
	    	L.addLast(e);
	}
		return L;

	}

	/*
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		EntradaComparable<K,PositionList<Entry<K,V>>> entrada;
		NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo;
		Entry<K,V >a_retornar = new EntradaComparable<K,V>(key, value);
		checkKey(key);
		entrada =new EntradaComparable<K,PositionList<Entry<K,V>>>(key, null);
		nodo = abb.buscar(entrada);
		// Si el nodo correspondiente a entry es DUMMY, no existe en el ABB una entrada con clave key, por lo que se debe
		// agregar una nueva entrada. Caso contrario, existe entrada con clave key, y se debe modificar su valor.
		if (nodo.getRotulo() == null) {
			abb.expandir(nodo, entrada);
			entrada.setValue(new ListaDE_CC_EPyU<Entry<K,V>>());
			entrada.getValue().addLast(a_retornar);

		}else {
			nodo.getRotulo().getValue().addLast(a_retornar);

		}
		tamanio++;
		return a_retornar;
	}*/
	
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		EntradaComparable<K,PositionList<Entry<K,V>>> entrada = new EntradaComparable<K,PositionList<Entry<K,V>>>(key, null);
		NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo;
		Entry<K,V> a_retornar = new EntradaComparable<K,V>(key, value);
		nodo = abb.buscar(entrada);
		// Si el nodo correspondiente a entry no es DUMMY, existe al menos una entrada en ABB con la clave key.
		if (nodo.getRotulo() != null) 
			nodo.getRotulo().getValue().addLast(a_retornar);
		else {
			abb.insertar(entrada);
			//abb.expandir(nodo, entrada);
			entrada.setValue(new ListaDE_CC_EPyU<Entry<K,V>>());
			entrada.getValue().addLast(a_retornar);
		}
		tamanio++;
		return a_retornar;
		
		}

	

	/*
	@Override
	public Entry<K,V> remove(Entry<K, V> e) throws InvalidEntryException {
		if (e==null )
			throw new InvalidEntryException("");

		EntradaComparable<K,PositionList<Entry<K,V>>> entrada;
		boolean encontre=false;
		NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo;
		Entry<K,V >a_retornar =new EntradaComparable<K,V>(e.getKey(),e.getValue());
		Entry<K,V> ret=null;
		entrada =new EntradaComparable<K,PositionList<Entry<K,V>>>(e.getKey(), null);
		nodo = abb.buscar(entrada);
		
		if (nodo.getRotulo() != null) {
			for (Position<Entry<K,V>> k: nodo.getRotulo().getValue().positions()) {
		        if (k.element()==a_retornar) {
		        	encontre=true;
					ret= k.element(); 
					try {
						entrada.getValue().remove(k);
						//nodo.getRotulo().getValue().remove(k);
						
					
					} catch (InvalidPositionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
			}
	
		tamanio--;  
			
			 if (nodo.getRotulo().getValue().isEmpty()) 
				   abb.eliminar(entrada);
		
		}
			
		return ret; //a_retornar;
	}*/
	
	
	@Override
	public Entry<K,V> remove(Entry<K, V> e) throws InvalidEntryException {
		if (e==null )
			throw new InvalidEntryException("remove(e): la entrada es nula");
		
		EntradaComparable<K,PositionList<Entry<K,V>>> entrada = new EntradaComparable<K,PositionList<Entry<K,V>>>(e.getKey(), null);
		NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo = abb.buscar(entrada);
		//System.out.println("Borrando Nodo: " + nodo.getRotulo().getKey() + " - " + nodo.getRotulo().getValue());
		Entry<K,V> entradaABuscar = new EntradaComparable<K,V>(e.getKey(),e.getValue());
		Entry<K,V> toReturn = null;
		boolean encontre = false;
		
		
		if (nodo.getRotulo() == null) {
			System.out.println("nodo es nulo");
			throw new InvalidEntryException("La entrada no existe");
		}
		
		PositionList<Entry<K,V>> lista = nodo.getRotulo().getValue();
		for (Position<Entry<K,V>> k: nodo.getRotulo().getValue().positions()) {
	       // if (k.element().getValue().equals(entradaABuscar.getValue())) {
			if(k.element()==e) {
	        	encontre=true;
	        	toReturn = k.element(); 
	        	if(nodo.getRotulo().getValue().size()==1) {
					entrada.setValue(nodo.getRotulo().getValue());
					abb.eliminar(entrada);	
					try {
						lista.remove(k);
					} catch (InvalidPositionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	} else {
					try {
						nodo.getRotulo().getValue().remove(k);
					} catch (InvalidPositionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}
				tamanio--;  
				return toReturn;
			 }
		}
		
		if(!encontre) {
			System.out.println("no se encontro");
			throw new InvalidEntryException("La entrada no existe");
		}
		return toReturn;
	}
	

	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> lista = new ListaDE_CC_EPyU<Entry<K, V>>();
		if (abb.getRoot().getRotulo()!=null)
		entries_aux(lista, abb.getRoot());
		return lista;
	}

	private void entries_aux(PositionList<Entry<K, V>> lista,NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo) {
		if (nodo.getRotulo() != null) {
			for (Entry<K,V> e: nodo.getRotulo().getValue())
			        lista.addLast(e);
				
			if (nodo.getLeft() != null)
				entries_aux(lista, nodo.getLeft());
			if (nodo.getRight() != null)
				entries_aux(lista, nodo.getRight());
		}

	}


	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("La clave es nula.");
	}


}


