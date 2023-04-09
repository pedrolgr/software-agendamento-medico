package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.EspecializacaoDao;
import br.edu.femass.model.Especializacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CadastroEspecializacaoController implements Initializable{

    private Dao<Especializacao> especializacaoDao = new EspecializacaoDao();

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
        Alert alerta = new Alert(AlertType.ERROR);

        try{
            if (TxtEspecializacao.getText().length() == 0 || 
            TxtFaculdade.getText().length() == 0) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios!");
            } else {
                Especializacao especializacao = new Especializacao(
                TxtEspecializacao.getText(), 
                TxtFaculdade.getText()
            );

            TxtId.setText(especializacao.getId().toString());

            if(especializacaoDao.gravar(especializacao) == false) {
                alerta.setTitle("Não foi possível salvar o plano.");
                alerta.show();
            }
            exibirEspecializacoes();
            
            }
        } catch (Exception e){
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        TxtEspecializacao.setText("");
        TxtFaculdade.setText("");
        TxtId.setText("");
    }

    @FXML
    private void btnExcluir(ActionEvent event) throws StreamReadException, DatabindException, IOException{
        Especializacao especializacao = listaEspecializacao.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(AlertType.ERROR);

        if(especializacao == null) return;
        try {
            if(especializacaoDao.excluir(especializacao) == false) {
                alerta.setTitle("Não foi possível excluir a especializacao.");
                alerta.show();
            }
            
            exibirEspecializacoes();
            
            TxtEspecializacao.setText("");
            TxtFaculdade.setText("");
            TxtId.setText("");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void listaEspecializacao_mouseClicked(MouseEvent event) {
        exibirDados();
    }

    @FXML
    private void listaEspecializacao_keyPressed(KeyEvent event) {
        exibirDados();
    }

    private void exibirDados() {
        Especializacao especializacao = listaEspecializacao.getSelectionModel().getSelectedItem();
        if(especializacao == null) return;

        TxtEspecializacao.setText(especializacao.getEspecializacao());
        TxtFaculdade.setText(especializacao.getfaculdade());
        TxtId.setText(especializacao.getId().toString());
    }

    public void exibirEspecializacoes() throws StreamReadException, DatabindException, IOException {
        ObservableList<Especializacao> data = FXCollections.observableArrayList(especializacaoDao.buscar());
        listaEspecializacao.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            exibirEspecializacoes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
