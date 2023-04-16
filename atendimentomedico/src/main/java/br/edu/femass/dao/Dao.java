package br.edu.femass.dao;

import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Especializacao;
import javafx.util.Callback;

public interface Dao<T> {

    public boolean gravar(T objeto) throws StreamReadException, IOException ;
    
    public boolean excluir(T objeto) throws StreamReadException, DatabindException, IOException ;
        
    public Set<T> buscar() throws StreamReadException, DatabindException, IOException ;
        
    
}
