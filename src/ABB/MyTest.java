package ABB;


public class MyTest {

	
	public static void main(String[] args) {
		
				
		Dictionary<Integer, Integer> dic = new DiccionarioConABB<Integer,Integer>();
		try {
			System.out.println("Dicc vacio, debe dar true --> "+dic.isEmpty());
			System.out.println(dic.insert(1, 10));
			System.out.println("Dicc con 1 elemento, debe dar false -> "+dic.isEmpty());
			System.out.println(dic.find(1));
			System.out.println(dic.remove(dic.find(1)));
			System.out.println("true -> "+dic.isEmpty());

			System.out.println(dic.insert(1, 11));
			System.out.println(dic.insert(1, 12));
			System.out.println(dic.insert(1, 14));
			System.out.println(dic.insert(2, 20));
			System.out.println(dic.insert(3, 30));
			System.out.println(dic.size());
			
			System.out.println("\n\n buscarr:");
			Iterable<Entry<Integer, Integer>> lista = dic.findAll(1);
			for(Entry<Integer,Integer> e : lista)
				System.out.println(e.toString());

			System.out.println("BORRO Y VUELVO A BUSCAR");
			dic.remove(dic.find(1));
			dic.remove(dic.find(1));
			lista = dic.findAll(1);
			for(Entry<Integer,Integer> e : lista)
				System.out.println(e.toString());
			

			System.out.println("BORRO ultimo elemento");
			dic.remove(dic.find(1));
			lista = dic.findAll(1);
			for(Entry<Integer,Integer> e : lista)
				System.out.println(e.toString());

			dic.remove(dic.find(1));
			
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
