package it.polito.tdp.lab04.DAO;

public class TestDB {

	public static void main(String[] args) {
		
		/*
		 * 	This is a main to check the DB connection
		 */
		try {
			CorsoDAO cdao = new CorsoDAO();
//			cdao.getTuttiICorsi();
			int matricola = Integer.parseInt("168889");
			StudenteDAO sdao = new StudenteDAO();
			sdao.getStudente(matricola);
			System.out.println(sdao.getStudente(matricola));
			System.out.println("testDB passato");
		} catch (Exception e) {
			System.err.println("TestDB fallito");
		}
	}

}
