package br.edu.femass.dao;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EspecializacaoDao {
    private File arquivo = new File("especializacoes.json");
    private ObjectMapper objectMapper = new ObjectMapper();
}
