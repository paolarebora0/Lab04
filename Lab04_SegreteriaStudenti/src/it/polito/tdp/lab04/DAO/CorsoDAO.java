package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	//FATTO
	/**
	 * Ottengo tutti i corsi salvati nel Db
	 */ 
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

//				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

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

	/**
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/**
	 * Ottengo tutti gli studenti iscritti al Corso
	 * 
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "SELECT * " +
				"FROM studente s, iscrizione i " + 
				"WHERE i.matricola= s.matricola " + 
				"AND i.codins = ? " + 
				"ORDER BY s.cognome";

		List<Studente> studenti = new ArrayList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String CDS = rs.getString("CDS");

//				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				Studente s = new Studente(matricola, nome, cognome, CDS);
				studenti.add(s);
				
			}
			conn.close();


		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return studenti;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
}
