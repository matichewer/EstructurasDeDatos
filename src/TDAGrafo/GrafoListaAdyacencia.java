package TDAGrafo;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDE_CC_EPyU;
import TDALista.PositionList;

public class GrafoListaAdyacencia<V,E> implements Graph<V,E> {

	protected PositionList<Vertice<V,E>> nodos;
	
	public GrafoListaAdyacencia() {
		nodos = new ListaDE_CC_EPyU<Vertice<V,E>>();
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
		for(Vertice<V,E> v : nodos)
			for(Arco<V,E> e : v.getAdyacentes())
				if(e.getV1()==v)
					lista.addLast(e);
		return lista;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		if(v==null)
			throw new InvalidVertexException("incidentEdges con vertice nulo");
		Vertice<V,E> vertice = (Vertice<V,E>) v;
		PositionList<Edge<E>> toReturn = new ListaDE_CC_EPyU<Edge<E>>();
		for(Arco<V,E> arco : vertice.getAdyacentes())
			toReturn.addLast(arco);
		return toReturn;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		if(v==null)
			throw new InvalidVertexException("opposite con Vertice nulo");
		if(e==null)
			throw new InvalidEdgeException("opposite con arco nulo");
		Vertice<V,E> vert = (Vertice<V,E>) v;
		Arco<V,E> arco = (Arco<V,E>) e;
		if(vert!=arco.getV1() && vert!=arco.getV2())
			throw new InvalidEdgeException("opposite: el arco no apunta al vertice");
		Vertice<V,E> toReturn = arco.getV1()==vert ? arco.getV2() : arco.getV1();
		return toReturn;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		if(e==null)
			throw new InvalidEdgeException("endvertices con arco nulo");
		Arco<V,E> arco = (Arco<V,E>) e;
		Vertex<V> [] toReturn = (Vertex<V>[]) new Vertice[2];
		toReturn[0] = arco.getV1();
		toReturn[1] = arco.getV2();
		return toReturn;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		if(v==null || w==null)
			throw new InvalidVertexException("areAdjacent con vertice nulo");
		Vertice<V,E> vv = (Vertice<V,E>) v;
		Vertice<V,E> ww = (Vertice<V,E>) w;
		for(Arco<V,E> arco : vv.getAdyacentes())
			if(arco.getV1()==ww || arco.getV2()==ww)
				return true;
		return false;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		if(v==null)
			throw new InvalidVertexException("replace con vertice nulo");
		Vertice<V,E> vertice = (Vertice<V,E>) v;
		V toReturn = v.element();
		vertice.setRotulo(x);
		return toReturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v = new Vertice<V,E>(x);
		nodos.addLast(v);
		try {
			v.setPosicionEnNodos(nodos.last());
		}catch(EmptyListException e) {
			e.getMessage();
		}
		return v;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		if(v==null || w==null)
			throw new InvalidVertexException("insertEdge con vertice nulo");
		Vertice<V,E> vv = (Vertice<V,E>) v;
		Vertice<V,E> ww = (Vertice<V,E>) w;
		Arco<V,E> arco = new Arco<V,E>(e,vv,ww);
		vv.getAdyacentes().addLast(arco);
		ww.getAdyacentes().addLast(arco);
		try {
			arco.setPosEnAdyV1(vv.getAdyacentes().last());
			arco.setPosEnAdyV2(ww.getAdyacentes().last());
		}catch(EmptyListException e1) {
			e1.getMessage();
		}
		return arco;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		if(v==null)
			throw new InvalidVertexException("removeVertex con v nulo");
		Vertice<V,E> vertice = (Vertice<V,E>) v;
		V toReturn = vertice.element();
		try {
			for(Arco<V,E> arco : vertice.getAdyacentes())
				removeEdge(arco);
			nodos.remove(vertice.getPosicionEnNodos());
		} catch(InvalidEdgeException e1) {
				e1.getMessage();
		} catch(InvalidPositionException e2) {
			e2.getMessage();
		}
		vertice.setPosicionEnNodos(null);
		vertice.setRotulo(null);
		return toReturn;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		if(e==null)
			throw new InvalidEdgeException("removeEdge con arco nulo");
		Arco<V,E> arco = (Arco<V,E>) e;
		E toReturn = arco.element();
		try {
			arco.getV1().getAdyacentes().remove(arco.getPosEnAdyV1());
			arco.getV2().getAdyacentes().remove(arco.getPosEnAdyV2());
//			arco.setPosEnAdyV1(null);
//			arco.setPosEnAdyV2(null);
//			arco.setRotulo(null);
//			arco.setV1(null);
//			arco.setV2(null);
		}catch(InvalidPositionException e1) {
			e1.getMessage();
		}
		return toReturn;
	}
}
