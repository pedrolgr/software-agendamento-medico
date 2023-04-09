package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Medico extends Pessoa{
    protected Long id;
    protected String crm;
    protected Especializacao especializacao;

    public static Long ultimoId = 0L;

    public Medico() {

    }

    public Medico(String nome, 
    String cpf, 
    String idade, 
    String telefone, 
    String crm, 
    Especializacao especializacao) {
        super(nome, cpf, idade, telefone);
        this.especializacao = especializacao;
        this.crm = crm;

        this.id = ultimoId+1;
    }

    public Long getId(){
        return this.id;
    }

    public Especializacao getEspecializacao() {
        return especializacao;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public static void atualizarUltimoId(Set<Medico> medicos) {
        for (Medico medico: medicos) {
            if (medico.getId().longValue() > ultimoId) {
                ultimoId = medico.getId();
            }
        }
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}
