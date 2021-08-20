package TDAColaConPrioridad;

/**
 * Interface PriorityQueue
 */
 
public interface PriorityQueue<K, V> {

/**
 * Consulta la cantidad de elementos de la cola.
 * @return Cantidad de elementos de la cola.
 */	
public int size();

/**
 * Consulta si la cola esta vacia.
 * @return Verdadero si la cola esta vacia, falso en caso contrario.
 */
public boolean isEmpty();

/**
 * Devuelve la entrada con menor prioridad de la cola.
 * @return Entrada con menor prioridad.
 * @throws EmptyPriorityQueueException si la cola esta vacia.
 */
public Entry<K,V> min()throws EmptyPriorityQueueException;

/**
 * Inserta un par clave-valor y devuelve la entrada creada.
 * @param key Clave de la entrada a insertar.
 * @param value Valor de la entrada a insertar.
 * @return Entrada creada.
 * @throws InvalidKeyException si la clave es invalida.
 */
public Entry<K,V> insert(K key,V value)throws InvalidKeyException;

/**
 * Remueve y devuelve la entrada con menor prioridad de la cola.
 * @return Entrada con menor prioridad.
 * @throws EmptyPriorityQueueException si la cola esta vacia.
 */
public Entry<K,V> removeMin()throws EmptyPriorityQueueException; 
}
