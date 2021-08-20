package TDAGrafo;

/**
 * Interface Grafo para grafos NO dirigidos.
 */
public interface Graph<V,E> {
	
	/**
	 * Devuelve una coleccion iterable de vertices.
	 * @return Una coleccion iterable de vertices.
	 */
	public Iterable<Vertex<V>> vertices();
	
	/**
	 * Devuelve una coleccion iterable de arcos.
	 * @return Una coleccion iterable de arcos.
	 */
	public Iterable<Edge<E>> edges();
	
	/**
	 * Devuelve una coleccion iterable de arcos incidentes a un vertice v.
	 * @param v Un vertice.
	 * @return Una coleccion iterable de arcos incidentes a un vertice v.
	 * @throws InvalidVertexException si el vertice es invalido.
	 */
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException;
	
	
	/**
	 * Devuelve el vertice opuesto a un Arco E y un vertice V.
	 * @param v Un vertice
	 * @param e Un arco
	 * @return El vertice opuesto a un Arco E y un vertice V.
	 * @throws InvalidVertexException si el vertice es invalido.
	 * @throws InvalidEdgeException si el arco es invalido.
	 */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException;
	
	/**
	 * Devuelve un Arreglo de 2 elementos con lo vertices extremos de un Arco e.
	 * @param  e Un arco
	 * @return Un Arreglo de 2 elementos con los extremos de un Arco e.
	 * @throws InvalidEdgeException si el arco es invalido.
	 */
	public Vertex<V> [] endvertices(Edge<E> e) throws InvalidEdgeException;
	
	/**
	 * Devuelve verdadero si el vertice w es adyacente al vertice v.
	 * @param v Un vertice
	 * @param w Un vertice
	 * @return Verdadero si el vertice w es adyacente al vertice v, falso en caso contrario.
	 * @throws InvalidVertexException si uno de los vertices es invalido.
	 */
	public boolean areAdjacent(Vertex<V> v,Vertex<V> w) throws InvalidVertexException;
	
	/**
	 * Reemplaza el rotulo de v por un rotulo x.
	 * @param v Un vertice
	 * @param x Rotulo
	 * @return El rotulo anterior del vertice v al reemplazarlo por un rotulo x.
	 * @throws InvalidVertexException si el vertice es invalido.
	 */
	public V replace(Vertex<V> v, V x) throws InvalidVertexException;
	
	/**
	 * Inserta un nuevo vertice con rotulo x.
	 * @param x rotulo del nuevo vertice
	 * @return Un nuevo vertice insertado.
	 */
	public Vertex<V> insertVertex(V x);
	
	/**
	 * Inserta un nuevo arco con rotulo e, con vertices extremos v y w.
	 * @param v Un vertice
	 * @param w Un vertice
	 * @param e rotulo del nuevo arco.
	 * @return Un nuevo arco.
	 * @throws InvalidVertexException si uno de los vertices es invalido.
	 */
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException;
	
	/**
	 * Remueve un vertice V y retorna su rotulo.
	 * @param v Un vertice
	 * @return rotulo de V.
	 * @throws InvalidVertexException si el vertice es invalido.
	 */
	public V removeVertex(Vertex<V> v) throws InvalidVertexException;
	
	/**
	 * Remueve un arco e y retorna su rotulo.
	 * @param e Un arco
 	 * @return rotulo de E.
	 * @throws InvalidEdgeException si el arco es invalido.
	 */
	public E removeEdge(Edge<E> e) throws InvalidEdgeException;
}
