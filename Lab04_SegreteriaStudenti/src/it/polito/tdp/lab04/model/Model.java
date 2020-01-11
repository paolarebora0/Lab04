package it.polito.tdp.lab04.model;

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
}
