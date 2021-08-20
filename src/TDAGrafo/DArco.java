package TDAGrafo;

import TDALista.*;

public class DArco<V,E> implements Edge<E> {

	private E rotulo;
	private DVertice<V,E> predecesor, sucesor;
	private Position<DArco<V,E>> posEnAdyPred, posEnAdySuc;
	
	public DArco(E rotulo, DVertice<V,E> pred, DVertice<V,E> suc){
		this.rotulo = rotulo;
		predecesor = pred;
		sucesor = suc;
	}
	
	public void setRotulo(E rot) {
		rotulo = rot;
	}	
	
	public void setPredecesor(DVertice<V,E> predecesor) {
		this.predecesor = predecesor;
	}
	
	public void setSucesor(DVertice<V,E> sucesor) {
		this.sucesor = sucesor;
	}
	
	public void setPosEnAdyPred(Position<DArco<V,E>> posEnAdyPred) {
		this.posEnAdyPred = posEnAdyPred;
	}
	
	public void setPosEnAdySuc(Position<DArco<V,E>> posEnAdySuc) {
		this.posEnAdySuc = posEnAdySuc;
	}
	
	@Override
	public E element() {
		return rotulo;
	}
	
	public DVertice<V,E> getPredecesor() {
		return predecesor;
	}
	
	public DVertice<V,E> getSucesor() {
		return sucesor;
	}
	
	public Position<DArco<V,E>> getPosEnAdyPred() {
		return posEnAdyPred;
	}
	
	public Position<DArco<V,E>> getPosEnAdySuc() {
		return posEnAdySuc;
	}
}
