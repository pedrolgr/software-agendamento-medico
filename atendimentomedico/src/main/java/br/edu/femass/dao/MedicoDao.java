package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Medico;

public class MedicoDao extends Persist implements Dao<Medico>{

    public MedicoDao() {
        super("medicos.json");
    }

    @Override
    public boolean gravar(Medico objeto) throws StreamReadException, IOException {
        Set<Medico> medico = buscar();
        boolean gravou = medico.add(objeto); 

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medico);
        return gravou;
    }

    @Override
    public boolean excluir(Medico objeto) throws StreamReadException, DatabindException, IOException {
        Set<Medico> medico = buscar();
        boolean gravou = medico.remove(objeto); 

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medico);
        return gravou;
    }
        

    @Override
    public Set<Medico> buscar() throws StreamReadException, DatabindException, IOException {
        try {
            Set<Medico> medicos =  objectMapper.readValue(arquivo, new TypeReference<Set<Medico>>(){});
            Medico.atualizarUltimoId(medicos);
            return medicos;
           } catch (IOException ex) {
            return new HashSet<Medico>();
           } 
    }
    
}
