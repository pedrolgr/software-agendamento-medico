package br.edu.femass.dao;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Especializacao;

public class EspecializacaoDao extends Persist implements Dao<Especializacao> {

    public EspecializacaoDao() {
        super("especializacoes.json");
    }

    @Override
    public boolean gravar(Especializacao objeto) throws StreamReadException, IOException {
        Set<Especializacao> especializacoes = buscar();
        boolean gravou = especializacoes.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especializacoes);
        return gravou;
    }

    @Override
    public boolean excluir(Especializacao objeto) throws StreamReadException, DatabindException, IOException {
        Set<Especializacao> especializacoes = buscar();
        boolean gravou = especializacoes.remove(objeto); 

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especializacoes);
        return gravou;
    }

    @Override
    public Set<Especializacao> buscar() throws StreamReadException, DatabindException, IOException {
        try {
            Set<Especializacao> especializacoes =  objectMapper.readValue(arquivo, new TypeReference<Set<Especializacao>>(){});
            Especializacao.atualizarUltimoId(especializacoes);
            return especializacoes;
           } catch (IOException ex) {
            return new HashSet<Especializacao>();
           } 
    }
    
}
