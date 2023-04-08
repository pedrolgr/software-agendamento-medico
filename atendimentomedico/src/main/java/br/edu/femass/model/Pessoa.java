package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.femass.utils.Validador;

public class Pessoa {
    private Long id;
    private String nome;
    private String cpf;
    private int idade;
    private List<String> telefones = new ArrayList<String>();

    private static Long ultimoId = 0L;

    public Pessoa(String nome, String cpf, int idade, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefones.add(telefone);

        this.id = ultimoId+1;
        ultimoId++;

        if(Validador.validarCPF(cpf) == false) throw new IllegalArgumentException("CPF Inválido");
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public int getIdade(){
        return this.idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

}
