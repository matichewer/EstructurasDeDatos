package TDACola;


/**
 * Clase PilaArreglo
 * 
 * @author Matias David Schwerdt
 * Implementación de una cola genérica usando un arreglo circular
 */

public class ColaConArregloCircular<E> implements Queue<E> {

	protected int front, rear, N;
	protected E[] datos;
	
	/**
	 * Crea una Cola utilizando un arreglo.
	 * @param max tamaño de la cola.
	 */	
	public ColaConArregloCircular(int max) {
		datos = (E[]) new Object[max];
		front = 0; rear = 0;
		N = max;
	}
	
	/**
	 * Crea una cola de tamaño 20.
	 */
	public ColaConArregloCircular() {
		this(20);
	}
	
	@Override
	public int size() {
		return (N - front + rear) % N;
	}
	
	@Override
	public boolean isEmpty() {
		return front == rear;
	}
	
	@Override
	public E front() throws EmptyQueueException {
		if( front == rear )
			throw new EmptyQueueException("La cola se encuentra vacia");
		return datos[front];
	}
	

	@Override
	public void enqueue(E element){
		if( size() == N-1 ) 
			agrandarCola();
		datos[rear] = element;
		rear = (rear+1) % N;
	}
	
	/**
	 * Remplaza el arreglo de la cola por un arreglo del doble del tamaño
	 */
	private void agrandarCola() {
		int aux = 0;
		N = N * 2;
		E [] q = (E[]) new Object[N];
		if( front == 0 )  
			for (int i=front; i<rear; i++)
			 q[i] = datos[i];
		else { // caso front > rear
			for (int j=front; j<datos.length; j++) 
				q[aux++] = datos[j];
			for (int k=0; k<rear; k++) 
				q[aux++] = datos[k];
			front = 0;
			rear = datos.length-1;			
		}
		datos = q;
	}
	
	@Override
	public E dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("La cola se encuentra vacia");
		E temp = datos[front];
		datos[front] = null;
		front = (front+1) % N;
		return temp;		
	}
	
}
