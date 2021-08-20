package TDAGrafo;

import TDALista.*;

public class Vertice<V,E> implements Vertex<V>{
	
	private V rotulo;
	private PositionList<Arco<V,E>> adyacentes; 
	private Position<Vertice<V,E>> posicionEnNodos;
	
	public Vertice(V rotu) {
		rotulo = rotu;
		adyacentes = new ListaDE_CC_EPyU<Arco<V,E>>();
	}
		
	public void setRotulo(V nuevoRotulo) {
		rotulo = nuevoRotulo;
	}
	
	public void setPosicionEnNodos(Position<Vertice<V,E>> p) {
		posicionEnNodos = p;
	}
	
	@Override
	public V element() {
		return rotulo;
	}
	
	public Position<Vertice<V,E>> getPosicionEnNodos() {
		return posicionEnNodos;
	}
	
	public PositionList<Arco<V,E>> getAdyacentes(){
		return adyacentes;
	}
}