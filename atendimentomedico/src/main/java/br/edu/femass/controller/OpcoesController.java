package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpcoesController implements Initializable{

    @FXML
    private void btnPaciente(ActionEvent action) throws IOException {
        
        FXMLLoader fx = new FXMLLoader(OpcoesController.class.getResource("/fxml/CadastroPaciente.fxml"));
       
        try {
            Scene s = new Scene(fx.load());
            Stage st = new Stage();
            st.setTitle("Cadastro Paciente");
            st.setScene(s);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnMedico(ActionEvent action) {
        
        FXMLLoader fx = new FXMLLoader(OpcoesController.class.getResource("/fxml/CadastroMedico.fxml"));
       
        try {
            Scene s = new Scene(fx.load());
            Stage st = new Stage();
            st.setTitle("Cadastro Médico");
            st.setScene(s);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void btnPlano(ActionEvent action) {
        
        FXMLLoader fx = new FXMLLoader(OpcoesController.class.getResource("/fxml/CadastroPlano.fxml"));
       
        try {
            Scene s = new Scene(fx.load());
            Stage st = new Stage();
            st.setTitle("Cadastro Plano");
            st.setScene(s);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void btnEspecializacao(ActionEvent action) {

        FXMLLoader fx = new FXMLLoader(OpcoesController.class.getResource("/fxml/CadastroEspecializacao.fxml"));
       
        try {
            Scene s = new Scene(fx.load());
            Stage st = new Stage();
            st.setTitle("Cadastro Especializacao");
            st.setScene(s);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void btnConsulta(ActionEvent action) {
        System.out.println("Consulta");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         
    }
    
}
