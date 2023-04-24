package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.PlanoDao;
import br.edu.femass.model.Paciente;
import br.edu.femass.model.PlanoDeSaude;
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

public class CadastroPacienteController implements Initializable {

   private Dao<Paciente> pacienteDao = new PacienteDao();
   private Dao<PlanoDeSaude> planoDao = new PlanoDao();

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
    private ComboBox<PlanoDeSaude> CboPlano;

    @FXML
    private ListView<Paciente> listaPaciente;

    @FXML
    private void btnSalvar(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);

        try{
            if (TxtNome.getText().length() == 0 || 
            TxtCpf.getText().length() == 0 ||
            TxtTelefone.getText().length() == 0 ||
            TxtIdade.getText().length() == 0
            ) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios!");
            } else {
                Paciente paciente = new Paciente(
                TxtNome.getText(), 
                TxtCpf.getText(),
                TxtIdade.getText(),
                TxtTelefone.getText(),
                CboPlano.getSelectionModel().getSelectedItem());

                TxtId.setText(paciente.getId().toString());

                if(pacienteDao.gravar(paciente) == false) {
                    alerta.setTitle("Não foi possível salvar o paciente.");
                    alerta.show();
                } else {
                    paciente.ultimoId++;
                }
                exibirPacientes();
            
            }
        } catch (Exception e){
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        TxtNome.setText("");
        TxtCpf.setText("");
        TxtIdade.setText("");
        TxtTelefone.setText("");
        CboPlano.getSelectionModel().select(null);
    }

    @FXML
    private void btnExcluir(ActionEvent event) throws StreamReadException, DatabindException, IOException{
        Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(AlertType.ERROR);

        if (paciente == null) return;
        try {
            if (pacienteDao.excluir(paciente)==false) {
                alerta.setTitle("Não foi possível excluir o paciente.");
                alerta.show();
            }

            exibirPacientes();

            TxtId.setText("");
            TxtNome.setText("");
            TxtCpf.setText("");
            TxtIdade.setText("");
            TxtTelefone.setText("");
            CboPlano.getSelectionModel().select(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNovo (ActionEvent event) {
        TxtId.setText("");
        TxtNome.setText("");
        TxtCpf.setText("");
        TxtIdade.setText("");
        TxtTelefone.setText("");
        CboPlano.getSelectionModel().select(null);
    }

    @FXML
    private void listaPaciente_mouseClicked(MouseEvent event){
        exibirDados();
    }

    @FXML
    private void listaPaciente_keyPressed(KeyEvent event){
        exibirDados();
    }

    private void exibirDados() {
        Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
        if(paciente == null) return;

        TxtNome.setText(paciente.getNome());
        TxtCpf.setText(paciente.getCpf());
        TxtId.setText(paciente.getId().toString());
        TxtIdade.setText(String.valueOf(paciente.getIdade()));
        CboPlano.getSelectionModel().select(paciente.getPlano());
        TxtTelefone.setText(paciente.getTelefones().get(0));
    }

    public void exibirPacientes() throws StreamReadException, DatabindException, IOException {
        try {
            ObservableList<Paciente> data = FXCollections.observableArrayList(pacienteDao.buscar());
            listaPaciente.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    public void exibirPlanos() throws StreamReadException, DatabindException, IOException {
        try {
            ObservableList<PlanoDeSaude> data = FXCollections.observableArrayList(planoDao.buscar());
            CboPlano.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try {
        exibirPlanos();
        exibirPacientes();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}