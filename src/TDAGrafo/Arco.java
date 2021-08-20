package TDAGrafo;

import TDALista.*;

public class Arco<V,E> implements Edge<E> {

	private E rotulo;
	private Vertice<V,E> v1,v2;
	private Position<Arco<V,E>> posAdyV1, posAdyV2;
	
	public Arco(E rotu, Vertice<V,E> v1, Vertice<V,E> v2 ){
		rotulo = rotu;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public void setRotulo(E rot) {
		rotulo = rot;
	}	
	
	public void setV1(Vertice<V,E> v1) {
		this.v1 = v1;
	}
	
	public void setV2(Vertice<V,E> v2) {
		this.v2 = v2;
	}
	
	public void setPosEnAdyV1(Position<Arco<V,E>> pos) {
		posAdyV1 = pos;
	}
	
	public void setPosEnAdyV2(Position<Arco<V,E>> pos) {
		posAdyV2 = pos;
	}
	
	@Override
	public E element() {
		return rotulo;
	}
	
	public Vertice<V, E> getV1() {
		return v1;
	}
	
	public Vertice<V, E> getV2() {
		return v2;
	}
	
	public Position<Arco<V, E>> getPosEnAdyV1() {
		return posAdyV1;
	}
	
	public Position<Arco<V, E>> getPosEnAdyV2() {
		return posAdyV2;
	}
}
