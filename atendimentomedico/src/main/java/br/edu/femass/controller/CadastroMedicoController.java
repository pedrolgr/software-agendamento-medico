package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.EspecializacaoDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.model.Especializacao;
import br.edu.femass.model.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class CadastroMedicoController implements Initializable{

    private Dao<Medico> medicoDao = new MedicoDao();
    private Dao<Especializacao> especializacaoDao = new EspecializacaoDao();

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtCpf;

    @FXML
    private TextField TxtTelefone;

    @FXML
    private TextField TxtIdade;

    @FXML
    private TextField TxtId;

    @FXML
    private TextField TxtCrm;

    @FXML
    private ComboBox<Especializacao> CboEspecializacao;

    @FXML
    private ListView<Medico> listaMedico;

    @FXML
    private void btnSalvar(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);

        try{
            if (TxtNome.getText().length() == 0 || 
            TxtCpf.getText().length() == 0 ||
            TxtTelefone.getText().length() == 0 ||
            TxtIdade.getText().length() == 0 ||
            CboEspecializacao.getValue() == null ||
            TxtCrm.getText().length() == 0
            ) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios!");
            } else {
                Medico medico = new Medico(
                TxtNome.getText(), 
                TxtCpf.getText(),
                TxtIdade.getText(),
                TxtTelefone.getText(),
                TxtCrm.getText(),
                CboEspecializacao.getSelectionModel().getSelectedItem());

                TxtId.setText(medico.getId().toString());

                if(medicoDao.gravar(medico) == false) {
                    alerta.setTitle("Não foi possível salvar o medico.");
                    alerta.show();
                } else {
                    medico.ultimoId++;
                }
                
                exibirMedicos();
            
            }
        } catch (Exception e){
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        TxtNome.setText("");
        TxtCpf.setText("");
        TxtIdade.setText("");
        TxtTelefone.setText("");
        TxtCrm.setText("");
        CboEspecializacao.getSelectionModel().select(null);
    }

    @FXML
    private void btnExcluir(ActionEvent event) throws StreamReadException, DatabindException, IOException{
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(AlertType.ERROR);

        if (medico == null) return;
        try {
            if (medicoDao.excluir(medico)==false) {
                alerta.setTitle("Não foi possível excluir o medico.");
                alerta.show();
            }

            exibirMedicos();

            TxtNome.setText("");
            TxtCpf.setText("");
            TxtIdade.setText("");
            TxtTelefone.setText("");
            TxtCrm.setText("");
            CboEspecializacao.getSelectionModel().select(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void listaMedico_mouseClicked(MouseEvent event){
        exibirDados();
    }

    @FXML
    private void listaMedico_keyPressed(KeyEvent event){
        exibirDados();
    }

    private void exibirDados() {
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if(medico == null) return;

        TxtNome.setText(medico.getNome());
        TxtCpf.setText(medico.getCpf());
        TxtId.setText(medico.getId().toString());
        TxtIdade.setText(String.valueOf(medico.getIdade()));
        CboEspecializacao.getSelectionModel().select(medico.getEspecializacao());
        TxtTelefone.setText(medico.getTelefones().get(0));
    }

    public void exibirMedicos() throws StreamReadException, DatabindException, IOException {
        try {
            ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.buscar());
            listaMedico.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    public void exibirEspecializacoes() throws StreamReadException, DatabindException, IOException {
        try {
            ObservableList<Especializacao> data = FXCollections.observableArrayList(especializacaoDao.buscar());
            CboEspecializacao.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            exibirMedicos();
            exibirEspecializacoes();
            } catch (StreamReadException e) {
                e.printStackTrace();
            } catch (DatabindException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
}
