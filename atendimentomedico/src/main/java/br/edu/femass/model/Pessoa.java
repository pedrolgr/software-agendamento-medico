package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.femass.utils.Validador;

public class Pessoa {
    // protected Long id;
    protected String nome;
    protected String cpf;
    protected String idade;
    protected List<String> telefones = new ArrayList<String>();

    // protected static Long ultimoId = 0L;

    public Pessoa() {

    }

    public Pessoa(String nome, String cpf, String idade, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefones.add(telefone);

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

    public List<String> getTelefones(){
        return telefones;
    }

    public void addTelefone(String telefone) {
        this.telefones.add(telefone);
    }

    public void removerTelefone(String telefone) throws Exception {
        if (telefones.size()==1) {
            throw new Exception("O cliente tem que ter pelo menos um telefone");
        }
        this.telefones.remove(telefone);
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    public String getIdade(){
        return this.idade;
    }

    public void setIdade(String idade){
        this.idade = idade;
    }

}
