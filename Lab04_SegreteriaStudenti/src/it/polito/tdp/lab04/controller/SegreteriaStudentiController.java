package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	
	List<Corso> corsi;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboCorso;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCercaNome;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    public void setModel(Model model) {

		this.model = model;
		setComboItems();
	}
    
    private void setComboItems() {

		corsi = model.getAllCorsi();
		comboCorso.getItems().addAll(corsi);
	}

    @FXML
    void doCercaCorsi(ActionEvent event) {

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {

    }

    /**
     * Cliccando il bottone verde si ottengono nome e cognome 
     * della matricola selezionata
     * 
     * @param event
     */
    @FXML
    void doCercaNome(ActionEvent event) { //BOTTONE VERDE

    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	
    	int matricola;
    	try {
			matricola = Integer.parseInt(txtMatricola.getText());
		} catch (NumberFormatException e) {
			txtResult.appendText("Devi inserire una matricola (numero intero)");
			return;
		}
    
    	Studente s = this.model.getStudente(matricola);
    	if(s == null) {
    		txtResult.appendText("La matricola inserita non esiste");
    		return;
    	}
		txtNome.setText(s.getNome());
		txtCognome.setText(s.getCognome());
		
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }
    
    
}
