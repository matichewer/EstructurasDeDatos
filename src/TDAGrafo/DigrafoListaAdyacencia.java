package TDAGrafo;

import TDALista.*;

public class DigrafoListaAdyacencia<V,E> implements GraphD<V,E> {

	protected PositionList<DVertice<V,E>> nodos;

	public DigrafoListaAdyacencia() {
		nodos = new ListaDE_CC_EPyU<DVertice<V,E>>();
	}

	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new ListaDE_CC_EPyU<Vertex<V>>();
		for(Vertex<V> v : nodos)
			lista.addLast(v);
		return lista;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDE_CC_EPyU<Edge<E>>();
		try {
			for(Vertex<V> v : nodos)
				for(Edge<E> e : succesorEdges(v))
					lista.addLast(e);
		}catch(InvalidVertexException e) {
			e.getMessage();
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		return null;
	}

	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		if(v==null)
			throw new InvalidVertexException("succesorEdges con vertice nulo");
		DVertice<V,E> vv = (DVertice<V,E>) v;
		return (Iterable<Edge<E>>) vv.getAdyacentes().iterator();
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		if(v==null)
			throw new InvalidVertexException("oposite con vertice nulo");
		if(e==null)
			throw new InvalidEdgeException("oposite con arco nulo");
		DVertice<V,E> vv = (DVertice<V,E>) v; 
		DArco<V,E> ee = (DArco<V,E>) e;
		if(ee.getPredecesor()!=vv && ee.getSucesor()!=vv)
			throw new InvalidEdgeException("oposite: el arco no es incidente ni emergente del vertice");
		if(ee.getPredecesor()!=vv)
			return ee.getPredecesor();
		else
			return ee.getSucesor();
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		if(e==null)
			throw new InvalidEdgeException("endvertices con arco nulo");
		DArco<V,E> ee = (DArco<V,E>) e;
		Vertex<V> [] toReturn = (Vertex<V>[]) new Vertice[2];
		toReturn[0] = ee.getPredecesor();
		toReturn[1] = ee.getSucesor();
		return toReturn;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		if(v==null || w==null)
			throw new InvalidVertexException("areAdjacent con vertice nulo");
		DVertice<V,E> vv = (DVertice<V,E>) v;
		DVertice<V,E> ww = (DVertice<V,E>) v;
		for(DArco<V,E> a : vv.getAdyacentes()) {
			if(a.getPredecesor()==ww || a.getSucesor()==ww)
				return true;
		}
		return false;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		return null;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		DVertice<V,E> v = new DVertice<V,E>(x);
		nodos.addLast(v);
		try {
			v.setPosicionEnNodos(nodos.last());
		} catch (EmptyListException e) {
			e.getMessage();
		}
		return v;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E elem) throws InvalidVertexException {
		if(v==null)
			throw new InvalidVertexException("insertEdge con vertice nulo");
		DVertice<V,E> vv = (DVertice<V,E>) v;
		DVertice<V,E> ww = (DVertice<V,E>) w;
		DArco<V,E> arco = new DArco<V,E>( elem, vv, ww );
		vv.getAdyacentes().addLast(arco);
		ww.getAdyacentes().addLast(arco);
		try {
			arco.setPosEnAdyPred(vv.getAdyacentes().last());
			arco.setPosEnAdySuc(ww.getAdyacentes().last());
		} catch (EmptyListException e) {
			e.getMessage();
		}
		return arco;
	}	

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		if(v==null)
			throw new InvalidVertexException("removeVertex con vertice nulo");
		try {
			DVertice<V,E> vv = (DVertice<V,E>) v;
			V toReturn = vv.element();
			for(DArco<V,E> a : vv.getAdyacentes()) 
				removeEdge(a);
			Position<DVertice<V,E>> pos = vv.getPosicionEnNodos();
			nodos.remove(pos);
			return toReturn;
		} catch(InvalidPositionException e) {
			e.getMessage();
		} catch(InvalidEdgeException e) {
			e.getMessage();
		}
		return null;		
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		if(e==null)
			throw new InvalidEdgeException("removeEdge con arco nulo");
		try {
			DArco<V,E> ee = (DArco<V,E>) e;
			E toReturn = ee.element();	
			
			DVertice<V,E> verticePredecesor = ee.getPredecesor();
			verticePredecesor.getAdyacentes().remove(ee.getPosEnAdyPred());
			
			DVertice<V,E> verticeSucesor = ee.getSucesor();
			verticeSucesor.getAdyacentes().remove(ee.getPosEnAdySuc());
			
//			ee.setPosEnAdyPred(null);
//			ee.setPosEnAdySuc(null);
//			ee.setPredecesor(null);
//			ee.setSucesor(null);
//			ee.setRotulo(null);
			return toReturn;			
		} catch(InvalidPositionException e1) {
			e1.getMessage();
		}
		return null;
	}	
}