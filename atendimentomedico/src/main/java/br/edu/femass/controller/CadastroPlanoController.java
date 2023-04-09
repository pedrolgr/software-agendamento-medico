package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.PlanoDao;
import br.edu.femass.model.PlanoDeSaude;
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


public class CadastroPlanoController implements Initializable{

    private Dao<PlanoDeSaude> planoDao = new PlanoDao();

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtTipo;

    @FXML
    private TextField TxtId;

    @FXML
    private ListView<PlanoDeSaude> listaPlano;

    @FXML
    private void btnSalvar(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);

        try{
            if (TxtNome.getText().length() == 0 || 
            TxtTipo.getText().length() == 0) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios!");
            } else {
                PlanoDeSaude plano = new PlanoDeSaude(
                TxtNome.getText(), 
                TxtTipo.getText()
            );

            TxtId.setText(plano.getId().toString());

            if(planoDao.gravar(plano) == false) {
                alerta.setTitle("Não foi possível salvar o plano.");
                alerta.show();
            }
            exibirPlanos();
            
            }
        } catch (Exception e){
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        TxtNome.setText("");
        TxtTipo.setText("");
        TxtId.setText("");
    }

    @FXML
    private void btnExcluir(ActionEvent event) throws StreamReadException, DatabindException, IOException{
        PlanoDeSaude plano = listaPlano.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(AlertType.ERROR);

        if(plano == null) return;
        try {
            if(planoDao.excluir(plano) == false) {
                alerta.setTitle("Não foi possível excluir o plano.");
                alerta.show();
            }
            
            exibirPlanos();
            
            TxtNome.setText("");
            TxtTipo.setText("");
            TxtId.setText("");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void listaPlano_mouseClicked(MouseEvent event){
        exibirDados();
    }

    @FXML
    private void listaPlano_keyPressed(KeyEvent event){
        exibirDados();
    }

    private void exibirDados() {
        PlanoDeSaude plano = listaPlano.getSelectionModel().getSelectedItem();
        if(plano == null) return;

        TxtNome.setText(plano.getNome());
        TxtTipo.setText(plano.getTipo());
        TxtId.setText(plano.getId().toString());
    }


    public void exibirPlanos() throws StreamReadException, DatabindException, IOException {
        ObservableList<PlanoDeSaude> data = FXCollections.observableArrayList(planoDao.buscar());
        listaPlano.setItems(data);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            exibirPlanos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
