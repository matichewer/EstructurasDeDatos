package TDAArbol;

import TDALista.Position;
import TDALista.PositionList;

public class MyTest_arbol {

	
	public static void main(String[] args) {
		
		try {
			
			Tree<Integer> t = new Arbol<Integer>();
			
			t.createRoot(0);
			t.addLastChild(t.root(), 1);
			t.addLastChild(t.root(), 2);
			t.addLastChild(t.root(), 3);
			t.addLastChild(t.root(), 4);
			t.addLastChild(t.root(), 5);
			
			PositionList<Position<Integer>> lista = (PositionList<Position<Integer>>) t.children(t.root());
			System.out.println(lista.size());
			
		}catch (Exception e) {}
		
	}
}
