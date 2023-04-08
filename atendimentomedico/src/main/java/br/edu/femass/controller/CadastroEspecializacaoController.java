package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.model.Especializacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CadastroEspecializacaoController implements Initializable{

    @FXML
    private TextField TxtEspecializacao;

    @FXML
    private TextField TxtFaculdade;

    @FXML
    private TextField TxtId;

    @FXML
    private ListView<Especializacao> listaEspecializacao;

    @FXML
    private void btnSalvar(ActionEvent event) {
        
    }

    @FXML
    private void btnExcluir(ActionEvent event) {

    }

    @FXML
    private void listaEspecializacao_mouseClicked(MouseEvent event) {

    }

    @FXML
    private void listaEspecializacao_keyPressed(KeyEvent event) {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
