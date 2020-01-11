package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(int matricola) {

		final String sql = "SELECT * FROM studente " + 
				"WHERE matricola = ?";

		Studente studente = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String CDS = rs.getString("CDS");

//				System.out.println(mat + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				studente = new Studente(matricola, nome, cognome, CDS);
				
				// Crea un nuovo JAVA Bean Corso  ****
				// Aggiungi il nuovo oggetto Corso alla lista corsi *****
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return studente;
	}
	
	/**
	 * Data una matricola restituisce i corsi a cui è iscritta
	 * 
	 * @param matricola
	 * @return lista dei corsi
	 */
	public List<Corso> getCorsiDelloStudente(int matricola) {
		
		final String sql = "SELECT * FROM corso c, iscrizione i " + 
				"WHERE c.codins = i.codins " + 
				"AND i.matricola = ?";

		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return corsi;
	}
	
	public boolean isIscritto(Corso corso, int matricola) {
		
		final String sql = "SELECT * FROM iscrizione " + 
				"WHERE codins = ?" + 
				"AND matricola = ?";

		List<Corso> corsi = new LinkedList<Corso>();
		boolean isIscritto = false;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			st.setInt(2, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {			
				isIscritto = true;
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return isIscritto;
	}
	
	public boolean iscriviStudenteAlCorso(Studente studente, Corso corso) {
		final String sql = "INSERT IGNORE INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES(?,?)";

		boolean value = false;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			
			int rs = st.executeUpdate();	

			if (rs == 1) {			
				value = true;
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return value;
	}
}
