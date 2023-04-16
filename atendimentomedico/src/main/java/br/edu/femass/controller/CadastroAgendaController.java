package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.Dao;
import br.edu.femass.dao.EspecializacaoDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.model.Agenda;
import br.edu.femass.model.Especializacao;
import br.edu.femass.model.Medico;
import br.edu.femass.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class CadastroAgendaController implements Initializable{

    private Dao<Paciente> pacienteDao = new PacienteDao();
    private Dao<Medico> medicoDao = new MedicoDao();
    private Dao<Agenda> agendaDao = new AgendaDao();
    private Dao<Especializacao> especializacaoDao = new EspecializacaoDao();

    @FXML
    private TextField TxtId;

    @FXML
    private TextField TxtHora;

    @FXML
    private ComboBox<Paciente> CboPaciente;

    @FXML
    private ComboBox<Medico> CboMedico;

    @FXML
    private ComboBox<Especializacao> CboEsp;

    @FXML
    private TextField TxtData;

    @FXML
    private TableView<Agenda> tableAgendamento;

    @FXML
    private TableColumn<Agenda, Paciente> colPaciente;

    @FXML
    private TableColumn<Agenda, Medico> colMedico;

    @FXML
    private TableColumn<Agenda, String> colData;

    @FXML
    private TableColumn<Agenda, String> colHora;
    
    @FXML private TableColumn<Agenda, Especializacao> colEsp;

    @FXML
    private void btnSalvar(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);

        try{
            if (TxtData.getText().length() == 0 || 
            TxtHora.getText().length() == 0 ||
            CboPaciente.getValue() == null ||
            CboMedico.getValue() == null
            ) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios!");
            } else {
                Agenda agenda = new Agenda(
                CboPaciente.getSelectionModel().getSelectedItem(), 
                CboMedico.getSelectionModel().getSelectedItem(),
                TxtData.getText(),
                TxtHora.getText(),
                CboEsp.getSelectionModel().getSelectedItem());

                TxtId.setText(agenda.getId().toString());

                if(agendaDao.gravar(agenda) == false) {
                    alerta.setTitle("Não foi possível salvar a agenda.");
                    alerta.show();
                } else {
                    agenda.ultimoId++;
                }
                exibirAgendas();
            
            }
        } catch (Exception e){
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        
    }

    @FXML
    private void btnExcluir(ActionEvent event) throws StreamReadException, DatabindException, IOException{
        Agenda agenda = tableAgendamento.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(AlertType.ERROR);

        if (agenda == null) return;
        try {
            if (agendaDao.excluir(agenda)==false) {
                alerta.setTitle("Não foi possível excluir a agenda.");
                alerta.show();
            }

            exibirAgendas();

            CboPaciente.getSelectionModel().select(null);
            CboMedico.getSelectionModel().select(null);
            TxtData.setText("");
            TxtHora.setText("");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void listaAgendamento_mouseClicked(MouseEvent event){
        exibirDados();
    }

    @FXML
    private void listaAgendamento_keyPressed(KeyEvent event){
        exibirDados();
    }

    @FXML
    public void CbpEspAction(ActionEvent actionEvent) throws StreamReadException, DatabindException, IOException {
        Especializacao especializacao = CboEsp.getSelectionModel().getSelectedItem();
        if (especializacao == null) return;

        exibirMedicos(especializacao);
    }

    private void exibirDados() {
        Agenda agenda = tableAgendamento.getSelectionModel().getSelectedItem();
        if(agenda == null) return;

        TxtId.setText(agenda.getId().toString());
        CboPaciente.getSelectionModel().select(agenda.getPaciente());
        CboMedico.getSelectionModel().select(agenda.getMedico());
        CboEsp.getSelectionModel().select(agenda.getEspecializacao());
        TxtData.setText(agenda.getData());
        TxtHora.setText(agenda.getHora());
    }

    public void exibirAgendas() throws StreamReadException, DatabindException, IOException {
        try {
            ObservableList<Agenda> data = FXCollections.observableArrayList(agendaDao.buscar());
            tableAgendamento.setItems(data);
            
            colPaciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
            colMedico.setCellValueFactory(new PropertyValueFactory<>("medico"));
            colData.setCellValueFactory(new PropertyValueFactory<>("data"));
            colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
            colEsp.setCellValueFactory(new PropertyValueFactory<>("especializacao"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    public void exibirPacientes() throws StreamReadException, DatabindException, IOException {
        try {
            ObservableList<Paciente> data = FXCollections.observableArrayList(pacienteDao.buscar());
            CboPaciente.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    public void exibirEspecializacoes () throws StreamReadException, DatabindException, IOException {
        try {
            ObservableList<Especializacao> data = FXCollections.observableArrayList(especializacaoDao.buscar());
            CboEsp.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
	
	public void exibirMedicos(Especializacao especializacao) throws StreamReadException, DatabindException, IOException {
        try {
            ObservableList<Medico> data = FXCollections.observableArrayList(
                ((MedicoDao) medicoDao).buscarEspecializacao(especializacao));
            CboMedico.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            exibirAgendas();
            exibirPacientes();
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
