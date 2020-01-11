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

    /**
     * Trova tutti i corsi a cui è iscritta la matricola selezionata
     * 
     * @param event
     */
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	
    	int matricola;
    	try {
			matricola = Integer.parseInt(txtMatricola.getText());
			
			List<Corso> corsi = this.model.getAllCorsiDelloStudente(matricola);
        	
        	for(Corso c: corsi) {
        		txtResult.appendText(c.toString() + "\n");
        		
        	}
		
		} catch (NumberFormatException e) {
			txtResult.appendText("Devi inserire una matricola (numero intero)");
			return;
		}
    }

    /**
     * Cliccando il tasto "Cerca Iscritti Corso" si ottengono
     * tutti gli studenti iscritto al corso selezionato nel
     * menu a tendina 
     * 
     * @param event
     */
    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	txtResult.clear();
    	try {
			
    		Corso c = comboCorso.getValue();
    		
    		List<Studente> studenti = this.model.getAllStudentiIscrittiAlCorso(c);
        	
        	for(Studente s: studenti) {
        		txtResult.appendText(s.toString() + "\n");
        		
        	}
		} catch (Exception e) {
			txtResult.appendText("Deve scegliere un corso!");
		}
       	
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
			Studente s = this.model.getStudente(matricola);
	    	if(s == null) {
	    		txtResult.appendText("La matricola inserita non esiste");
	    		return;
	    	}
			txtNome.setText(s.getNome());
			txtCognome.setText(s.getCognome());
			
		} catch (NumberFormatException e) {
			txtResult.appendText("Devi inserire una matricola (numero intero)");
			return;
		}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    	txtResult.clear();
    	
    	int matricola;
    	try {
    		
			matricola = Integer.parseInt(txtMatricola.getText());
			Studente s = this.model.getStudente(matricola);
	    	if(s == null) {
	    		txtResult.appendText("La matricola inserita non esiste");
	    		return;
	    	}
	    	try {
				
	    		Corso c = comboCorso.getValue();
	    		
	    		if(model.isIscritto(c, matricola) == true) {
	    			
		    		txtResult.appendText("La matricola '"+s.getMatricola()+" "+ s.getNome()+" "+ s.getCognome()+"' è gia iscritta al corso '"+ c.getNome()+"'.\n");
		    	} else {
		    		model.iscriviStudenteAlCorso(s, c);
		    		txtResult.appendText("Matricola '"+s.getMatricola()+" "+ s.getNome()+" "+ s.getCognome()+"' iscritta con successo");
		    	}
	    		
			        	
	        	
			} catch (Exception e) {
				txtResult.appendText("Deve scegliere un corso!");
			}
	    	
			
		} catch (NumberFormatException e) {
			txtResult.appendText("Devi inserire una matricola (numero intero)");
			return;
		}
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtCognome.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtResult.clear();
    	comboCorso.getSelectionModel().clearSelection();
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
