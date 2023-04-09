package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Paciente;

public class PacienteDao extends Persist implements Dao<Paciente>{

    public PacienteDao() {
        super("pacientes.json");
    }

    @Override
    public boolean gravar(Paciente objeto) throws StreamReadException, IOException {
        Set<Paciente> paciente = buscar();
        boolean gravou = paciente.add(objeto); 

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, paciente);
        return gravou;
    }

    @Override
    public boolean excluir(Paciente objeto) throws StreamReadException, DatabindException, IOException {
        Set<Paciente> paciente = buscar();
        boolean gravou =  paciente.remove(objeto); 

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, paciente);
        return gravou;
    }

    @Override
    public Set<Paciente> buscar() throws StreamReadException, DatabindException, IOException {
        try {
            Set<Paciente> pacientes =  objectMapper.readValue(arquivo, new TypeReference<Set<Paciente>>(){});
            Paciente.atualizarUltimoId(pacientes);
            return pacientes;
           } catch (IOException ex) {
            return new HashSet<Paciente>();
           } 
        
    }
    
}
