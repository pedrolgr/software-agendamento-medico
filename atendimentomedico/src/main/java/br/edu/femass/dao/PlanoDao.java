package br.edu.femass.dao;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.femass.model.PlanoDeSaude;

public class PlanoDao extends Persist implements Dao<PlanoDeSaude> {

    public PlanoDao() {
        super("planos.json");
    }

    public boolean gravar(PlanoDeSaude objeto) throws StreamReadException, IOException {
        Set<PlanoDeSaude> planos = buscar();
        boolean gravou = planos.add(objeto); 

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return gravou;
    }

    public boolean excluir(PlanoDeSaude objeto) throws StreamReadException, DatabindException, IOException {
        Set<PlanoDeSaude> planos = buscar();
        boolean gravou = planos.remove(objeto); 

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return gravou;
    }

    public Set<PlanoDeSaude> buscar() throws StreamReadException, DatabindException, IOException {
       try {
        Set<PlanoDeSaude> planos =  objectMapper.readValue(arquivo, new TypeReference<Set<PlanoDeSaude>>(){});
        PlanoDeSaude.atualizarUltimoId(planos);
        return planos;
       } catch (IOException ex) {
        return new HashSet<PlanoDeSaude>();
       } 
    }

    //private static List<PlanoDeSaude> planos = new ArrayList<PlanoDeSaude>();
}
