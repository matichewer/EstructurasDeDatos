package TDAGrafo;

import TDALista.*;

public class DVertice<V,E> implements Vertex<V>{
	
	private V rotulo;
	private PositionList<DArco<V,E>> adyacentes; 
	private Position<DVertice<V,E>> posicionEnNodos;
	
	public DVertice(V rotu) {
		rotulo = rotu;
		adyacentes = new ListaDE_CC_EPyU<DArco<V,E>>();
	}
		
	public void setRotulo(V nuevoRotulo) {
		rotulo = nuevoRotulo;
	}
	
	public void setPosicionEnNodos(Position<DVertice<V,E>> p) {
		posicionEnNodos = p;
	}
	
	@Override
	public V element() {
		return rotulo;
	}
	
	public Position<DVertice<V,E>> getPosicionEnNodos() {
		return posicionEnNodos;
	}
	
	public PositionList<DArco<V,E>> getAdyacentes(){
		return adyacentes;
	}
}