package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
}
