package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Agenda;
import br.edu.femass.model.Medico;
import br.edu.femass.model.Paciente;

public class AgendaDao extends Persist implements Dao<Agenda>{

    public AgendaDao() {
        super("agendas.json");
    }

    @Override
    public boolean gravar(Agenda objeto) throws StreamReadException, IOException {
        Set<Agenda> agenda = buscar();
        boolean gravou = agenda.add(objeto); 

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, agenda);
        return gravou;
    }

    @Override
    public boolean excluir(Agenda objeto) throws StreamReadException, DatabindException, IOException {
        Set<Agenda> agenda = buscar();
        boolean gravou =  agenda.remove(objeto); 

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, agenda);
        return gravou;
    }

    @Override
    public Set<Agenda> buscar() throws StreamReadException, DatabindException, IOException {
        try {
            Set<Agenda> agendas = objectMapper.readValue(arquivo, new TypeReference<Set<Agenda>>(){});
            Agenda.atualizarUltimoId(agendas);
            return agendas;
           } catch (IOException ex) {
            return new HashSet<Agenda>();
           } 
    }

    public List<Agenda> buscarAgendas(Medico medico) throws StreamReadException, DatabindException, IOException {
        Set<Agenda> agendas = buscar();

        List<Agenda> medicosAgendados = agendas
        .stream()
        .filter(agenda -> agenda.getMedico().equals(medico))
        .collect(Collectors.toList());

        return medicosAgendados;
    }
    
}
