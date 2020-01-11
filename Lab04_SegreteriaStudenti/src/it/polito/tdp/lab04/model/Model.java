package it.polito.tdp.lab04.model;

import java.util.Collections;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		studenteDAO = new StudenteDAO();
		corsoDAO = new CorsoDAO();
	}
	//Punto 1
	public List<Corso> getAllCorsi() {
		return corsoDAO.getTuttiICorsi();
	}
	
	//Punto 2
	public Studente getStudente(int matricola) {

		return studenteDAO.getStudente(matricola);
	}
	
	//Punto 3 
	public List<Studente> getAllStudentiIscrittiAlCorso(Corso corso) {
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	//Punto 4
	public List<Corso> getAllCorsiDelloStudente(int matricola) {
		return studenteDAO.getCorsiDelloStudente(matricola);
	}
	
	//Punto 5
	public boolean isIscritto(Corso c, int matricola) {
		return studenteDAO.isIscritto(c, matricola);
	}
	
	//Punto 6 
	public boolean iscriviStudenteAlCorso(Studente s, Corso c) {
		return studenteDAO.iscriviStudenteAlCorso(s, c);
	}
}
