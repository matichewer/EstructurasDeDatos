package TDAMapeo;
import TDALista.*;


/**
 * Clase MapeoConHashCerrado
 * Implementacion de un mapeo utilizando hash cerrado
 * @param <K> Tipo de dato de la clave del par.
 * @param <V> Tipo de dato del valor del par.
 */

public class MapeoConHashCerrado<K,V> implements Map<K,V> {
	
	protected Entrada<K,V> [] arreglo;
	protected int N; // tamanio del arreglo, debe ser un numero primo
	protected int tamanio; // cantidad de entradas del mapeo
	protected final float factor= 0.5F;
	protected Entrada<K,V> Disponible;
	
	/**
	 * Creo un mapeo con hash cerrado
	 */
	public MapeoConHashCerrado(int N) {
		arreglo = new Entrada[N];
		this.N = N;
		tamanio = 0;
		Disponible = new Entrada<K,V>(null,null);
	}
	
	public MapeoConHashCerrado(){
		this(997);
	}
	
	/**
	 * Funcion hash
	 * @param key clave a la cual se le aplica la función hash
	 * @return numero al aplicar la función hash
	 */
	private int funcionHash(K key){
		return Math.abs(key.hashCode() % N);
	}

	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		return tamanio == 0;
	}

	private void checkKey(K key) throws InvalidKeyException {
		if( key==null )
			throw new InvalidKeyException("La clave no puede ser nula");
	}
	
	@Override
	public V get(K k) throws InvalidKeyException{ 
		checkKey(k);
		V valueToReturn = null;
        int cont = funcionHash(k);
        int aux = 1;        
		while(aux<=N && arreglo[cont]!=null && valueToReturn==null) {
			if(arreglo[cont]!=Disponible && arreglo[cont].getKey().equals(k))
				valueToReturn = arreglo[cont].getValue();
			cont = (cont+1) % N;//Esto hace que recorra de manera circular	
			aux++;
		}		
		return valueToReturn;
	}

	@Override
	public V put(K k,V v) throws InvalidKeyException{
		checkKey(k);
		if((tamanio/N)>=factor) 
			rehashing();
		
		V valueToReturn = null;
		int cont = funcionHash(k);
		boolean seguir = true;
		int aux = 1;
		
		while(aux<=arreglo.length && seguir){
			if(arreglo[cont]==null||arreglo[cont]==Disponible) { //Si encuentra un Disponible,pone una nueva Entrada en esa pos
				arreglo[cont]=new Entrada<K,V>(k,v);
				seguir=false;
				tamanio++;
			}else if(arreglo[cont].getKey().equals(k)) {//Si la clave de arreglo[cont] es igual a k, guarda valor anterior y lo remplaza con v
				valueToReturn = arreglo[cont].getValue();
				arreglo[cont].setValue(v);
				seguir=false;
			}else {
				cont=(cont+1)%arreglo.length;//Esto hace que recorra de manera circular
			}
			aux++;
		}

		return valueToReturn;
			
	}
	
	/**
	 * Guarda las entradas del arreglo en uno nuevo
	 */
	private void rehashing() {
		Iterable<Entry<K,V>> entradas = this.entries();
		
		N = siguientePrimo(arreglo.length*2);
		arreglo=(Entrada<K,V> [])new Entrada[N];
		tamanio=0;
		try {
		for(Entry<K,V> e:entradas) 
			this.put(e.getKey(),e.getValue());
		}catch(InvalidKeyException f) {
			f.printStackTrace();
		}

	}
	/**
	 * Siguiente primo
	 * @param x un numero entero
	 * @return el siguiente numero primo al pasado por parametro
	 */
	private int siguientePrimo(int x) {
		int toReturn = 0;
		
		boolean esPrimo = false;
		while(!esPrimo) {
			
			esPrimo = true;
			for (int j = 2; (j<=Math.sqrt(x)) && (esPrimo); j++) {
				if((x % j) == 0) {
					esPrimo=false;
					x++;
				}
			}
			if(esPrimo)
				toReturn= x;
		}
		return toReturn;
	}

	@Override
	public V remove(K k) throws InvalidKeyException{
		if(k==null)
			throw new InvalidKeyException("");

		V toret=null;
		int cont=funcionHash(k);
		int aux=1;//Cuenta la cantidad de vueltas del while
		
		while(aux<=arreglo.length && arreglo[cont]!=null && toret==null) {
			if(arreglo[cont]!=Disponible && arreglo[cont].getKey().equals(k)) {
				toret=arreglo[cont].getValue();
				arreglo[cont]=Disponible;
				tamanio--;
			}else {
				cont=(cont+1)%arreglo.length; //Esto hace que recorra de manera circular
			}	
			aux++;
		}
		return toret;		
	}

	@Override
	public Iterable<K> keys(){
		PositionList<K> l=new ListaDE_CC_EPyU<K>();
		
		for(Entrada<K,V> e:arreglo)
			if(e!=null && e!=Disponible)
			    l.addLast(e.getKey());
		
		return l;
	}

	@Override
	public Iterable<V> values(){
		PositionList<V> l=new ListaDE_CC_EPyU<V>();
		
		for(Entrada<K,V> e:arreglo)
			if(e!=null && e!=Disponible)
			    l.addLast(e.getValue());
		
		return l;
	}
	
	@Override
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> l=new ListaDE_CC_EPyU<Entry<K,V>>();
		
		for(Entrada<K,V> e:arreglo)
			if(e!=null && e!=Disponible)
			    l.addLast(e);
		
		return l;
	}
}